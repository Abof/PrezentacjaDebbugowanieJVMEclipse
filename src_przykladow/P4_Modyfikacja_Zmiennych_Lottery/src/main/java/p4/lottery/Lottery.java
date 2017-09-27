package p4.lottery;

import static java.util.stream.Collectors.toList;
import static p4.restriction.CheckingResult.ResultType.RESTRICTIONS;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import p4.opr.OperatorCtx;
import p4.repos.UserInstallmentsRepository;
import p4.repos.UserRepository;
import p4.restriction.CheckingResult;
import p4.restriction.RestrictionChecker;
import p4.restriction.RestrictionsException;
import p4.user.User;

public class Lottery {
	
	private UserRepository usersRepo = new UserRepository();
	private UserInstallmentsRepository userInstallmentsRepo = new UserInstallmentsRepository();
	private RestrictionChecker restrictionChecker = new RestrictionChecker();
	private Bargain bargain = new Bargain();
	
	public void makeDreamsComeTrue() {
		
		checkIfOprInGroup();
		
		// WYLOSUJ SZCZĘŚLIWCA
		int winnerId = drawUserId();
		
		// SPRAWDŹ OGRANICZENIA WYBRAŃCA
		CheckingResult results = restrictionChecker.checkForUser(winnerId);
		if(results.type.equals(RESTRICTIONS)){
			throw new RestrictionsException("Wybraniec nie jest OK!");
		}
		
		// POBIERZ RATY SZCZĘŚLIWCA
		List<Integer> installmentsForUser = userInstallmentsRepo.getInstallmentsForUser(winnerId);
		
		// ZSUMUJ AKTUALNE RATY (bo... czemu nie :)
		int oldInstallmentSum = installmentsForUser
			.stream()
			.mapToInt(Integer::intValue)
			.sum(); // Ten sum nie lubi NULL-i!
		
		// ZASTOSUJ PROMOCJĘ DO RAT!
		int newInstallmentSum = 0;
		for (Integer installment : installmentsForUser) {
			int newInstallment = bargain.apply(installment);
			newInstallmentSum += newInstallment;
		}
		
		System.out.println("Szczęśliwy użytkownik " + winnerId 
				+ " ma teraz do spłaty zaledwie: " + newInstallmentSum 
				+ " (a miał " + oldInstallmentSum +")");
	}

	private static final int LOTTERY_GROUP = 1234;
	private void checkIfOprInGroup() throws IllegalAccessError {
		OperatorCtx oprCtx = OperatorCtx.getCtx();
		if(! oprCtx.getGroups().contains(LOTTERY_GROUP)) {
			throw new IllegalAccessError("Operator nie jest w grupie " + LOTTERY_GROUP + " !");
		}
	}
	
	private int drawUserId(){	
		List<User> choosenUsers = usersRepo.getUsers().stream()
			.filter(u -> !u.isExcluded && u.isCool && (u.isTall || u.isClever))
			.collect(toList());
		
		int winnerIndex = new Random().nextInt(choosenUsers.size());
		
		return choosenUsers.get(winnerIndex).id;
	}
}
