package uk.co.mruoc.exercises.channelprocessing.channel;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import uk.co.mruoc.exercises.channelprocessing.Arguments;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class Channels {

    private final Map<Character, Flux<Arguments>> values;

    public Flux<Arguments> get(char id) {
        return Optional.ofNullable(values.get(id))
                .orElseThrow(() -> new ChannelNotFoundException(id));
    }

}
