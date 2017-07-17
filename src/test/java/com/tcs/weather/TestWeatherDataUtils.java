package com.tcs.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.tcs.weather.bo.WeatherDataUtils;
import com.tcs.weather.dto.WeatherDataDto;

/**
 * 
 * @author srawanthi.samala
 * TestWeatherDataUtils contains all testcases related to WeatherDataUtils
 */
public class TestWeatherDataUtils {

	/**
	 * testGetFileContentinBuffer read weather data file content and generate bufferreader object
	 * and compares line with expected value
	 * @throws IOException
	 */
	@Test
	public void testGetFileContentinBuffer() throws IOException {
		WeatherDataUtils objWeatherDataUtils = new WeatherDataUtils();
		BufferedReader br = objWeatherDataUtils
				.getFileContentinBuffer("src/main/resources/longlatlookup-place-timezone.txt");
		Assert.assertEquals(br.readLine(),
				"-33.86,151.21,39|Sydney|Australia/Sydney");
	}

	/**
	 * testConvertTimezoneDatatoMap compares and test timezonemap with expected result
	 * @throws IOException
	 */
	@Test
	public void testConvertTimezoneDatatoMap() throws IOException {
		WeatherDataUtils objWeatherDataUtils = new WeatherDataUtils();
		HashMap<String, String> timezonemap = objWeatherDataUtils
				.convertTimezoneDatatoMap("src/main/resources/longlatlookup-place-timezone.txt");

		Assert.assertEquals(
				timezonemap.toString(),
				"{-37.83,144.98,7=-37.83,144.98,7|Melbourne|Australia/Melbourne, -34.92,138.62,48=-34.92,138.62,48|Adelaide|Australia/Adelaide, 17.38,78.48,505=17.38,78.48,505|Hyderabad|Asia/Kolkata, -33.86,151.21,39=-33.86,151.21,39|Sydney|Australia/Sydney}");

	}

	/**
	 * testConvertWeatherDatatoMap compares and test weather data with expected result
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test
	public void testConvertWeatherDatatoMap() throws IOException, ParseException {
		WeatherDataUtils objWeatherDataUtils = new WeatherDataUtils();
		HashMap<String, String> timezoneMap = objWeatherDataUtils
				.convertTimezoneDatatoMap("src/main/resources/longlatlookup-place-timezone.txt");
		HashMap<String, WeatherDataDto> weatherMap = objWeatherDataUtils.convertWeatherDatatoMap("src/main/resources/2017-07-14-weatherdata.txt",
				timezoneMap);
		//System.out.println(weatherMap.toString());
		Assert.assertEquals(weatherMap.toString(), 
				"{Melbourne|2015-12-25|02:30:55=position: -37.83,144.98,7, conditions: Snow, temparature: -5.3, pressure: 998.4, humidity: 55, city: Melbourne, "
				+ "localtime: 2015-12-25T02:30:55Z, timeOffset: +11:00, Adelaide|2017-06-20|20:30:37=position: -34.92,138.62,48, conditions: Sunny,"
				+ " temparature: +19, pressure: 1026, humidity: 37, city: Adelaide, localtime: 2017-06-20T20:30:37Z, timeOffset: +09:30, "
				+ "Adelaide|2016-01-03|23:05:37=position: -34.92,138.62,48, conditions: Sunny, temparature: +39.4, pressure: 1114.1, "
				+ "humidity: 12, city: Adelaide, localtime: 2016-01-03T23:05:37Z, timeOffset: +10:30, Hyderabad|2017-06-20|16:30:37=position: 17.38,78.48,505,"
				+ " conditions: Rain, temparature: 27, pressure: 1006, humidity: 78, city: Hyderabad, localtime: 2017-06-20T16:30:37Z, timeOffset: +05:30,"
				+ " Adelaide|2016-11-20|21:30:37=position: -34.92,138.62,48, conditions: Snow, temparature: -3, pressure: 990.2, humidity: 45, city: Adelaide, "
				+ "localtime: 2016-11-20T21:30:37Z, timeOffset: +10:30, Sydney|2015-12-23|16:02:12=position: -33.86,151.21,39, conditions: Rain, temparature: +12.5, "
				+ "pressure: 1004.3, humidity: 97, city: Sydney, localtime: 2015-12-23T16:02:12Z, timeOffset: +11:00}");
	}
}
