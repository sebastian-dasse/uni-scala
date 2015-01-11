package ueb05;

import static org.junit.Assert.*;

import org.junit.Test;



public class MathServiceTest {
    
    
    private static MathService testee = new MathServiceImpl();
    
    private static final Function<Double> fConst = new Function<Double>(){
        public Double f(final Double x){
            return 2.0;
        }
    };
    private static final Function<Double> expectedFConstIntegral = new Function<Double>(){
        public Double f(final Double x){
            return 2*x;
        }
    };

    private static final Function<Double> fLin1 = new Function<Double>(){
        public Double f(Double x){
            return x;
        }
    };
    private static final Function<Double> expectedFLin1Integral = new Function<Double>(){
        public Double f(Double x){
            return x*x/2;
        }
    };

    private static final Function<Double> fQuad = new Function<Double>(){
        public Double f(Double x){
            return 3*x*x + 5;
        }
    };
    private static final Function<Double> expectedFQuadIntegral = new Function<Double>(){
        public Double f(Double x){
            return x*x*x + 5*x;
        }
    };

    @Test public void integrateFConstTo10(){
        final Double actual = testee.integrate(fConst, 10, 0.1);
        final Double expected = expectedFConstIntegral.f(10.0);
        assertEquals(expected, actual, expected*0.05);
    }
    
    @Test public void integrateFLin1To5(){
        final Double actual = testee.integrate(fLin1, 5, 0.1);
        final Double expected = expectedFLin1Integral.f(5.0);
        assertEquals(expected, actual, expected*0.05);
    }
    
    @Test public void integrateFQuadTo5(){
        final Double actual = testee.integrate(fQuad, 5, 0.1);
        final Double expected = expectedFQuadIntegral.f(5.0);
        assertEquals(expected, actual, expected*0.05);
    }
    
    @Test public void integralOfFLin1To1000(){
        final Function<Double> integral = testee.integral(fLin1, 1.0);
        for(int i=0; i<1000; i++){
            final double x = i;
            final Double actual = integral.f(x);
            final Double expected = expectedFLin1Integral.f(x);
            assertEquals("Integral of fLin1(x)=x at " + x, expected, actual, expected*0.1+1.5);
        }
    }
    
    
}
