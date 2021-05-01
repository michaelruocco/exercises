package uk.co.mruoc.exercises.cakefactory;

public class CakeFactory {

    public int calculateWellPrepared(CakeFactoryRequest request) {
        var lastLayers = new int[request.getNumberOfCakes()];
        int[] flavours = request.getFlavours();
        int[] starts = request.getStarts();
        int[] ends = request.getEnds();
        for (var f = 0; f < flavours.length; f++) {
            for (int c = starts[f]; c <= ends[f]; c++) {
                int cake = c - 1;
                int flavour = flavours[f];
                if ((lastLayers[cake] + 1) == flavour) {
                    lastLayers[cake] = flavour;
                } else {
                    lastLayers[cake] = -1;
                }
            }
        }
        var count = 0;
        for (int lastLayer : lastLayers) {
            if (lastLayer == request.getRequiredLayers()) {
                count++;
            }
        }
        return count;
    }

}
