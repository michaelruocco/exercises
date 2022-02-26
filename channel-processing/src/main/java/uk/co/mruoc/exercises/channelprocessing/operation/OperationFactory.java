package uk.co.mruoc.exercises.channelprocessing.operation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.exercises.channelprocessing.function.MathConstants;
import uk.co.mruoc.exercises.channelprocessing.function.UnsupportedOperatorException;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationFactory {

    private static final char ADD_OPERATOR = '+';
    private static final char SUBTRACT_OPERATOR = '-';
    private static final char MULTIPLY_OPERATOR = '*';
    private static final char DIVIDE_OPERATOR = '/';

    public static Operation operation(char operator) {
        return switch (operator) {
            case ADD_OPERATOR -> add();
            case SUBTRACT_OPERATOR -> subtract();
            case MULTIPLY_OPERATOR -> multiply();
            case DIVIDE_OPERATOR -> divide();
            default -> throw new UnsupportedOperatorException(operator);
        };
    }

    public static Operation add() {
        return new Operation(ADD_OPERATOR, BigDecimal::add);
    }

    public static Operation subtract() {
        return new Operation(SUBTRACT_OPERATOR, BigDecimal::subtract);
    }

    public static Operation multiply() {
        return new Operation(MULTIPLY_OPERATOR, BigDecimal::multiply);
    }

    public static Operation divide() {
        return new Operation(DIVIDE_OPERATOR, (b1, b2) -> b1.divide(b2, MathConstants.CONTEXT));
    }

}
