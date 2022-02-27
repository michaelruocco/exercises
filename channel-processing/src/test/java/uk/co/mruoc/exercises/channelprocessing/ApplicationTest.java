package uk.co.mruoc.exercises.channelprocessing;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemErr;

class ApplicationTest {

    @Test
    void shouldRunApplication() throws Exception {
        String[] args = new String[]{
                "-c", toTestDirectory("test-channels.txt"),
                "-p", toTestDirectory("test-parameters.txt"),
                "-f", toTestDirectory("test-functions.csv"),
                "-o", "C"
        };

        String[] output = tapSystemErr(() -> Application.main(args)).split(System.lineSeparator());
        String lastLine = output[output.length-1];
        assertThat(lastLine).endsWith("C value is 3.01801801801801801801");
    }

    private static String toTestDirectory(String fileName) {
        return String.format("./src/test/resources/%s", fileName);
    }

    private static String getCurrentDirectory() {
        return new File(".").getAbsolutePath();
    }

}
