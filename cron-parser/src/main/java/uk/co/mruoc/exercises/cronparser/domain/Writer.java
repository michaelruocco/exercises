package uk.co.mruoc.exercises.cronparser.domain;

public interface Writer {

    void writeOutput(String value);

    void writeError(String value);

}
