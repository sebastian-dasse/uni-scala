package ueb05;

/** A Service for numerically integrating functions of double floating point numbers.*/
public interface MathService {
    
    /** Computes the area between the X-axis and the graph of the function f from 0 to endX 
     * by summing up the rectangles beneath the graph with a width of incrementX. */
    double integrate(Function<Double> f, double endX, double incrementX);
    
    /** Returns a function, which is the integration function for f, computed by X-increments of incrementX. */
    Function<Double> integral(Function<Double> f, double incrementX);

}
