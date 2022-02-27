package uk.co.mruoc.exercises.channelprocessing.function.spec;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class InputSpecFactory {

    public InputSpec build(String spec) {
        log.debug("building input spec {}", spec);
        String[] parts = spec.split("\\(");
        String type = parts[0];
        String value = parts[1].replace(")", "");
        char id = value.charAt(0);
        return switch (type) {
            case "channel" -> channel(id);
            case "metric" -> metric(id);
            case "param" -> parameter(id);
            case "constant" -> constant(value);
            default -> throw new UnsupportedInputTypeException(type);
        };
    }

    public static InputSpec channel(char id) {
        return new VariableInputSpec(id);
    }

    public static InputSpec metric(char id) {
        return new VariableInputSpec(id);
    }

    public static InputSpec parameter(char id) {
        return new ParameterInputSpec(id);
    }

    public static InputSpec constant(String value) {
        return constant(new BigDecimal(value));
    }

    public static InputSpec constant(double value) {
        return constant(BigDecimal.valueOf(value));
    }

    public static InputSpec constant(BigDecimal value) {
        return new ConstantInputSpec(value);
    }

}
