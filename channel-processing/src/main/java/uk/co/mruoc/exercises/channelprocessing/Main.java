package uk.co.mruoc.exercises.channelprocessing;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import uk.co.mruoc.exercises.channelprocessing.channel.Channels;
import uk.co.mruoc.exercises.channelprocessing.channel.ChannelsSupplier;
import uk.co.mruoc.exercises.channelprocessing.function.ChannelFunction;
import uk.co.mruoc.exercises.channelprocessing.function.Function1;
import uk.co.mruoc.exercises.channelprocessing.function.Function2;
import uk.co.mruoc.exercises.channelprocessing.function.Function3;
import uk.co.mruoc.exercises.channelprocessing.function.Function4;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;
import uk.co.mruoc.exercises.channelprocessing.parameter.ParametersSupplier;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Channels channels = new ChannelsSupplier("channels.txt").get();
        Parameters parameters = new ParametersSupplier("parameters.txt").get();

        ChannelFunction function1 = new Function1();
        ChannelFunction function2 = new Function2();
        ChannelFunction function3 = new Function3();
        ChannelFunction function4 = new Function4();

        Flux<Arguments> X = channels.get('X');
        Flux<Arguments> Y = X.map(arg -> function1.apply(parameters, arg));
        Flux<Arguments> A = X.map(arg -> function3.apply(parameters, arg));
        Flux<Arguments> b = Flux.zip(Y, A).map(Main::zip).map(arg -> function2.apply(parameters, arg));
        b.map(arg -> function4.apply(parameters, arg)).subscribe();
    }

    private static Arguments zip(Tuple2<Arguments, Arguments> tuple) {
        Arguments args1 = tuple.getT1();
        Arguments args2 = tuple.getT2();
        args1.addAll(args2);
        return args1;
    }
}
