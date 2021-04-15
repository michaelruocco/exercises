package uk.co.mruoc.exercises.cronparser;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import uk.co.mruoc.exercises.cronparser.parser.CommaNotationParser;
import uk.co.mruoc.exercises.cronparser.parser.NotationParser;
import uk.co.mruoc.exercises.cronparser.parser.SimpleNotationParser;

import java.util.Arrays;
import java.util.Collection;

@RequiredArgsConstructor
public class CronExpressionParser {

    private final Collection<NotationParser> parsers;

    public CronExpressionParser() {
        this(new CommaNotationParser(),
                new SimpleNotationParser()
        );
    }

    public CronExpressionParser(NotationParser... parsers) {
        this(Arrays.asList(parsers));
    }

    public CronResult parse(String expression) {
        String[] values = StringUtils.split(expression);
        return CronResult.builder()
                .hours(parse(values, 1))
                .daysOfMonth(parse(values, 2))
                .build();
    }

    private int[] parse(String[] values, int index) {
        String value = values[index];
        return parsers.stream()
                .filter(parser -> parser.appliesTo(value))
                .map(parser -> parser.toValues(value, toTimeUnit(index)))
                .findFirst()
                .orElseThrow(() -> new ParserNotFoundException(value));
    }

    private static TimeUnit toTimeUnit(int index) {
        return TimeUnit.values()[index];
    }



}
