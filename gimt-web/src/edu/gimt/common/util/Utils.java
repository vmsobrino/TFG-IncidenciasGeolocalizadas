/**
 * 
 */
package edu.gimt.common.util;

//import java.math.BigDecimal;
//import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase de utilidades.
 * @author Victor M. Sobrino - TFG
 */
public class Utils {
	
	public final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public final static String DATE_FORMAT = "yyyy-MM-dd";
	
	private final static SimpleDateFormat sdfDateTimeFmt = new SimpleDateFormat(DATETIME_FORMAT);
	private final static SimpleDateFormat sdfDateFmt = new SimpleDateFormat(DATE_FORMAT);

	public Utils() {
	}
	
	public static void main(String[] args) {
		System.out.println("Prueba validDateTime");
		boolean ok = false;
		ok = validDateTime("2016-01-31 11:24:11");
		System.out.println(ok);
		
		System.out.println("");
		System.out.println("Prueba distanceBetween");
		double distance = 0d;
		distance = distanceBetween("42.94338", "-2.63963", "43.22337", "-2.819959");
		System.out.println(distance);
	}
	
	public static String getLatLonFormat(BigDecimal latLon) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		DecimalFormat latLonFmt = new DecimalFormat("###.######", symbols);
		return latLonFmt.format(latLon);
	}
	
	public static String getStringDate(Date date) {
		return sdfDateFmt.format(date);
	}
	
	public static String getStringDateTime(Date date) {
		if (null == date) {
			return "";
		}
		else {
			return sdfDateTimeFmt.format(date);
		}
	}
	
	/**
	 * Check format of date-time value.
	 * @param strDateTime String with date-time value.
	 * @return True if pass validation, False otherwise.
	 */
	public static boolean validDateTime(String strDateTime) {
		boolean valid = true;
		Date date = null;
		
		Calendar cal = null;
		
		try {
			sdfDateTimeFmt.setLenient(false);
			date = sdfDateTimeFmt.parse(strDateTime);
			cal = Calendar.getInstance();
			cal.setLenient(false);
			cal.setTime(date);
		    cal.getTime();
		}
		catch (ParseException pe) {
			valid = false;
		}
		catch (Exception e) {
			valid = false;
		}			
		return valid;
	}


	/**
	 * Check format of date value.
	 * @param strDate String with date value.
	 * @return True if pass validation, False otherwise.
	 */
	public static boolean validDate(String strDate) {
		boolean valid = true;
		Date date = null;
		Calendar cal = null;
		
		try {
			sdfDateFmt.setLenient(false);
			date = sdfDateFmt.parse(strDate);
			cal = Calendar.getInstance();
			cal.setLenient(false);
			cal.setTime(date);
		    cal.getTime();
		}
		catch (ParseException pe) {
			valid = false;
		}
		catch (Exception e) {
			valid = false;
		}			
		return valid;
	}

	public static Date getDateTime(String dateTime) {
		Date date = null;
		try {
			sdfDateTimeFmt.setLenient(false);
			date = sdfDateTimeFmt.parse(dateTime);
		}
		catch (ParseException pe) {
			date = null;
		}
		return date;
	}
	
	public static Date getDate(String strDate) {
		Date date = null;
		try {
			sdfDateFmt.setLenient(false);
			date = sdfDateFmt.parse(strDate);
		}
		catch (ParseException pe) {
			date = null;
		}
		return date;
	}
	
	public static boolean isDateRange(String StringdateTime) {
		return (StringdateTime.split(" - ").length == 2);
	}
	
	public static boolean equalDates(String oneDateTime, String otherDateTime) {
		boolean equals = false;
		boolean firstDateRange = false;
		boolean secondDateRange = false;
		Date oneDate = null;
		Date otherDate = null;
		Calendar oneCal = null;
		Calendar otherCal = null;
		
		// Comprobamos si ambas son un rango de fechas
		firstDateRange = isDateRange(oneDateTime);
		secondDateRange = isDateRange(otherDateTime);
		
		if (firstDateRange && secondDateRange && oneDateTime.equals(otherDateTime)) {
			equals = true;
		}
		else {
			try {
				if (!firstDateRange && !secondDateRange) {
					oneDate = getDateTime(oneDateTime);
					otherDate = getDateTime(otherDateTime);
					oneCal = Calendar.getInstance();
					otherCal = Calendar.getInstance();
					oneCal.setTime(oneDate);
					otherCal.setTime(otherDate);
					
					if (oneCal.get(Calendar.YEAR) == otherCal.get(Calendar.YEAR) &&
						oneCal.get(Calendar.MONTH) == otherCal.get(Calendar.MONTH) &&
						oneCal.get(Calendar.DATE) == otherCal.get(Calendar.DATE)) {
						equals = true;
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("oneDate="+oneDate);
				System.out.println("otherDate="+otherDate);
			}
		}
		return equals;
	}


	public static int compareDates(String oneDateTime, String otherDateTime) {
		
		boolean firstDateRange = false;
		boolean secondDateRange = false;
		Calendar oneCal = Calendar.getInstance();
		Calendar otherCal = Calendar.getInstance();
		int compare = 0;

		try {
			// Comprobamos si son un rango de fechas
			firstDateRange = isDateRange(oneDateTime);
			secondDateRange = isDateRange(otherDateTime);
			
			if (firstDateRange) {
				oneCal.setTime(getDate(oneDateTime.split(" - ")[0]));
				oneCal.set(Calendar.HOUR, 0);
				oneCal.set(Calendar.MINUTE, 0);
				oneCal.set(Calendar.SECOND, 0);
			}
			else {
				oneCal.setTime(getDateTime(oneDateTime));
			}
			
			if (secondDateRange) {
				otherCal.setTime(getDate(otherDateTime.split(" - ")[0]));
				otherCal.set(Calendar.HOUR, 0);
				otherCal.set(Calendar.MINUTE, 0);
				otherCal.set(Calendar.SECOND, 0);
			}
			else {
				otherCal.setTime(getDateTime(otherDateTime));
			}
			compare = oneCal.compareTo(otherCal);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("oneDate="+oneDateTime);
			System.out.println("otherDate="+otherDateTime);
		}
		return compare;
	}

	/**
	 * Calculate the distance between two coordinate points in meters.
	 * @param latitude1 Latitude for first coordinate point.
	 * @param longitude1 Longitude for first coordinate point.
	 * @param latitude2 Latitude for second coordinate point.
	 * @param longitude2 Longitude for first coordinate point.
	 * @return Meters between the first and the second coordinate point.
	 */
	public static double distanceBetween(String latitude1, String longitude1, String latitude2, String longitude2) {
		return distanceBetween(Double.parseDouble(latitude1),
							   Double.parseDouble(longitude1),
							   Double.parseDouble(latitude2),
							   Double.parseDouble(longitude2));
	}
	
	/**
	 * Calculate the distance between two coordinate points in meters.
	 * @param latitude1 Latitude for first coordinate point.
	 * @param longitude1 Longitude for first coordinate point.
	 * @param latitude2 Latitude for second coordinate point.
	 * @param longitude2 Longitude for first coordinate point.
	 * @return Meters between the first and the second coordinate point.
	 */
	public static double distanceBetween(double latitude1, double longitude1, double latitude2, double longitude2) {  
        double earthRadio = 6371; // en kilometros  
        double dLat = Math.toRadians(latitude2 - latitude1);  
        double dLng = Math.toRadians(longitude2 - longitude1);  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distance = earthRadio * va2;
        distance = ((long)distance * 1000); // en metros sin decimales
   
        return distance;  
    }
	
	/**
	 * Get the difference in minutes between two date-time.
	 * @param firstDateTime First calendar date.
	 * @param secondDateTime Second calendar date.
	 * @return Difference in minutes between the two dates in absolute value.
	 */
	public static int minutesBetween(Calendar firstDateTime, Calendar secondDateTime) {
		int minBetween = 0;
		long seconds = Math.abs(secondDateTime.getTimeInMillis() - firstDateTime.getTimeInMillis()) / 1000;
		minBetween = (int) (seconds / 60);
		return minBetween;
	}


	public static int minutesBetween(String oneDateTime, String otherDateTime) {
		
		boolean firstDateRange = false;
		boolean secondDateRange = false;
		Calendar oneCal = Calendar.getInstance();
		Calendar otherCal = Calendar.getInstance();
		int minutes = 0;

		try {
			// Comprobamos si son un rango de fechas
			firstDateRange = isDateRange(oneDateTime);
			secondDateRange = isDateRange(otherDateTime);
			
			// Si alguna de las fechas es rango, no comparamos
			if ((firstDateRange && !secondDateRange) ||
				(!firstDateRange && secondDateRange)) {
				minutes = Integer.MAX_VALUE;
			}
			else { // Si ambas son rango de fechas
				if (firstDateRange && secondDateRange) {
					oneCal.setTime(getDate(oneDateTime.split(" - ")[0]));
					oneCal.set(Calendar.HOUR, 0);
					oneCal.set(Calendar.MINUTE, 0);
					oneCal.set(Calendar.SECOND, 0);
					
					otherCal.setTime(getDate(otherDateTime.split(" - ")[0]));
					otherCal.set(Calendar.HOUR, 0);
					otherCal.set(Calendar.MINUTE, 0);
					otherCal.set(Calendar.SECOND, 0);
				}
				else { // Ninguna es rango de fechas
					oneCal.setTime(getDateTime(oneDateTime));
					otherCal.setTime(getDateTime(otherDateTime));
				}
				minutes = minutesBetween(oneCal, otherCal);
			}
		}
		catch (Exception e) {
			System.out.println("oneDate="+oneDateTime);
			System.out.println("otherDate="+otherDateTime);
			e.printStackTrace();
		}
		return minutes;
	}

}
