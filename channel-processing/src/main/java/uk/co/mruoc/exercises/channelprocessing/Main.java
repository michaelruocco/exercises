package uk.co.mruoc.exercises.channelprocessing;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import uk.co.mruoc.exercises.channelprocessing.channel.Channels;
import uk.co.mruoc.exercises.channelprocessing.channel.ChannelLoader;
import uk.co.mruoc.exercises.channelprocessing.function.ChannelFunction;
import uk.co.mruoc.exercises.channelprocessing.function.FunctionLoader;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;
import uk.co.mruoc.exercises.channelprocessing.parameter.ParameterLoader;
import uk.co.mruoc.file.content.ContentLoader;

import java.util.Arrays;
import java.util.function.UnaryOperator;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("running application with arguments {}", Arrays.toString(args));
        UnaryOperator<String> fileSystemLoader = ContentLoader::loadContentFromFileSystem;
        Channels channels = new ChannelLoader(fileSystemLoader).load(args[0]);
        Parameters parameters = new ParameterLoader(fileSystemLoader).load(args[1]);
        ChannelFunction function = new FunctionLoader(fileSystemLoader).load(args[2]);
        String targets = args[3];

        Variables outputVariables = channels.getVariables()
                .map(variables -> function.apply(parameters, variables))
                .last()
                .blockOptional()
                .orElseThrow(() -> new IllegalStateException("no output variables calculated"));

        Arrays.stream(targets.split(","))
                .map(StringUtils::deleteWhitespace)
                .map(target -> target.charAt(0))
                .forEach(target -> log.info("{} value is {}", target, outputVariables.get(target)));
    }

}
