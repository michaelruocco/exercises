package uk.co.mruoc.exercises.cronparser.domain.notation;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import uk.co.mruoc.exercises.cronparser.domain.TimeUnit;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class ComplexNotationParser implements NotationParser {

    private final Collection<NotationParser> parsers;

    public ComplexNotationParser() {
        this(new WildcardNotationParser(),
                new RangeNotationParser(),
                new IntervalNotationParser(),
                new SimpleNotationParser()
        );
    }

    public ComplexNotationParser(NotationParser... parsers) {
        this(Arrays.asList(parsers));
    }

    @Override
    public boolean appliesTo(String value) {
        if (appliesToSegment(value)) {
            return true;
        }
        return toSegments(value).stream().allMatch(this::appliesToSegment);
    }

    @Override
    public int[] toValues(String input, TimeUnit unit) {
        if (appliesToSegment(input)) {
            return segmentToValues(input, unit).toArray();
        }
        return toSegments(input).stream()
                .flatMapToInt(segment -> segmentToValues(segment, unit))
                .sorted()
                .toArray();
    }

    private Collection<String> toSegments(String value) {
        return Arrays.asList(StringUtils.split(value, ","));
    }

    private boolean appliesToSegment(String segment) {
        return parsers.stream().anyMatch(parser -> parser.appliesTo(segment));
    }

    private IntStream segmentToValues(String segment, TimeUnit unit) {
        return parsers.stream().filter(parser -> parser.appliesTo(segment))
                .map(parser -> parser.toValues(segment, unit))
                .map(IntStream::of)
                .flatMapToInt(Function.identity());
    }

}
