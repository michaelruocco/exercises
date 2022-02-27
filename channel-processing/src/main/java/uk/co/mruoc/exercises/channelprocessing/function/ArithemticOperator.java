package uk.co.mruoc.exercises.channelprocessing.function;

import com.fathzer.soft.javaluator.Operator;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

import static com.fathzer.soft.javaluator.Operator.Associativity.LEFT;

@EqualsAndHashCode(callSuper = true)
public class ArithemticOperator extends Operator implements BinaryOperator<BigDecimal>  {

    private final BinaryOperator<BigDecimal> function;

    public ArithemticOperator(String symbol, int precedence, BinaryOperator<BigDecimal> function) {
        super(symbol, 2, LEFT, precedence);
        this.function = function;
    }

    @Override
    public BigDecimal apply(BigDecimal b1, BigDecimal b2) {
        return function.apply(b1, b2);
    }
}
