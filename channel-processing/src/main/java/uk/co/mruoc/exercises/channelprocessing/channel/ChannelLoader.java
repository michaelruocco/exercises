package uk.co.mruoc.exercises.channelprocessing.channel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Flux;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.file.FileLoader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor
@Slf4j
public class ChannelLoader {

    private final UnaryOperator<String> contentLoader;

    public ChannelLoader() {
        this(FileLoader::loadContentFromClasspath);
    }

    public Channels load(String path) {
        String content = contentLoader.apply(path);
        return new Channels(toArguments(content));
    }

    private static Flux<Variables> toArguments(String content) {
        List<Variables> collection = new ArrayList<>();
        Flux.fromArray(content.split(System.lineSeparator()))
                .map(StringUtils::deleteWhitespace)
                .map(line -> line.split(","))
                .subscribe(line -> populate(line, collection));
        return Flux.fromIterable(collection);
    }

    private static void populate(String[] line, List<Variables> collection) {
        char id = line[0].trim().charAt(0);
        log.debug("populating channel {} with {} values", id, line.length - 1);
        for (int i = 0; i < line.length - 1; i++) {
            BigDecimal value = new BigDecimal(line[i + 1].trim());
            if (i < collection.size()) {
                Variables variables = collection.get(i);
                collection.set(i, variables.set(id, value));
            } else {
                collection.add(new Variables(id, value));
            }
        }
        log.debug("argument collection size {} after populating channel {}", collection.size(), id);
    }
}
