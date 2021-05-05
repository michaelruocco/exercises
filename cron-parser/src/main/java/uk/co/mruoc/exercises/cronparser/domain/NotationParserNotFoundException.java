package uk.co.mruoc.exercises.cronparser.domain;

import uk.co.mruoc.exercises.cronparser.ParserException;

public class NotationParserNotFoundException extends ParserException {

    public NotationParserNotFoundException(String value) {
        super(String.format("notation parser not found for value %s", value));
    }

}
