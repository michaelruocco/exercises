package uk.co.mruoc.exercises.channelprocessing;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import uk.co.mruoc.exercises.channelprocessing.channel.Channels;
import uk.co.mruoc.exercises.channelprocessing.channel.ChannelsSupplier;
import uk.co.mruoc.exercises.channelprocessing.function.ChannelCFunction;
import uk.co.mruoc.exercises.channelprocessing.function.ChannelFunction;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;
import uk.co.mruoc.exercises.channelprocessing.parameter.ParametersSupplier;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Channels channels = new ChannelsSupplier("channels.txt").get();
        Parameters parameters = new ParametersSupplier("parameters.txt").get();

        Flux<Arguments> x = channels.get('X');

        ChannelFunction channelC = new ChannelCFunction();
        x.map(arg -> channelC.apply(parameters, arg))
                .last()
                .subscribe(arg -> logValue(arg, 'C'));

        ChannelFunction metricB = new ChannelCFunction();
        x.map(arg -> metricB.apply(parameters, arg))
                .last()
                .subscribe(arg -> logValue(arg, 'b'));
    }

    private static void logValue(Arguments arguments, char id) {
        log.info("{} value is {}", id, arguments.get(id));
    }
}