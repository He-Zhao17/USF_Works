import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class StoreSimulatorTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getNewArrivals() {
        Comparator<Integer> q = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };
        Queue<Integer> a = new PriorityQueue<>(q);
        a.add(5);
        a.add(6);
        a.add(9);
        System.out.println(a.remove());
        System.out.println(a.remove());
        System.out.println(a.remove());
    }

    @Test
    void simulate() {
    }

    @Test
    void main() {
    }
}