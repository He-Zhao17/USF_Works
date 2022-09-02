import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IntMatrixTest {
    IntMatrix test = new IntMatrix(3,2);

    @BeforeEach
    void setUp() {
        int[][] testint = new int[3][2];
        int k = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                testint[i][j] = k;
                k++;
            }
        }
        test.setMatrix(testint);


    }

    @Test
    void setMatrix() {
        int[][] testint = new int[3][2];
        int k = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                testint[i][j] = k;
                k++;
            }
        }
        test.setMatrix(testint);
        System.out.println(test);
    }

    @Test
    void getMatrix() {
        System.out.println(test.getMatrix());
    }

    @Test
    void get() {
        System.out.println(test.get(1,1));
    }

    @Test
    void set() {
        test.set(1,1,2);
        System.out.println(test);
    }

    @Test
    void testToString() {
        System.out.println(test);
    }

    @Test
    void getRow() {
        System.out.println(Arrays.toString(test.getRow(1)));
    }

    @Test
    void getCol() {
        System.out.println(Arrays.toString(test.getCol(1)));
    }

    @Test
    void add() {
        IntMatrix testadd = new IntMatrix(3,2);
        testadd.setMatrix(test.getMatrix());
        System.out.println(test.add(testadd));

    }

    @Test
    void multiply() {
        System.out.println(test.multiply(2));
    }

    @Test
    void testMultiply() {
        IntMatrix testadd = new IntMatrix(2,3);
        testadd.setMatrix(test.transpose().getMatrix());
        System.out.println(test.multiply(testadd));
    }

    @Test
    void transpose() {
    }
}