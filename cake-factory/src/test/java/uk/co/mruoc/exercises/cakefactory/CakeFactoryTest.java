package uk.co.mruoc.exercises.cakefactory;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class CakeFactoryTest {

    private final CakeFactory factory = new CakeFactory();

    @Test
    void test1() {
        CakeFactoryRequest request = CakeFactoryRequest.builder()
                .numberOfCakes(5)
                .requiredLayers(3)
                .starts(new int[]{1, 1, 4, 1, 4})
                .ends(new int[]{5, 2, 5, 5, 4})
                .flavours(new int[]{1, 2, 2, 3, 3})
                .build();

        int result = factory.calculateWellPrepared(request);

        assertThat(result).isEqualTo(3);
    }

    @Test
    void test2() {
        CakeFactoryRequest request = CakeFactoryRequest.builder()
                .numberOfCakes(6)
                .requiredLayers(4)
                .starts(new int[]{1, 2, 1, 1})
                .ends(new int[]{3, 3, 6, 6})
                .flavours(new int[]{1, 2, 3, 4})
                .build();

        int result = factory.calculateWellPrepared(request);

        assertThat(result).isEqualTo(2);
    }

    @Test
    void test3() {
        CakeFactoryRequest request = CakeFactoryRequest.builder()
                .numberOfCakes(3)
                .requiredLayers(2)
                .starts(new int[]{1, 3, 3, 1, 1})
                .ends(new int[]{2, 3, 3, 1, 2})
                .flavours(new int[]{1, 2, 1, 2, 2})
                .build();

        int result = factory.calculateWellPrepared(request);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void test4() {
        CakeFactoryRequest request = CakeFactoryRequest.builder()
                .numberOfCakes(5)
                .requiredLayers(2)
                .starts(new int[]{1, 1, 2})
                .ends(new int[]{5, 5, 3})
                .flavours(new int[]{1, 2, 1})
                .build();

        int result = factory.calculateWellPrepared(request);

        assertThat(result).isEqualTo(3);
    }

}
