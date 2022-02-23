package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.MathContext;

import static java.math.RoundingMode.HALF_EVEN;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MathConstants {

    public static final MathContext CONTEXT = new MathContext(21, HALF_EVEN);

}
