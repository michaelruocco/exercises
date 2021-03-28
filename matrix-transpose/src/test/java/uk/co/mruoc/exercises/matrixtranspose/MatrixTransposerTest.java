package uk.co.mruoc.exercises.matrixtranspose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class MatrixTransposerTest {

    private final MatrixTransposer transposer = new MatrixTransposer();

    @Test
    void shouldTranspose2By2Matrix() {
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };

        transposer.transpose(matrix);

        int[][] expected = {
                {1, 3},
                {2, 4}
        };
        assertThat(matrix).isDeepEqualTo(expected);
    }

    @Test
    void shouldTranspose3By3Matrix() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        transposer.transpose(matrix);

        int[][] expected = {
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9}
        };
        assertThat(matrix).isDeepEqualTo(expected);
    }

    @Test
    void shouldThrowExceptionIfMatrixIsNotSquareWithXGreaterThanY() {
        int[][] matrix = new int[1][2];

        Throwable error = catchThrowable(() -> transposer.transpose(matrix));

        assertThat(error).isInstanceOf(MatrixNotSquareException.class);
    }

    @Test
    void shouldThrowExceptionIfMatrixIsNotSquareWithYGreaterThanX() {
        int[][] matrix = new int[2][1];

        Throwable error = catchThrowable(() -> transposer.transpose(matrix));

        assertThat(error).isInstanceOf(MatrixNotSquareException.class);
    }
}
