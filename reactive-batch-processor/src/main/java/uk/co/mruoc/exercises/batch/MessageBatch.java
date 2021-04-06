package uk.co.mruoc.exercises.batch;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Data
public class MessageBatch {

    private final Collection<Message> messages;

    public Collection<Message> getMessages() {
        return Collections.unmodifiableCollection(messages);
    }

}
