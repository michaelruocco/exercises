package uk.co.mruoc.exercises.channelprocessing.function;

import com.fathzer.soft.javaluator.AbstractEvaluator;
import com.fathzer.soft.javaluator.Operator;
import com.fathzer.soft.javaluator.Parameters;

import java.math.BigDecimal;
import java.util.Iterator;

public class Evaluator extends AbstractEvaluator<BigDecimal> {

    public static final ArithemticOperator SUBTRACT = new ArithemticOperator("-", 1, Operations::subtract);
    public static final ArithemticOperator ADD = new ArithemticOperator("+", 1, Operations::add);
    public static final ArithemticOperator MULTIPLY = new ArithemticOperator("*",  2, Operations::multiply);
    public static final ArithemticOperator DIVIDE = new ArithemticOperator("/",2, Operations::divide);

    public Evaluator() {
        super(buildParameters());
    }

    @Override
    protected BigDecimal evaluate(Operator operator, Iterator<BigDecimal> operands, Object evaluationContext) {
        if (operator instanceof ArithemticOperator arithemticOperator) {
            return arithemticOperator.apply(operands.next(), operands.next());
        }
        return super.evaluate(operator, operands, evaluationContext);
    }

    @Override
    protected BigDecimal toValue(String literal, Object evaluationContext) {
        return new BigDecimal(literal);
    }

    private static Parameters buildParameters() {
        Parameters params = new Parameters();
        params.add(SUBTRACT);
        params.add(ADD);
        params.add(MULTIPLY);
        params.add(DIVIDE);
        return params;
    }

}
