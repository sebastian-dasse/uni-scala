package ueb05;

public class MathServiceImpl implements MathService {

	//---- recursive version
	public double integrate(final Function<Double> f, final double endX, final double incrementX) {
		return endX <= 0 ? 0 : incrementX * f.f(endX) + integrate(f, endX - incrementX, incrementX);
	}

	
	//---- tail-recursive version
//	public double integrate(final Function<Double> f, final double endX, final double incrementX) {
//		return integrate(f, endX - incrementX, incrementX, 0);
//	}
//	
//	private double integrate(final Function<Double> f, final double endX, final double incrementX, final double sum) {
//		if (endX <= 0) {
//			return sum; 
//		}
//		return integrate(f, endX - incrementX, incrementX, sum + incrementX * f.f(endX));
//	}
	
	
	//---- iterative version
//	public double integrate(final Function<Double> f, final double endX, final double incrementX) {
//		double sum = 0;
//		for (double x = incrementX; x <= endX; x += incrementX) {
//			sum += incrementX * f.f(x);
//		}
//		return sum;
//	}
	
	
	public Function<Double> integral(final Function<Double> f, final double incrementX) {
		return new Function<Double>() {
			public Double f(final Double x) {
				return integrate(f, x, incrementX);
			}
		};
	}

}
