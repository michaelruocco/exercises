package uk.co.mruoc.exercises.cronparser.expression;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import uk.co.mruoc.exercises.cronparser.expression.notation.AsteriskNotationParser;
import uk.co.mruoc.exercises.cronparser.expression.notation.CommaNotationParser;
import uk.co.mruoc.exercises.cronparser.expression.notation.IntervalNotationParser;
import uk.co.mruoc.exercises.cronparser.expression.notation.NotationParser;
import uk.co.mruoc.exercises.cronparser.expression.notation.RangeNotationParser;
import uk.co.mruoc.exercises.cronparser.expression.notation.SimpleNotationParser;

import java.util.Arrays;
import java.util.Collection;

@RequiredArgsConstructor
public class CronExpressionParser {

    private final Collection<NotationParser> parsers;

    public CronExpressionParser() {
        this(new CommaNotationParser(),
                new AsteriskNotationParser(),
                new RangeNotationParser(),
                new IntervalNotationParser(),
                new SimpleNotationParser()
        );
    }

    public CronExpressionParser(NotationParser... parsers) {
        this(Arrays.asList(parsers));
    }

    public CronResult parse(String expression) {
        String[] values = StringUtils.split(expression);
        return CronResult.builder()
                .minutes(parse(values, TimeUnit.MINUTES))
                .hours(parse(values, TimeUnit.HOURS))
                .daysOfMonth(parse(values, TimeUnit.DAYS_OF_MONTH))
                .months(parse(values, TimeUnit.MONTHS))
                .daysOfWeek(parse(values, TimeUnit.DAYS_OF_WEEK))
                .command(values[5])
                .build();
    }

    private int[] parse(String[] values, TimeUnit timeUnit) {
        String value = values[timeUnit.ordinal()];
        return parsers.stream()
                .filter(parser -> parser.appliesTo(value))
                .map(parser -> parser.toValues(value, timeUnit))
                .findFirst()
                .orElseThrow(() -> new NotationParserNotFoundException(value));
    }

}
