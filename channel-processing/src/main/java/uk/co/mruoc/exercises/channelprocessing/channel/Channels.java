package uk.co.mruoc.exercises.channelprocessing.channel;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import uk.co.mruoc.exercises.channelprocessing.Variables;

import java.math.BigDecimal;
import java.util.Collection;

@RequiredArgsConstructor
public class Channels {

    private final Flux<Variables> arguments;

    public Flux<Variables> getArguments() {
        return arguments;
    }

    public Collection<BigDecimal> getValuesCollection(char id) {
        return getValues(id).collectList().block();
    }

    public int size() {
        return arguments.next()
                .blockOptional()
                .map(Variables::size)
                .orElse(0);
    }

    public Flux<BigDecimal> getValues(char id) {
        return arguments.filter(arg -> arg.contains(id)).map(arg -> arg.get(id));
    }

}
