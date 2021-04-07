package uk.co.mruoc.exercises.cakefactory;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CakeFactoryRequest {

    private final int numberOfCakes;
    private final int requiredLayers;
    private final int[] starts;
    private final int[] ends;
    private final int[] flavours;

}
