package uk.co.mruoc.exercises.channelprocessing.channel;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Collection;

@Builder
@Data
public class Channel {

    private final char id;
    private final Collection<BigDecimal> values;

    public int size() {
        return values.size();
    }

}
