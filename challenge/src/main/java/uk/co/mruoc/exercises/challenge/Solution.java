package uk.co.mruoc.exercises.challenge;

public class Solution {

    private Solution() {
        //utility class
    }

    public static int solution(int numberOfCakes, int requiredLayers, int[] starts, int[] ends, int[] flavours) {
        int[] lastLayers = new int[numberOfCakes];
        for (int f = 0; f < flavours.length; f++) {
            for (int c = starts[f]; c <= ends[f]; c++) {
                int index = c - 1;
                int flavour = flavours[f];
                if ((lastLayers[index] + 1) == flavour) {
                    lastLayers[index] = flavour;
                } else {
                    lastLayers[index] = -1;
                }
            }
        }
        int count = 0;
        for (int lastLayer : lastLayers) {
            if (lastLayer == requiredLayers) {
                count++;
            }
        }
        return count;
    }

}
