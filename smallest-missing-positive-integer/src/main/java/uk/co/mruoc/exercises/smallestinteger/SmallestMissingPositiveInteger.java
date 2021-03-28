package uk.co.mruoc.exercises.smallestinteger;

import java.util.Arrays;

public class SmallestMissingPositiveInteger {

    public int find(int... ints) {
        int[] positiveInts = removeNegatives(ints);
        return findLowestMissingPositiveInteger(positiveInts);
    }

    private static int[] removeNegatives(int[] ints) {
        return Arrays.stream(ints)
                .filter(i -> i > 0)
                .toArray();
    }

    private static int findLowestMissingPositiveInteger(int[] positiveInts) {
        if (isEmpty(positiveInts) || hasSingleValueGreaterThanOne(positiveInts)) {
            return 1;
        }
        boolean[] isPresent = toIsPresentFlags(positiveInts);
        for (int i = 1; i < isPresent.length; i++) {
            if (!isPresent[i]) {
                return i;
            }
        }
        return isPresent.length + 1;
    }

    private static boolean isEmpty(int[] positiveInts) {
        return positiveInts.length == 0;
    }

    private static boolean hasSingleValueGreaterThanOne(int[] positiveInts) {
        return positiveInts.length == 1 && positiveInts[0] > 1;
    }

    private static boolean[] toIsPresentFlags(int[] positiveInts) {
        boolean[] isPresent = new boolean[positiveInts.length];
        for (int number : positiveInts) {
            if (number < isPresent.length) {
                isPresent[number] = true;
            }
        }
        return isPresent;
    }

}
