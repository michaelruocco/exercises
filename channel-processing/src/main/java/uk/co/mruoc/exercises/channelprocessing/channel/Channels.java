package uk.co.mruoc.exercises.channelprocessing.channel;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import uk.co.mruoc.exercises.channelprocessing.Arguments;

import java.math.BigDecimal;
import java.util.Collection;

@RequiredArgsConstructor
public class Channels {

    private final Flux<Arguments> arguments;

    public Flux<Arguments> getArguments() {
        return arguments;
    }

    public Collection<BigDecimal> getValues(char id) {
        return getChannelValues(id).collectList().block();
    }

    public Flux<BigDecimal> get(char id) {
        return getChannelValues(id);
    }

    public int size() {
        return arguments.next()
                .blockOptional()
                .map(Arguments::size)
                .orElse(0);
    }

    private Flux<BigDecimal> getChannelValues(char id) {
        return arguments.filter(arg -> arg.contains(id)).map(arg -> arg.get(id));
    }

}