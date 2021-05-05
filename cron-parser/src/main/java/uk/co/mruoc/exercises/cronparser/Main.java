package uk.co.mruoc.exercises.cronparser;

import uk.co.mruoc.exercises.cronparser.domain.App;

public class Main {

    private static final App APP = App.builder().build();

    private Main() {
        // main class
    }

    public static void main(String[] args) {
        APP.run(args);
    }

}
