package fr.lernejo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SampleTest {
    public Sample sTest = new Sample();

    @Test
    void opAddTest() {
        Assertions.assertEquals(sTest.op(Sample.Operation.ADD, 1, 1), 2);
    }

    @Test
    void factExceptionThrown() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> sTest.fact(-1));
    }

    @Test
    void opMultiTest() {
        Assertions.assertEquals(sTest.op(Sample.Operation.MULT, 2, 3), 6);
    }

    @Test
    void factTest() {
        Assertions.assertEquals(sTest.fact(4), 24);
    }
}


