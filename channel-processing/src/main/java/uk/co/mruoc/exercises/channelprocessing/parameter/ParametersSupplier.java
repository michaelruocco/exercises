package uk.co.mruoc.exercises.channelprocessing.parameter;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.file.content.ContentLoader;

import java.math.BigDecimal;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ParametersSupplier implements Supplier<Parameters> {

    private final String path;

    @Override
    public Parameters get() {
        String content = ContentLoader.loadContentFromClasspath(path);
        return new Parameters(toMap(content));
    }

    private static Map<Character, BigDecimal> toMap(String content) {
        return Arrays.stream(content.split(System.lineSeparator()))
                .map(ParametersSupplier::toEntry)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static Map.Entry<Character, BigDecimal> toEntry(String line) {
        String[] parts = line.split(",");
        char id = parts[0].charAt(0);
        BigDecimal value = new BigDecimal(parts[1].trim());
        return new SimpleEntry<>(id, value);
    }

}
