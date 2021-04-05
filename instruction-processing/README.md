# Instruction Processing

This is another interview question I have come across in the past, the problem was
stated as follows:

## Problem Statement

Your challenge is to build a queue for storing instruction messages. Instruction messages are received
as strings in the following format.

```InstructionMessage <InstructionType> <ProductCode> <Quantity> <UOM> <Timestamp>```

The timestamp uses the format ```yyyy-MM-dd'T'HH:mm:ss.SSS'Z'```. Messages end in a newline character and
each field is separated by a space. For example:

```InstructionMessage A MZ89 5678 50 2015-03-05T10:04:56.012Z```

You should implement the following interface for receiving messages.

```java
public interface MessageReceiver {
    void receive(String message);
}
```

This method should build an instance of type ```InstructionMessage```
that can be inserted into a queue of type ```InstructionQueue```.
You must write the ```InstructionMessage``` and the ```InstructionQueue``` classes,
together with any supporting classes required.

Only valid messages should be enqueued. Message validity is determined according to
the rules shown below. Any invalid messages should not be enqueued; instead,
an exception should be thrown.

### Validation

*   InstructionType -> one of A, B, C or D
*   ProductCode -> string of form AB12 (two uppercase letters followed by two digits)
*   Quantity -> integer, 0 < n
*   UOM  -> integer, 0 <= n < 256
*   Timestamp -> unix epoch < t <= current time

The ```InstructionQueue``` class should have the following public methods.

*   ```void enqueue(InstructionMessage message)``` -> adds a message to the queue
*   ```InstructionMessage dequeue()``` -> removes the highest-priority message from the queue and returns it
*   ```InstructionMessage peek()``` -> returns the highest-priority message from the queue, without removing it
*   ```int count()``` -> Returns the number of messages currently on the queue
*   ```boolean isEmpty()``` -> returns true if the queue is empty, false otherwise

### Priority

Messages should be dequeued in priority order. Message priority is based on the priority calculation shown below.

*   A = High
*   B = Medium
*   C or D = Low

Messages with the same priority should be returned in first-in, first-out order.
