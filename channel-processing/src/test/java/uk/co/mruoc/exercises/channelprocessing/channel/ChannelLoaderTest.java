package uk.co.mruoc.exercises.channelprocessing.channel;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ChannelLoaderTest {

    private static final String TEST_CHANNELS_PATH = "test-channels.txt";

    private final ChannelLoader loader = new ChannelLoader();

    @Test
    void shouldLoadAllChannels() {
        Channels channels = loader.load(TEST_CHANNELS_PATH);

        assertThat(channels.size()).isEqualTo(3);
    }

    @Test
    void shouldLoadTestChannelX() {
        Channels channels = loader.load(TEST_CHANNELS_PATH);

        assertThat(channels.getValues('X')).containsExactly(
                new BigDecimal("1.5"),
                new BigDecimal("1.75"),
                new BigDecimal("2.0")
        );
    }

    @Test
    void shouldLoadTestChannelY() {
        Channels channels = loader.load(TEST_CHANNELS_PATH);

        assertThat(channels.getValues('Y')).containsExactly(
                new BigDecimal("2.35"),
                new BigDecimal("2.735")
        );
    }

    @Test
    void shouldLoadTestChannelZ() {
        String path = "test-channels.txt";

        Channels channels = loader.load(path);

        assertThat(channels.getValues('Z')).containsExactly(
                new BigDecimal("3"),
                new BigDecimal("4"),
                new BigDecimal("5")
        );
    }

}