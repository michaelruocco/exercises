package uk.co.mruoc.exercises.channelprocessing.channel;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Flux;
import uk.co.mruoc.exercises.channelprocessing.Arguments;
import uk.co.mruoc.file.content.ContentLoader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ChannelLoader {

    public Channels load(String path) {
        String content = ContentLoader.loadContentFromClasspath(path);
        return new Channels(toArguments(content));
    }

    private static Flux<Arguments> toArguments(String content) {
        List<Arguments> collection = new ArrayList<>();
        Flux.fromArray(content.split(System.lineSeparator()))
                .map(StringUtils::deleteWhitespace)
                .map(line -> line.split(","))
                .subscribe(line -> populate(line, collection));
        return Flux.fromIterable(collection);
    }

    private static void populate(String[] line, List<Arguments> collection) {
        char id = line[0].trim().charAt(0);
        log.debug("populating channel {} with {} values", id, line.length - 1);
        for (int i = 0; i < line.length - 1; i++) {
            BigDecimal value = new BigDecimal(line[i + 1].trim());
            if (i < collection.size()) {
                collection.get(i).set(id, value);
            } else {
                collection.add(new Arguments(id, value));
            }
        }
        log.debug("argument collection size {} after populating channel {}", collection.size(), id);
    }

}
