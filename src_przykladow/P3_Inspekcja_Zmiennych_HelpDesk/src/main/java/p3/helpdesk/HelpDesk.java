package p3.helpdesk;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import p3.helpdesk.dit.Domain;
import p3.helpdesk.dit.Programmer;
import p3.wesola_tworczosc_wlasna.Random;

public class HelpDesk {

	public List<Programmer> ditProgrammers;
	public Map<String, Integer> ditScoreboard;

	/**
	 * Tutaj decydujemy się wgłębiać - poznamy przebieg działania...
	 */
	private void initialize() {
		initProgrammersList();
		initProgrammersScoreboard();
	}
	
	/**
	 * "Find" victim and assign ticket t(-_-t)
	 */
	public Programmer dispatchTicket(Ticket ticket){		
		// Wybór programisty
		Random decisionEngine = new Random();
		int programmerIdx = decisionEngine.nextInt(ditProgrammers.size());
		Programmer wiselyChoosenProgrammer = ditProgrammers.get(programmerIdx);
		
		// Dodanie punktu
		addOnePoint(wiselyChoosenProgrammer.name);
		
		return wiselyChoosenProgrammer;
	}
	
	private void addOnePoint(String name) {
		int actualScore = ditScoreboard.get(name);
		ditScoreboard.put(name, ++actualScore);
	}

	private void initProgrammersList() {
		ditProgrammers = new ArrayList<>();
		ditProgrammers.addAll(Arrays.asList(
				new Programmer("DAREK", Domain.ECOURT),
				new Programmer("ADAM",	Domain.CTI),
				new Programmer("WIESŁAW", Domain.SERVERS),
				new Programmer("DAMIAN", Domain.ECOURT),
				new Programmer("ADRIAN", Domain.EOD),
				new Programmer("PAWEŁ", Domain.KREATOR_DOK)
			));	
	}
	
	private void initProgrammersScoreboard() {
		ditScoreboard = new HashMap<>();
		ditProgrammers.forEach(
				programmer -> ditScoreboard.put(programmer.name, 0));
	}
	
	/*
	 * SINGLETON STUFF...
	 */
	private static HelpDesk INSTANCE;
	
	private HelpDesk() {
		initialize();
	}
	
	public static HelpDesk getInstance() {
		if(isNull(INSTANCE)) {
			INSTANCE = new HelpDesk();
		}
		return INSTANCE;
	}
}
