package uk.co.mruoc.exercises.challenge;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void test1() {
        int N = 5;
        int K = 3;

        int[] A = {1, 1, 4, 1, 4};
        int[] B = {5, 2, 5, 5, 4};
        int[] C = {1, 2, 2, 3, 3};

        int result = Solution.solution(N, K, A, B, C);

        assertThat(result).isEqualTo(3);
    }

    @Test
    void test2() {
        int N = 6;
        int K = 4;

        int[] A = {1, 2, 1, 1};
        int[] B = {3, 3, 6, 6};
        int[] C = {1, 2, 3, 4};

        int result = Solution.solution(N, K, A, B, C);

        assertThat(result).isEqualTo(2);
    }

    @Test
    void test3() {
        int N = 3;
        int K = 2;

        int[] A = {1, 3, 3, 1, 1};
        int[] B = {2, 3, 3, 1, 2};
        int[] C = {1, 2, 1, 2, 2};

        int result = Solution.solution(N, K, A, B, C);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void test4() {
        int N = 5;
        int K = 2;

        int[] A = {1, 1, 2};
        int[] B = {5, 5, 3};
        int[] C = {1, 2, 1};

        int result = Solution.solution(N, K, A, B, C);

        assertThat(result).isEqualTo(3);
    }

}
