package p1.elements.boring;

public class C {
	
	public void damnInterestingThings() {
		int numerator = 1;
		int denominator = 0;
		
		try {
			
			int result = numerator / denominator;
			
		} catch (ArithmeticException e) {
			System.out.print("UFF... THAT WAS CLOSE!..");
		}
		
	}
}
