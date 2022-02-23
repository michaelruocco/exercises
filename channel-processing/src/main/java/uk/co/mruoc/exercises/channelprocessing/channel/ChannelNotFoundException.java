package uk.co.mruoc.exercises.channelprocessing.channel;

public class ChannelNotFoundException extends RuntimeException {

    public ChannelNotFoundException(char id) {
        super(Character.toString(id));
    }

}
