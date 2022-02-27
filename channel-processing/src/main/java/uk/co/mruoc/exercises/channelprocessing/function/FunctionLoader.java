package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Flux;
import uk.co.mruoc.exercises.channelprocessing.function.spec.FunctionSpec;
import uk.co.mruoc.exercises.channelprocessing.function.spec.InputSpec;
import uk.co.mruoc.exercises.channelprocessing.function.spec.InputSpecFactory;
import uk.co.mruoc.file.content.ContentLoader;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.UnaryOperator;

@Slf4j
@RequiredArgsConstructor
public class FunctionLoader {

    private final UnaryOperator<String> contentLoader;
    private final InputSpecFactory inputSpecFactory;

    public FunctionLoader() {
        this(ContentLoader::loadContentFromClasspath);
    }

    public FunctionLoader(UnaryOperator<String> contentLoader) {
        this(contentLoader, new InputSpecFactory());
    }

    public ChannelFunction load(String path) {
        String content = contentLoader.apply(path);
        Collection<ChannelFunction> functions = Flux.fromArray(content.split(System.lineSeparator()))
                .map(StringUtils::deleteWhitespace)
                .map(line -> line.split(","))
                .map(this::toFunction)
                .collectList()
                .block();
        return new CompositeFunction(functions);
    }

    private ChannelFunction toFunction(String[] line) {
        log.debug("building function from function file line {}", Arrays.toString(line));
        String type = line[0];
        String[] specs = Arrays.copyOfRange(line, 1, line.length);
        return switch (type) {
            case "combine" -> toCombineFunction(specs);
            case "single" -> toSingleFunction(specs[0]);
            default -> throw new UnsupportedSpecTypeException(type);
        };
    }

    private ChannelFunction toCombineFunction(String[] specs) {
        return new CombineFunction(
                toSingleFunction(specs[0]),
                toSingleFunction(specs[1])
        );
    }

    private ChannelFunction toSingleFunction(String spec) {
        String[] parts = spec.split("~");
        String type = parts[0];
        return switch (type) {
            case "arithmetic" -> toArithmeticFunction(parts);
            case "mean" -> toMeanFunction(parts);
            default -> throw new UnsupportedFunctionTypeException(type);
        };
    }

    private ChannelFunction toArithmeticFunction(String[] spec) {
        log.debug("building arithmetic function {}", Arrays.toString(spec));
        String expression = spec[1];
        Collection<InputSpec> inputSpecs = Flux.fromArray(Arrays.copyOfRange(spec, 2, spec.length))
                .map(inputSpecFactory::build)
                .collectList()
                .block();
        return new ArithmeticFunction(new FunctionSpec(expression, inputSpecs));
    }

    private ChannelFunction toMeanFunction(String[] spec) {
        String expression = spec[1];
        char target = expression.split("=")[0].charAt(0);
        return new MeanFunction(inputSpecFactory.build(spec[2]), target);
    }

}
