package uk.co.mruoc.exercises.cronparser;

import lombok.Builder;
import uk.co.mruoc.exercises.cronparser.expression.CronExpressionParser;
import uk.co.mruoc.exercises.cronparser.expression.CronResult;
import uk.co.mruoc.exercises.cronparser.expression.CronResultFormatter;

@Builder
public class App {

    @Builder.Default
    private final ArgumentParser argumentParser = new ArgumentParser();

    @Builder.Default
    private final CronExpressionParser expressionParser = new CronExpressionParser();

    @Builder.Default
    private final CronResultFormatter formatter = new CronResultFormatter();

    public void run(String[] args) {
        argumentParser.toExpression(args)
                .map(expressionParser::parse)
                .ifPresentOrElse(this::print, App::printUsage);
    }

    private void print(CronResult result) {
        System.out.println(formatter.format(result));
    }

    private static void printUsage() {
        System.err.println("usage: please provide a cron expression as an argument");
    }

}
