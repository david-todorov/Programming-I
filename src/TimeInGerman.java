
import java.time.LocalTime;

public class TimeInGerman {

	private static final String FÜNF = "FÜNF";
	private static final String ZEHN = "ZEHN";
	private static final String VIERTEL = "VIERTEL";
	private static final String NACH = "NACH";
	private static final String VOR = "VOR";
	private static final String UHR = "UHR";
	private static final String HALB = "HALB";
	private static final String IRREGULAR_EIN = "EIN";
	private static final String[] HOURS_IN_WORDS = { "EINS", "ZWEI", "DREI", "VIER", "FÜNF", "SECHS", "SIEBEN", "ACHT",
			"NEUN", "ZEHN", "ELF", "ZWÖLF" };

	public static String sayTimeInGerman(LocalTime time) {

		int hours = time.getHour();
		int minutes = time.getMinute();
		int seconds = time.getSecond();
		int parsedMinutes = (minutes * 60) + seconds; // Parsing minutes and seconds into seconds. This is the time period
		String minutesAsWord = getMinutesAsWord(minutes, seconds); // Getting specific word which represents the minutes
		String currentHourAsWord = getHoursAsWord(hours); // Getting current hour. Example 16 -> "VIER"
		String nextHourAsWord = getHoursAsWord(hours + 1); // Getting the very next hour according to the current hour. In some cases the next hour is required.
															 
		String result = null;

		if (parsedMinutes >= 150 && parsedMinutes <= 1049) { // Different cases which depend on the time period. The time period leads to different output formats.
			result = minutesAsWord + " " + NACH + " " + currentHourAsWord;
		} else if (parsedMinutes >= 1050 && parsedMinutes <= 1649) {
			result = minutesAsWord + " " + VOR + " " + HALB + " " + nextHourAsWord;
		} else if (parsedMinutes >= 1650 && parsedMinutes <= 1949) {
			result = HALB + " " + nextHourAsWord;
		} else if (parsedMinutes >= 1950 && parsedMinutes <= 2549) {
			result = minutesAsWord + " " + NACH + " " + HALB + " " + nextHourAsWord;
		} else if (parsedMinutes >= 2550 && parsedMinutes <= 3449) {
			result = minutesAsWord + " " + VOR + " " + nextHourAsWord;
		} else if ((parsedMinutes >= 0 && parsedMinutes <= 149)) {
			if (currentHourAsWord.equals("EINS")) {
				currentHourAsWord = IRREGULAR_EIN;
			}
			result = currentHourAsWord + " " + UHR;
		} else if (parsedMinutes >= 3450 && parsedMinutes <= 3599) {
			if (nextHourAsWord.equals("EINS")) {
				nextHourAsWord = IRREGULAR_EIN;
			}
			result = nextHourAsWord + " " + UHR;
		}
		return result;
	}

	private static String getHoursAsWord(int hours) { // Getting hours as words from HOURS_IN_WORDS where the parameter hours point to specific value in the array using (hours - 1)  formula or 11 because 11th element is ZW�LF
		while ((hours >= 0 && hours < 12) == false) {
			hours -= 12;
		}
		int index = hours == 0 ? 11 : hours - 1;
		return HOURS_IN_WORDS[index];
	}

	private static String getMinutesAsWord(int min, int sec) { // Getting minutes as words. Every specific value for amount of minutes (F�NF,ZEHN,VIERTEL) has a time period and parsedMinutes represents that time period.															
		int parsedMinutes = (min * 60) + sec;
		String result = null;
		if ((parsedMinutes >= 150 && parsedMinutes <= 449) || (parsedMinutes >= 1350 && parsedMinutes <= 1649)
				|| (parsedMinutes >= 1950 && parsedMinutes <= 2249)
				|| (parsedMinutes >= 3150 && parsedMinutes <= 3449)) {
			result = FÜNF;
		} else if ((parsedMinutes >= 450 && parsedMinutes <= 749) || (parsedMinutes >= 1050 && parsedMinutes <= 1349)
				|| (parsedMinutes >= 2250 && parsedMinutes <= 2549)
				|| (parsedMinutes >= 2850 && parsedMinutes <= 3149)) {
			result = ZEHN;
		} else if ((parsedMinutes >= 750 && parsedMinutes <= 1049)
				|| (parsedMinutes >= 2550 && parsedMinutes <= 2849)) {
			result = VIERTEL;
		}
		return result;
	}
}
