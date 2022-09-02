import java.util.Arrays;

/** An IntMatrix is a wrapper for a 2D matrix.
 *
 */
public class IntMatrix {

    private int[][] matrix;

    public IntMatrix(int rows, int cols) {
        matrix = new int[rows][cols];
    }

    public void setMatrix(int[][] setmatrix) {
        matrix = setmatrix;
    }
    public int[][] getMatrix(){
        return matrix;
    }

    /**
     * Return the element at [row, col]. Throw an IllegalArgumentException if the input is invalid.
     * @param row
     * @param col
     * @return
     */
    public int get(int row, int col) {
        if (row > 0 && row < matrix.length && col > 0 && col < matrix[0].length) {
            return matrix[row - 1][col - 1];
        } else {
            throw new IllegalArgumentException("Error. Please input the correct row and col.");
        }
    }

    /**
     * Set the element at [row,col] to be value. Throw an IllegalArgumentException if the input is invalid.
     * @param row
     * @param col
     * @param value
     */
    public void set(int row, int col, int value) {
        if (row < 1 || row > matrix.length || col < 1 || col > matrix[0].length) {
            throw new IllegalArgumentException("Error. Please input correctly.");
        } else {
            matrix[row - 1][col - 1] = value;
        }

    }

    @Override
    public String toString() {
        return "IntMatrix{" +
                "matrix=" + Arrays.deepToString(matrix) +
                '}';
    }
    /**  return an array of integers containing the elements of row r.
     * Throw an IllegalArgumentException if the input is invalid.
     * @param row
     * @param col
     * @return
     */
    public int[] getRow(int r) {
        if (r < 1 || r > matrix.length) {
            throw new IllegalArgumentException("Error. Please input correctly.");
        } else {
            return matrix[r-1];
        }

    }
    /**  return an array of integers containing the elements of column c.
     * Throw an IllegalArgumentException if the input is invalid.
     * @param row
     * @param col
     * @return
     */

    public int[] getCol(int c) {
        if (c < 1 || c > matrix[0].length) {
            throw new IllegalArgumentException("Error. Please input correctly.");
        } else {
            int[] k = new int[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                k[i] = matrix[i][c-1];
            }
            return k;
        }
    }

    /**
     * Return a new IntMatrix that is the sum of this matrix plus otherMatrix.
     * Recall that:
     * | a b|  +  |c  d| = |a+c  b+d|
     * | e f|     |g  h|   |e+g  f+h|
     * Throw an IllegalArgumentException if the input is invalid.
     * @param otherMatrix
     * @return
     */

    public IntMatrix add(IntMatrix otherMatrix) {
        if (otherMatrix.matrix.length == this.matrix.length && otherMatrix.matrix[0].length == this.matrix[0].length) {
            IntMatrix k = new IntMatrix(this.matrix.length, this.matrix[0].length);
            for (int i = 0; i < this.matrix.length; i++) {
                for (int j = 0; j < this.matrix[0].length; j++) {
                    k.matrix[i][j] = this.matrix[i][j] + otherMatrix.matrix[i][j];
                }
            }
            return k;
        } else {
            throw new IllegalArgumentException("Error. Please input a same-size matrix.");
        }

    }

    /**
     * Return a new IntMatrix that is the product of this matrix plus a scalar.
     * Recall that:
     * | a b|  * c = |ac  bc|
     * | e f|        |ec  fc|
     * Throw an IllegalArgumentException if the input is invalid.
     * @param scalar
     * @return
     */

    public IntMatrix multiply(int scalar) {
        IntMatrix k = new IntMatrix(this.matrix.length, this.matrix[0].length);
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                k.matrix[i][j] = this.matrix[i][j] * scalar;
            }
        }
        return k;
    }

    /**
     * Return a new IntMatrix that is the product of this matrix and otherMatrix.
     * Recall that:
     * | a b c|  *  |g  h| = |ag+bi+ck  ah+bj+cl|
     * | d e f|     |i  j|   |dg+ei+fk  dh+ej+fl|
     *              |k  l|
     * Throw an IllegalArgumentException if the input is invalid.
     * @param otherMatrix
     * @return
     */
    public IntMatrix multiply(IntMatrix otherMatrix) {
        if (otherMatrix.matrix.length == this.matrix[0].length) {
            IntMatrix k = new IntMatrix(this.matrix.length, otherMatrix.matrix[0].length);
            for (int i = 0; i < this.matrix.length; i++) {
                for (int j = 0; j < otherMatrix.matrix[0].length; j++) {
                    int mid = 0;
                    for (int l = 0; l < this.matrix[0].length; l++) {
                        mid += this.matrix[i][l] * otherMatrix.matrix[l][j];
                    }
                    k.matrix[i][j] = mid;
                }
            }
            return k;

        } else {
            throw new IllegalArgumentException("Error. Please input a valid matrix.");
        }
    }

    /**
     * Return a new IntMatrix that is the transpose of this matrix.
     * Recall that the transpose of :
     * | a b|  is  |a  c|
     * | c d|      |b  d|
     * @return
     */
    public IntMatrix transpose() {
        IntMatrix k = new IntMatrix(matrix[0].length, matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                k.matrix[j][i] = matrix[i][j];
            }
        }
        return k;
    }

}



