package p6;

import java.util.Random;

public class ComplicatedLogic {
	
	public static void doThis() {
		if(isItMe()) {
			throw new RuntimeException("doThis");
		}
	}
	
	public static void doThat() {
		if(isItMe()) {
			throw new RuntimeException("doThat");
		}
	}
	
	public static void doThatAgain() {
		if(isItMe()) {
			throw new RuntimeException("doThatAgain");
		}
	}
	
	public static void doThisAgain() {
		if(isItMe()) {
			throw new RuntimeException("doThisAgain");
		}
	}
	
	public static void doThisLastTime() {
		if(isItMe()) {
			throw new RuntimeException("doThisLastTime");
		}
	}
	
	public static void doThatLastTime() {
		if(isItMe()) {
			throw new RuntimeException("doThatLastTime");
		}
	}
	
	private static final double EXCEPTION_TRESHOLD = 0.95;
	private static boolean isItMe() {
		double dbl = new Random().nextDouble();
		return dbl > EXCEPTION_TRESHOLD;
	}
}
