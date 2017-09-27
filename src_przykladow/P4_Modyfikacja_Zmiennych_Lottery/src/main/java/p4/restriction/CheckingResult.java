package p4.restriction;

public class CheckingResult {
	
	public ResultType type;
	
	public CheckingResult() {
		super();
		this.type = ResultType.RESTRICTIONS;
	}

	public void setType(ResultType type) {
		this.type = type;
	}

	public enum ResultType {
		NO_RESTRICTIONS,
		RESTRICTIONS;
	}
}
