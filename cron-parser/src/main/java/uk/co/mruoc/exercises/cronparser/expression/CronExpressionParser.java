package uk.co.mruoc.exercises.cronparser.expression;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.cronparser.expression.notation.ComplexNotationParser;
import uk.co.mruoc.exercises.cronparser.expression.notation.NotationParser;

import java.util.Arrays;
import java.util.Collection;

@RequiredArgsConstructor
public class CronExpressionParser {

    private final Collection<NotationParser> parsers;

    public CronExpressionParser() {
        this(new ComplexNotationParser());
    }

    public CronExpressionParser(NotationParser... parsers) {
        this(Arrays.asList(parsers));
    }

    public CronResult parse(String[] values) {
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
        String rawValues = values[timeUnit.ordinal()];
        String intValues = timeUnit.toIntValues(rawValues);
        return parsers.stream()
                .filter(parser -> parser.appliesTo(intValues))
                .map(parser -> parser.toValues(intValues, timeUnit))
                .findFirst()
                .orElseThrow(() -> new NotationParserNotFoundException(rawValues));
    }

}
