package uk.co.mruoc.exercises.batch;

public interface MessageHandler {

    enum Result {
        SUCCESS,
        FAILURE
    }

    Result handleMessage(Message message);

}
