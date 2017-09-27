package p7;

import java.util.Set;

import p7.processor.Processor;
import p7.repos.DummyInstallmentRepository;
import p7.repos.Installment;

public class P7_MainApp_ProcessingLoop {
	
	// RATY (DUUUUŻO ELEMENTÓW)
	private final static Set<Installment> installments = 
			new DummyInstallmentRepository().getAllInstallments();
	
	public static void main(String[] args) {
		Processor p = new Processor();
		
		installments.forEach(inst -> {
			p.process(inst);
		});
		
	}
}
