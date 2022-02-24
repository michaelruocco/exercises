package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.extern.slf4j.Slf4j;

import static java.math.BigDecimal.ONE;

@Slf4j
public class Function3 extends ConstantDividendDivide  {

    public Function3() {
        super(ONE, 'X', 'A');
    }

}
