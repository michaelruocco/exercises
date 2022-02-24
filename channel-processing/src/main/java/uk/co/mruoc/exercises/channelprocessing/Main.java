package uk.co.mruoc.exercises.channelprocessing;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.channel.Channels;
import uk.co.mruoc.exercises.channelprocessing.channel.ChannelLoader;
import uk.co.mruoc.exercises.channelprocessing.function.ChannelFunction;
import uk.co.mruoc.exercises.channelprocessing.function.MetricBFunction;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;
import uk.co.mruoc.exercises.channelprocessing.parameter.ParameterLoader;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Channels channels = new ChannelLoader().load("channels.txt");
        Parameters parameters = new ParameterLoader().load("parameters.txt");
        ChannelFunction metricB = new MetricBFunction();

        channels.getArguments()
                .map(arg -> metricB.apply(parameters, arg))
                .last()
                .subscribe(arg -> log.info("b value is {}", arg.get('b')));
    }

}
