package uk.co.mruoc.exercises.cronparser.expression.notation;

public class ParserNotFoundException extends RuntimeException {

    public ParserNotFoundException(String value) {
        super(String.format("parser not found for value %s", value));
    }
}
