package uk.co.mruoc.exercises.cronparser;

import lombok.Builder;
import uk.co.mruoc.exercises.cronparser.expression.CronExpressionParser;
import uk.co.mruoc.exercises.cronparser.expression.CronResult;
import uk.co.mruoc.exercises.cronparser.expression.CronResultFormatter;

@Builder
public class App {

    @Builder.Default
    private final ArgumentsValidator validator = new ArgumentsValidator();

    @Builder.Default
    private final CronExpressionParser parser = new CronExpressionParser();

    @Builder.Default
    private final CronResultFormatter formatter = new CronResultFormatter();

    @Builder.Default
    private final Writer writer = new SystemWriter();

    public void run(String[] args) {
        try {
            validator.validate(args);
            CronResult result = parser.parse(args);
            print(result);
        } catch (ParserException e) {
            printErrorMessage(e);
        }
    }

    private void print(CronResult result) {
        writer.writeOutput(formatter.format(result));
    }

    private void printErrorMessage(Throwable e) {
        writer.writeError(e.getMessage());
    }

}
