package p8.helper;

import java.util.Random;

public class Helper {
	public static int calculateWorkDone() {
		Random rnd = new Random();
		return rnd.nextInt(5);
	}
}
