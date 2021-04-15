package uk.co.mruoc.exercises.cronparser;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import uk.co.mruoc.exercises.cronparser.parser.AsteriskNotation;
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
                new AsteriskNotation(),
                new SimpleNotationParser()
        );
    }

    public CronExpressionParser(NotationParser... parsers) {
        this(Arrays.asList(parsers));
    }

    public CronResult parse(String expression) {
        String[] values = StringUtils.split(expression);
        return CronResult.builder()
                .hours(parse(values, TimeUnit.HOURS))
                .daysOfMonth(parse(values, TimeUnit.DAYS_OF_MONTH))
                .months(parse(values, TimeUnit.MONTHS))
                .build();
    }

    private int[] parse(String[] values, TimeUnit timeUnit) {
        String value = values[timeUnit.ordinal()];
        return parsers.stream()
                .filter(parser -> parser.appliesTo(value))
                .map(parser -> parser.toValues(value, timeUnit))
                .findFirst()
                .orElseThrow(() -> new ParserNotFoundException(value));
    }

}
