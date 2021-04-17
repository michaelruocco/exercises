package uk.co.mruoc.exercises.cronparser;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.cronparser.expression.CronExpressionParser;
import uk.co.mruoc.exercises.cronparser.expression.CronResult;
import uk.co.mruoc.exercises.cronparser.expression.CronResultFormatter;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemErrAndOut;

class AppTest {

    private final ArgumentParser argumentParser = mock(ArgumentParser.class);
    private final CronExpressionParser expressionParser = mock(CronExpressionParser.class);
    private final CronResultFormatter formatter = mock(CronResultFormatter.class);

    private final App app = App.builder()
            .argumentParser(argumentParser)
            .expressionParser(expressionParser)
            .formatter(formatter)
            .build();

    @Test
    void shouldPrintUsageMessageIfInvalidExpressionPassed() throws Exception {
        String[] args = new String[0];
        given(argumentParser.toExpression(args)).willReturn(Optional.empty());

        String output = tapSystemErrAndOut(() -> app.run(args));

        assertThat(output).contains("usage: please provide a cron expression as an argument");
    }

    @Test
    void shouldPrintFormattedResultsIfValidExpressionPassed() throws Exception {
        String[] args = new String[0];
        String expression = givenArgsParsedToExpression(args);
        CronResult result = givenExpressionParsedToResult(expression);
        String expectedFormattedResult = givenExpectedFormattedResult(result);

        String output = tapSystemErrAndOut(() -> app.run(args));

        assertThat(output).contains(expectedFormattedResult);
    }

    private String givenArgsParsedToExpression(String[] args) {
        String expression = "expression";
        given(argumentParser.toExpression(args)).willReturn(Optional.of(expression));
        return expression;
    }

    private CronResult givenExpressionParsedToResult(String expression) {
        CronResult result = mock(CronResult.class);
        given(expressionParser.parse(expression)).willReturn(result);
        return result;
    }

    private String givenExpectedFormattedResult(CronResult result) {
        String expectedFormattedResult = "formatted-result";
        given(formatter.format(result)).willReturn(expectedFormattedResult);
        return expectedFormattedResult;
    }

}
