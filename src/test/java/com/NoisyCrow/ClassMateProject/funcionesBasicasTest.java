package com.NoisyCrow.ClassMateProject;
import static org.junit.Assert.assertEquals;
import com.NoisyCrow.ClassMateProject.funciones.funcionesBasicas;

import org.junit.*;

public class funcionesBasicasTest {

    funcionesBasicas fb;

    @Before
    public void initialize(){
        fb = new funcionesBasicas();
    }

    @Test
    public void testSuma(){
        assertEquals("test", "test");
    }
}