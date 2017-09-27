package p3.helpdesk.dit;

public class Programmer {
	
	public String name;
	public Domain mainDomain;
	
	public Programmer(String name, Domain mainDomain) {
		super();
		this.name = name;
		this.mainDomain = mainDomain;
	}
		
	public Programmer(String name) {
		super();
		this.name = name;
	}

}
