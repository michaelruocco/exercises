package uk.co.mruoc.exercises.challenge;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.stream.IntStream;

public class Solution {

    private Solution() {
        //utility class
    }

    public static int solution(int numberOfCakes, int requiredLayers, int[] starts, int[] ends, int[] flavours) {
        int wellPrepared = IntStream.rangeClosed(1, requiredLayers).sum();
        return (int) IntStream.rangeClosed(1, numberOfCakes)
                .filter(cake -> isWellPrepared(cake, requiredLayers, starts, ends, flavours, wellPrepared))
                .count();
    }

    private static boolean isWellPrepared(int cake, int requiredLayers, int[] starts, int[] ends, int[] flavours, int wellPrepared) {
        Deque<Integer> layers = new ArrayDeque<>();
        for (int i = 0; i < flavours.length; i++) {
            if (cake >= starts[i] && cake <= ends[i]) {
                int flavour = flavours[i];
                if (Optional.ofNullable(layers.peek()).orElse(0) == flavour - 1) {
                    layers.push(flavour);
                } else {
                    return false;
                }
            }
        }

        int sumOfLayers = layers.stream().mapToInt(Integer::intValue).sum();
        return layers.size() == requiredLayers && sumOfLayers == wellPrepared;
    }

}
