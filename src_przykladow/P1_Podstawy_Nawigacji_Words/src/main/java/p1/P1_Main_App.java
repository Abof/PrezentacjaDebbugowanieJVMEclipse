package p1;

import java.util.List;
import java.util.Set;

import p1.elements.SeriousLogic;
import p1.elements.Word;
import p1.elements.WordsProvider;
import p1.elements.boring.A;

public class P1_Main_App {

	public static void main(String[] args) {
		
		System.out.println("START");
		
		new P1_Main_App().start();
		
		System.out.println("STOP");
		
	}

	private void start() {

		String suffix = new String(" ...czy Ala to miała?!");
		WordsProvider wp = new WordsProvider(1, 2, 3); // Zaglądnijmy tu z ciekawości...
		List<Word> words = wp.provide();
		
		System.out.print("\n"); // STEP OVER...
		System.out.print("TO "); // ...AND OVER
		System.out.print("JEST ");
		System.out.print("EWIDENTNY ");
		System.out.print("PRZYKŁAD ");
		System.out.print("CZEGOŚ ");
		System.out.print("DO OMINIĘCIA ");
		System.out.print("ALE NIE POMIŃMY PONIŻSZEGO ");
		System.out.print("CO NASTĘPUJE: ");
		doSomeInterestingStuff();	// <-- !!! !!! MUST SEE !!! !!!
		// MĘCZĄCE TE SEPOVERY... DODAJMY BP!
		System.out.print("...BO TO ");
		System.out.print("...BY BYŁO ");
		System.out.print("...NIEDOPATRZENIE! \n");
				
		new SeriousLogic(words, suffix).doItNow();
		
	}

	private void doSomeInterestingStuff() {
		A a = new A();
		a.getB().getC().damnInterestingThings();
	}
	
}
