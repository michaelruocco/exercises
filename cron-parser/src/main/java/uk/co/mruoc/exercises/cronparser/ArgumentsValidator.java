package uk.co.mruoc.exercises.cronparser;

public class ArgumentsValidator {

    private static final int REQUIRED_ARGUMENTS = 6;
    private static final String USAGE_MESSAGE = "usage: please provide a valid cron expression";

    public void validate(String[] args) {
        if (args.length != REQUIRED_ARGUMENTS) {
            throw new ParserException(toErrorMessage(args));
        }
    }

    private static String toErrorMessage(String[] args) {
        if (args.length == 0) {
            return USAGE_MESSAGE;
        }
        return toInvalidExpressionMessage(args);
    }

    private static String toInvalidExpressionMessage(String[] args) {
        String expression = String.join(" ", args);
        return String.format("%s, invalid cron expression provided %s", USAGE_MESSAGE, expression);
    }

}
