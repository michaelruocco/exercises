package uk.co.mruoc.exercises.cronparser;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.cronparser.expression.CronExpressionParser;
import uk.co.mruoc.exercises.cronparser.expression.CronResult;
import uk.co.mruoc.exercises.cronparser.expression.CronResultFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemErrAndOut;

class AppTest {

    private final ArgumentsValidator argumentsValidator = mock(ArgumentsValidator.class);
    private final CronExpressionParser expressionParser = mock(CronExpressionParser.class);
    private final CronResultFormatter formatter = mock(CronResultFormatter.class);

    private final App app = App.builder()
            .validator(argumentsValidator)
            .parser(expressionParser)
            .formatter(formatter)
            .build();

    @Test
    void shouldPrintUsageMessageIfInvalidExpressionPassed() throws Exception {
        String expectedMessage = "usage: please provide a cron expression as an argument";
        String[] args = new String[0];
        doThrow(new ParserException(expectedMessage)).when(argumentsValidator).validate(args);

        String output = tapSystemErrAndOut(() -> app.run(args));

        assertThat(output).contains(expectedMessage);
    }

    @Test
    void shouldPrintFormattedResultsIfValidExpressionPassed() throws Exception {
        String[] expression = new String[0];
        CronResult result = givenExpressionParsedToResult(expression);
        String expectedFormattedResult = givenExpectedFormattedResult(result);

        String output = tapSystemErrAndOut(() -> app.run(expression));

        assertThat(output).contains(expectedFormattedResult);
    }

    private CronResult givenExpressionParsedToResult(String[] expression) {
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
