package com.NoisyCrow.ClassMateProject;
import static org.junit.Assert.assertEquals;

import org.junit.*;

public class funcionesBasicasTest {

    funcionesBasicas fb;

    @Before
    public void initialize(){
        fb = new funcionesBasicas();
    }

    @Test
    public void testSuma(){
        assertEquals(2.0, fb.suma(1.0, 1.0));
        assertEquals(2.0, null);
    }
}