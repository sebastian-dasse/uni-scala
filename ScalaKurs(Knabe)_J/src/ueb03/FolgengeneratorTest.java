package ueb03;

import static org.junit.Assert.*;

import org.junit.Test;



public class FolgengeneratorTest {
    
    final Folgengenerator gen = new FolgengeneratorImpl();

    @Test public void folgeRekursiv(){
        assertEquals("1 ", gen.folge(1));
        assertEquals("1 2 ", gen.folge(2));
        assertEquals("1 2 3 4 5 6 7 8 9 ", gen.folge(9));
    }
    
    @Test public void folgeEndrekursiv(){
        assertEquals("1 ", gen.folge(1, ""));
        assertEquals("1 2 ", gen.folge(2, ""));
        assertEquals("1 2 3 4 5 6 7 8 9 ", gen.folge(9, ""));
        assertEquals("1 2 3 4 abcd", gen.folge(4, "abcd"));
    }
    
}
