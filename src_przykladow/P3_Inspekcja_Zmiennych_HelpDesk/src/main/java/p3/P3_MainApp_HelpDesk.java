package p3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import p3.helpdesk.HelpDesk;
import p3.helpdesk.Ticket;
import p3.helpdesk.dit.Domain;

public class P3_MainApp_HelpDesk {

	public static void main(String[] args) {
		
		/**
		 * 7.00 : DUS zaczyna pracę
		 */
		HelpDesk helpDesk = HelpDesk.getInstance();
		
		/**
		 * 6.00 - 22.00 Ciężka praca DIT 
		 */
		getTodaysTickets().stream()
			.forEach(ticket -> {
				helpDesk.dispatchTicket(ticket);
			});
		
		/**
		 * Przodownicy pracy...
		 */
		printoutBestProgrammers(helpDesk.ditScoreboard);
	}
	
	public static List<Ticket> getTodaysTickets() {
		return Arrays.asList(
					new Ticket("Cały smyk nie działa!", Domain.SERVERS),
					new Ticket("Się zawiesił!", Domain.SERVERS),
					new Ticket("Baza zablokowana!", Domain.SERVERS),
					new Ticket("Znowu Cały smyk nie działa!", Domain.SERVERS),
					new Ticket("Znowu Się zawiesił!", Domain.SERVERS),
					new Ticket("Znowu Baza zablokowana!", Domain.SERVERS),		
				
					new Ticket("EOD nie działa!", Domain.EOD),
					new Ticket("Puste kolejki!", Domain.EOD),
					new Ticket("Zniknęły dokumenty!", Domain.EOD),
					
					new Ticket("Głuchy telefon!", Domain.CTI),
					new Ticket("Brak nagrań!", Domain.CTI),
					new Ticket("Cisza w słuchawce!", Domain.CTI),
					new Ticket("Znowu Głuchy telefon!", Domain.CTI),
					new Ticket("Znowu Brak nagrań!", Domain.CTI),
					new Ticket("Znowu Cisza w słuchawce!", Domain.CTI),
					
					new Ticket("eOrzeczenia się nie importują!", Domain.ECOURT),
					new Ticket("ePozwy się nie wysyłają!", Domain.ECOURT),
					new Ticket("eSąd ma przewę techniczną!", Domain.ECOURT),
					
					new Ticket("Nie ma a wiem że jest!", Domain.SEARCH),
					new Ticket("Za dużo Kowalskich!", Domain.SEARCH),
					new Ticket("Wolno się wyszukuje!", Domain.SEARCH),
					new Ticket("Znowu Nie ma a wiem że jest!", Domain.SEARCH),
					new Ticket("Znowu Za dużo Kowalskich!", Domain.SEARCH),
					new Ticket("Znowu Wolno się wyszukuje!", Domain.SEARCH)
					
				);
	}
	
	private static void printoutBestProgrammers(Map<String, Integer> scoreboard) {
		System.out.println("Dzisiejsi przodownicy pracy: ");
		scoreboard.entrySet().stream()
		.sorted((e1,e2) -> Integer.compare(e2.getValue(), e1.getValue()))
		.forEach(System.out::println);
	}
	
}
