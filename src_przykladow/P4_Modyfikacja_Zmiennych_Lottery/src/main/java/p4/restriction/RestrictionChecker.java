package p4.restriction;

import static p4.restriction.CheckingResult.ResultType.NO_RESTRICTIONS;
import static p4.restriction.CheckingResult.ResultType.RESTRICTIONS;

public class RestrictionChecker {
	
	
	public CheckingResult checkForUser(int userId) {
		// Podstawowy RETURN - brak ograniczeń...
		CheckingResult checkingResult = new CheckingResult();
		checkingResult.type = NO_RESTRICTIONS;
				
		/**
		 * ZAWIŁE I SKOMPLIKOWANE SPRAWDZANIE OGRANICZEŃ.
		 * LICZNE ODWOŁANIA DO RÓŻBYCH OBSZARÓW BAZY DANYCH ;)
		 */
		if(userId == 13) {
			checkingResult.type = RESTRICTIONS;
		}
		
		return checkingResult;
	}
}
