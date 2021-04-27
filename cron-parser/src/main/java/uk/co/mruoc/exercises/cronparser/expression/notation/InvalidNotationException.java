package uk.co.mruoc.exercises.cronparser.expression.notation;

import uk.co.mruoc.exercises.cronparser.ParserException;

public class InvalidNotationException extends ParserException {

    public InvalidNotationException(String value) {
        super(value);
    }

    public InvalidNotationException(String value, Throwable cause) {
        super(value, cause);
    }

}
