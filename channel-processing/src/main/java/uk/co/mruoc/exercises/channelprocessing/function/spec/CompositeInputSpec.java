package uk.co.mruoc.exercises.channelprocessing.function.spec;

import com.fathzer.soft.javaluator.StaticVariableSet;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

@RequiredArgsConstructor
@Data
public class CompositeInputSpec {

    private final Collection<InputSpec> specs;

    public CompositeInputSpec(InputSpec... specs) {
        this(Arrays.asList(specs));
    }

    public StaticVariableSet<BigDecimal> select(Parameters parameters, Variables variables) {
        StaticVariableSet<BigDecimal> set = new StaticVariableSet<>();
        specs.forEach(spec -> set.set(spec.getId(), spec.select(parameters, variables)));
        return set;
    }

}
