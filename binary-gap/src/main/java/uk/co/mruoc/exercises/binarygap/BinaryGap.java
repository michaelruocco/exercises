package uk.co.mruoc.exercises.binarygap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BinaryGap {

    public int calculate(int value) {
        String binary = Integer.toBinaryString(value);
        Collection<Integer> gaps = findGaps(binary);
        return Collections.max(gaps);
    }

    private static Collection<Integer> findGaps(String binary) {
        int previous = 0;
        int next;
        Collection<Integer> gaps = new ArrayList<>();
        do {
            next = binary.indexOf("1", previous);
            if (next == -1) {
                return gaps;
            }
            gaps.add(next - previous);
            previous = next + 1;
        } while (true);
    }

}
