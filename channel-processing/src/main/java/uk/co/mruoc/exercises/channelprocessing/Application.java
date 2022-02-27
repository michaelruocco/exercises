package uk.co.mruoc.exercises.channelprocessing;

import com.beust.jcommander.JCommander;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.channel.Channels;
import uk.co.mruoc.exercises.channelprocessing.channel.ChannelLoader;
import uk.co.mruoc.exercises.channelprocessing.function.ChannelFunction;
import uk.co.mruoc.exercises.channelprocessing.function.FunctionLoader;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;
import uk.co.mruoc.exercises.channelprocessing.parameter.ParameterLoader;
import uk.co.mruoc.file.content.ContentLoader;

import java.util.function.UnaryOperator;

@Slf4j
public class Application {

    public static void main(String[] args) {
        ApplicationArgs appArgs = new ApplicationArgs();
        JCommander.newBuilder().addObject(appArgs).build().parse(args);
        log.debug("running application with arguments {}", appArgs);
        UnaryOperator<String> fileSystemLoader = ContentLoader::loadContentFromFileSystem;
        Channels channels = new ChannelLoader(fileSystemLoader).load(appArgs.getChannelsPath());
        Parameters parameters = new ParameterLoader(fileSystemLoader).load(appArgs.getParametersPath());
        ChannelFunction function = new FunctionLoader(fileSystemLoader).load(appArgs.getFunctionsPath());

        Variables outputVariables = channels.getVariables()
                .map(variables -> function.apply(parameters, variables))
                .last()
                .blockOptional()
                .orElseThrow(() -> new IllegalStateException("no output variables calculated"));

        appArgs.getOutputTargetsCollection().forEach(target -> log.info("{} value is {}", target, outputVariables.get(target)));
    }

}
