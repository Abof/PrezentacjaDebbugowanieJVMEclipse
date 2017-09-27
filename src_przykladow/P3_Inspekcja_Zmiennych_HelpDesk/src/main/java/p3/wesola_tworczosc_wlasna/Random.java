package p3.wesola_tworczosc_wlasna;

import static p3.helpdesk.dit.Domain.ECOURT;

import p3.helpdesk.HelpDesk;
import p3.helpdesk.dit.Programmer;

public class Random extends java.util.Random {
	
	public Random() {
		super();
		// I' have no idea what I'm doing :)
		HelpDesk helpDesk = HelpDesk.getInstance();
		helpDesk.ditProgrammers.add(new Programmer("DAREK", ECOURT));
	}
	
	private static final long serialVersionUID = 1L;
}
