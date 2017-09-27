package p4.repos;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserInstallmentsRepository {
	
	/**
	 * OCZYWIŚCIE JEST TO UPROSZCZONA WERSJA 
	 * WYSUBLIMOWANEJ LOGIKI POBIERANIA RAT Z DB
	 */
	public List<Integer> getInstallmentsForUser(int userId){
		switch (userId) {
		case 10:
			return Arrays.asList(432, 432, null, 123);
		case 11:
			return Arrays.asList(122, 122, null, 122);
		case 12:
			return Arrays.asList(null, 1_787, 1_787, null, 1_000, null, 1_787, 1_787, null, 1_000, null, 1_787, 1_787, null, 1_000, null, 1_787, 1_787, null, 1_000, null, 1_787, 1_787, null, 1_000);
		case 13:
			return Arrays.asList(45, null, 34, null);
		case 14:
			return Arrays.asList(633, null, 633, 633);
		default:
			return Collections.emptyList();
		}
	}
}
