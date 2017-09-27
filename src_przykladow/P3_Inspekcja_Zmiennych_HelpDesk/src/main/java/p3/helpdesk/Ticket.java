package p3.helpdesk;

import static java.util.Objects.requireNonNull;

import p3.helpdesk.dit.Domain;
import p3.helpdesk.dit.Programmer;

public class Ticket {
	
	String description;
	public Domain domain;
	
	Programmer assigned;
	
	boolean askedIfTheProblemStillOccur = false;

	public Ticket(String description, Domain domain) {
		this.description = requireNonNull(description);
		this.domain = requireNonNull(domain);
	}

	public Ticket() {
		super();
	}

}
