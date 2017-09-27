package p4.lottery;

public class Bargain {
	
	private int bargainAmount = -50;
	
	int apply(int installment){
		return installment + bargainAmount;
	}
}
