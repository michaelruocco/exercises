package uk.co.mruoc.exercises.channelprocessing;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import uk.co.mruoc.exercises.channelprocessing.channel.Channels;
import uk.co.mruoc.exercises.channelprocessing.channel.ChannelLoader;
import uk.co.mruoc.exercises.channelprocessing.function.ChannelCFunction;
import uk.co.mruoc.exercises.channelprocessing.function.ChannelFunction;
import uk.co.mruoc.exercises.channelprocessing.function.MetricBFunction;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;
import uk.co.mruoc.exercises.channelprocessing.parameter.ParameterLoader;

@Slf4j
public class Main {

    public static void main(String[] a) {
        Channels channels = new ChannelLoader().load("channels.txt");
        Parameters parameters = new ParameterLoader().load("parameters.txt");

        Flux<Arguments> args = channels.getArguments();

        ChannelFunction channelC = new ChannelCFunction();
        args.map(arg -> channelC.apply(parameters, arg))
                .last()
                .subscribe(arg -> logValue(arg, 'C'));

        ChannelFunction metricB = new MetricBFunction();
        args.map(arg -> metricB.apply(parameters, arg))
                .last()
                .subscribe(arg -> logValue(arg, 'b'));
    }

    private static void logValue(Arguments arguments, char id) {
        log.info("{} value is {}", id, arguments.get(id));
    }
}