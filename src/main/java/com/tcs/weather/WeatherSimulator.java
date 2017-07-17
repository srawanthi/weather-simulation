package com.tcs.weather;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import com.tcs.weather.bo.WeatherDataUtils;
import com.tcs.weather.constants.WeatherConstants;
import com.tcs.weather.dto.WeatherDataDto;

/**
 * 
 * @author srawanthi.samala Weather Simulator is taking an arguments like
 *         placeName, date, time, timezoneFileLookupPath, weatherDataInputPath
 *         and generating Weather data as
 *         location|position|localtimeutc|conditions
 *         |temparature|position|humidity
 */
public class WeatherSimulator {

	private static String placeName;
	private static String date;
	private static String time;
	private static String timezoneFileLookupPath;
	private static String weatherDataInputPath;

	/**
	 * This method used to setup the process for generating Weather Data args :
	 * String[] accepts parameters like placeName, date, time,
	 * timezoneFileLookupPath, weatherDataInputPath
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		WeatherSimulator.setupProcess(args);
	}

	/**
	 * validateArgs is used for validating arguments
	 * 
	 * @param args
	 * @return boolean
	 * @throws Exception
	 */
	public boolean validateArgs(String[] args) throws Exception {
		if (args.length < 5) {
			System.out.println("Weather Simulator Parameters");
			System.out.println("PlaceName [e.g. Sydney]");
			System.out.println("Date   [e.g. 2015-12-23]");
			System.out.println("Time     [e.g. 16:02:12]");
			System.out
					.println("TimezoneFileLookup Path   [ e.g. src/main/resources/longlatlookup-place-timezone.txt ]");
			System.out
					.println("WeatherData Input Path       [ e.g. src/main/resources/2017-07-14-weatherdata.txt ]");

			throw new Exception("Need more information (5 parameters)");
		}
		return true;
	}

	/**
	 * parseInputDatatoMap is used to parse the input weather data and mapping
	 * with timezone, longitude and latitude by using timezone file lookup path
	 * 
	 * @param timezoneFileLookupPath
	 * @param weatherDataInputPath
	 * @return HashMap<String, WeatherDataDto>
	 * @throws IOException
	 * @throws ParseException
	 */
	public HashMap<String, WeatherDataDto> parseInputDatatoMap(
			String timezoneFileLookupPath, String weatherDataInputPath)
			throws IOException, ParseException {

		WeatherDataUtils objWeatherDataUtils = new WeatherDataUtils();
		HashMap<String, String> timezoneMap = objWeatherDataUtils
				.convertTimezoneDatatoMap(timezoneFileLookupPath);
		System.out.println(timezoneMap.toString());
		// location|position|localtimeutc|conditions|temparature|position|humidity
		HashMap<String, WeatherDataDto> weatherMap = objWeatherDataUtils
				.convertWeatherDatatoMap(weatherDataInputPath, timezoneMap);
		return weatherMap;
	}

	/**
	 * setupProcess is used to accept arguments as placeName, date, time,
	 * timezoneFileLookupPath, weatherDataInputPath and generating Weather data
	 * as
	 * location|position|localtimeutc|conditions|temparature|position|humidity
	 * 
	 * @param args
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean setupProcess(String[] args) throws Exception {

		WeatherSimulator objWeatherSimulator = new WeatherSimulator();
		objWeatherSimulator.validateArgs(args);
		placeName = args[0];
		date = args[1];
		time = args[2];
		timezoneFileLookupPath = args[3];
		weatherDataInputPath = args[4];

		HashMap<String, WeatherDataDto> weatherMap = objWeatherSimulator
				.parseInputDatatoMap(timezoneFileLookupPath,
						weatherDataInputPath);

		try {
			WeatherDataDto outputdata = weatherMap.get(placeName
					+ WeatherConstants.ROW_SEPERATOR + date
					+ WeatherConstants.ROW_SEPERATOR + time);
			System.out.println("*******Weather Data Output******* :"
					+ outputdata.toString());
		} catch (Exception e) {
			System.out.println(" Weather Data Lookup not found for placeName: "
					+ placeName + ", date:  " + date + " and time: " + time);
		}
		return true;
	}

}
