package ueb03;

public class FolgengeneratorImpl implements Folgengenerator {
	
	public String folge(final int n) {
		return n <= 0 ? "" : folge(n-1) + n + " ";
	}

	// JVM will not recognize tail-recursion and not (!) optimize
	public String folge(final int n, final String accu) {
		return n <= 0 ? accu : folge(n-1, n + " " + accu);
	}
	
	// JVM will recognize tail-recursion and optimize
//	public String folge(final int n, final String accu) {
//		if (n <= 0) {
//			return accu;
//		}
//		return folge(n-1, n + " " + accu);
//	}
}
