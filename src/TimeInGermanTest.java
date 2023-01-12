import java.time.LocalTime;



public class TimeInGermanTest{

	public static void main(String[] args) {
		

		for(int hours = 0; hours < 24; hours++) {
			for (int minutes = 0; minutes < 60; minutes++) {
				for (int seocnds = 0; seocnds < 60 ; seocnds++) {
					System.out.println(TimeInGerman.sayTimeInGerman(LocalTime.of(hours, minutes,seocnds)));
				}
			}
		}
		
	}
}