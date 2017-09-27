package p7.repos;

public class Installment {
	
	public int amount;
	public InstallmentType type;
	
	public Installment(int amount, InstallmentType type) {
		super();
		this.amount = amount;
		this.type = type;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	private static String TO_STRING_TEMPLATE = "I[%4d, %10s]";
	@Override
	public String toString() {
		return String.format(TO_STRING_TEMPLATE, amount, type);
	}
	
	public enum InstallmentType {
		EQUAL,
		DECREASING,
		VARIABLE;
	}
	
}
