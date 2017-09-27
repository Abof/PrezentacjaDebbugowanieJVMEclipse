package p8.worker;

import p8.helper.Helper;

public class Worker {
	public static void main(String[] args) throws InterruptedException {
		new Worker().startDoingSthInLoop();
	}

	public void startDoingSthInLoop() {
		// Zdanie:
		Runnable todo = () -> {
			int workDoneSoFar = 0;
			while (!Thread.interrupted()) {
				int workPart = Helper.calculateWorkDone();
				System.out
						.println(String.format("%1d worked... So far %3d done!", workPart, workDoneSoFar += workPart));
				sleepForOneSecond();
			}
		};

		// Wątek:
		Thread todoThread = new Thread(todo, "ToDoThread");
		todoThread.start();
	}

	private void sleepForOneSecond() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
