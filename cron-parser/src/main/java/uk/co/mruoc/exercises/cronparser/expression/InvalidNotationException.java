package uk.co.mruoc.exercises.cronparser.expression;

import uk.co.mruoc.exercises.cronparser.ParserException;

public class InvalidNotationException extends ParserException {

    public InvalidNotationException(String value, Throwable cause) {
        super(value, cause);
    }

}
