package Core;
public class Timer extends Thread {

	/**
	 * Class to count the Chess Timer
	 *
	 * @author Kevin.K
	 *
	 */
	public boolean running = false;
	public int msek = 0, sek = 5, min = 0, hour = 0;

	public Timer(int h, int m, int s)
	{
		this.hour = h;
		this.min = m;
		this.sek = s;
	}
	
	// Start the thread with run
	public void run() {

		// Counts the msek to 1000 then starts the Sek/Min/hour Timer
		while (running) {
			try {
				Thread.sleep(1);
			} catch (Exception e) {
			}
			msek++;

			// Start of the Timer
			if (msek >= 1000) {
				if (sek >= 0) {
					sek--;
					if (hour == 0 && min == 0 && sek == 0) {
						break;
					}
					if (min == 0 && sek == 0) {
						hour--;
						min = 59;
						sek = 59;
					}
					if (sek == 0) {
						min--;
						sek = 59;
					}
				}
				msek = 0;
			}
		}
	}

	// Function to start the Timer
	public void TimerStart() {
		running = true;
	}

	// Function to start the Timer
	public void TimerStop() {
		running = false;
	}
}