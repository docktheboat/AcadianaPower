package com.practice2.SpringPractice2.student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class StudentModelTest {

    @Test
    void setId() {
    }

    @Test
    void setName() {
    }

    @ParameterizedTest
    @CsvSource({
        "2,2"
    })
    void setAge(Byte got, Byte expected) {
        assertEquals(got,expected);
        Predicate<Byte> checkAge = n -> n <= 127 && n >= 0;
        assertTrue(checkAge.test((byte) 2));
    }

    @Test
    void setDob() {
    }
}