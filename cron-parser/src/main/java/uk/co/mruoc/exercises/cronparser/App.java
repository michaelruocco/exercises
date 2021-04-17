package uk.co.mruoc.exercises.cronparser;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.cronparser.expression.CronExpressionParser;
import uk.co.mruoc.exercises.cronparser.expression.CronResult;
import uk.co.mruoc.exercises.cronparser.expression.CronResultFormatter;

@Builder
@Slf4j
public class App {

    @Builder.Default
    private final ArgumentParser argumentParser = new ArgumentParser();

    @Builder.Default
    private final CronExpressionParser expressionParser = new CronExpressionParser();

    @Builder.Default
    private final CronResultFormatter formatter = new CronResultFormatter();

    public void run(String[] args) {
        try {
            argumentParser.toExpression(args)
                    .map(expressionParser::parse)
                    .ifPresentOrElse(this::print, App::printUsage);
        } catch (ParserException e) {
            printErrorMessage(e);
        }
    }

    private void print(CronResult result) {
        log.info(formatter.format(result));
    }

    private static void printUsage() {
        log.error("usage: please provide a cron expression as an argument");
    }

    private static void printErrorMessage(Throwable e) {
        log.error(e.getMessage());
    }

}
