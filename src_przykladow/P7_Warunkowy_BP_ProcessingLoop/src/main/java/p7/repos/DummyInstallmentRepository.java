package p7.repos;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class DummyInstallmentRepository {
	
	private final int NO_OF_ALL_INSTALLMENTS = 1_234_567;
	public Set<Installment> getAllInstallments() {
		Set<Installment> installments = new HashSet<Installment>();
		
		for (int i = 0; i < NO_OF_ALL_INSTALLMENTS; i++) {
			Installment newInst = new Installment(
					instAmountSupplier.get(), 
					instTypeSupplier.get());
			installments.add(newInst);
		}
		
		return installments;
	}
	
	private final Random RND = new Random(11051988);
	
	private Supplier<Installment.InstallmentType> instTypeSupplier = 
			() -> Installment.InstallmentType.values()[RND.nextInt(Installment.InstallmentType.values().length)];
	
	private Supplier<Integer> instAmountSupplier = 
			() -> RND.nextInt(1_000);
	
}
