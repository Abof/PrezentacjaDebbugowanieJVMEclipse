package p7.processor;

import p7.repos.Installment;

public class Processor {
	public void process(Installment i) {
		/**
		 * Złożony proces przetwarzania.
		 */
		int result = ((i.amount + 1 * 1 - 1) / 1) + (234 / 2 * 8 / 2) / 468;
		System.out.println(i + " -> " + result);
	}
}
