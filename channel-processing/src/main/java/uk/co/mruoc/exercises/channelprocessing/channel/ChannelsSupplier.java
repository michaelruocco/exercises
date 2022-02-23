package uk.co.mruoc.exercises.channelprocessing.channel;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import uk.co.mruoc.exercises.channelprocessing.Arguments;
import uk.co.mruoc.file.content.ContentLoader;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ChannelsSupplier implements Supplier<Channels> {

    private final String path;

    @Override
    public Channels get() {
        String content = ContentLoader.loadContentFromClasspath(path);
        return new Channels(toMap(content));
    }

    private static Map<Character, Flux<Arguments>> toMap(String content) {
        return Arrays.stream(content.split(System.lineSeparator()))
                .map(ChannelsSupplier::toEntry)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static Map.Entry<Character, Flux<Arguments>> toEntry(String line) {
        String[] parts = line.split(",");
        char id = parts[0].trim().charAt(0);

        Flux<Arguments> values = Flux.fromArray(parts)
                .skip(1)
                .map(value -> new BigDecimal(value.trim()))
                .map(value -> new Arguments(id, value));
        return new AbstractMap.SimpleEntry<>(id, values);
    }

}
