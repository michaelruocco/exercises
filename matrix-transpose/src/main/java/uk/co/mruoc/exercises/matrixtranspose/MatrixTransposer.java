package uk.co.mruoc.exercises.matrixtranspose;

public class MatrixTransposer {

    public void transpose(int[][] matrix) {
        validate(matrix);
        int size = matrix.length;
        for (var x = 0; x < size; x++) {
            for (var y = 0; y < x; y++) {
                int temp = matrix[x][y];
                matrix[x][y] = matrix[y][x];
                matrix[y][x] = temp;
            }
        }
    }

    private void validate(int[][] matrix) {
        int size = matrix.length;
        for (int[] column : matrix) {
            if (column.length != size) {
                throw new MatrixNotSquareException();
            }
        }
    }

}
