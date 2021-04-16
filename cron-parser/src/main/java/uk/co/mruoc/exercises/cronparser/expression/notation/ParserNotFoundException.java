package uk.co.mruoc.exercises.cronparser.expression.notation;

public class ParserNotFoundException extends RuntimeException {

    public ParserNotFoundException(String value) {
        super(String.format("no parser found for value %s", value));
    }
}
