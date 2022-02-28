package uk.co.mruoc.exercises.channelprocessing.function;

public class MetricBFunction extends CompositeFunction {

    public MetricBFunction() {
        super(new CompositeFunction(new Function1(), new Function3()), new Function2());
    }

}
