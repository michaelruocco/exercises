package uk.co.mruoc.exercises.challenge;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {

    private Solution() {
        //utility class
    }

    public static int solution(int numberOfCakes, int requiredLayers, int[] start, int[] end, int[] flavours) {
        Cake[] cakes = setupCakes(numberOfCakes, requiredLayers);
        for (int i = 0; i < flavours.length; i++) {
            process(cakes, start[i], end[i], flavours[i]);
        }
        return (int) Arrays.stream(cakes)
                .filter(cake -> cake.isWellFormed(requiredLayers))
                .count();
    }

    private static Cake[] setupCakes(int numberOfCakes, int requiredLayers) {
        return IntStream.range(0, numberOfCakes)
                .mapToObj(i -> new Cake(requiredLayers))
                .toArray(Cake[]::new);
    }

    private static void process(Cake[] cakes, int start, int end, int flavour) {
        for (int i = start - 1; i < end; i++) {
            cakes[i].add(flavour);
        }
    }

    private static class Cake {

        private final int[] layers;
        private int index;
        private boolean overflowed;

        public Cake(int requiredLayers) {
            layers = new int[requiredLayers];
        }

        public void add(int flavour) {
            if (index >= layers.length) {
                overflowed = true;
                return;
            }
            layers[index] = flavour;
            index++;
        }

        public boolean isWellFormed(int requiredLayers) {
            if (overflowed) {
                return false;
            }
            if (layers.length != requiredLayers) {
                return false;
            }
            for (int i = 0; i < layers.length; i++) {
                if (layers[i] != i + 1) {
                    return false;
                }
            }
            return true;
        }
    }

}
