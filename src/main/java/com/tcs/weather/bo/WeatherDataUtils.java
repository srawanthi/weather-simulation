package com.tcs.weather.bo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;

import com.tcs.weather.constants.WeatherConstants;
import com.tcs.weather.dto.WeatherDataDto;

/**
 * 
 * @author srawanthi.samala
 * WeatherDataUtils contains all methods related to File Content reading and convert into required object
 */
public class WeatherDataUtils {
	
	/**
	 * getFileContentinBuffer read weather data file content and generate bufferreader object
	 * @param path
	 * @return BufferedReader
	 * @throws FileNotFoundException
	 */
	public BufferedReader getFileContentinBuffer(String path) throws FileNotFoundException{
		File initialFile = new File(path);
		InputStream in = new FileInputStream(initialFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		return br;
	}

	/**
	 * convertTimezoneDatatoMap reads timezone data file content and generates HashMap<String, String>
	 * HashMap<String, String> contains key as longitude latitude value as position, country, timezone
	 * @param path
	 * @return HashMap<String, String>
	 * @throws IOException
	 */
	public HashMap<String, String> convertTimezoneDatatoMap(String path) throws IOException {
		HashMap<String, String> timezoneMap = new HashMap<String, String>();
		// -33.86,151.21,39|Sydney|Australia/Sydney

		BufferedReader br1 =getFileContentinBuffer(path);

		String line1 = br1.readLine();
		while (line1 != null) {
			if (!timezoneMap.containsKey(line1.split(WeatherConstants.ROW_SPLIT_SEPERATOR)[0]))
				timezoneMap.put(line1.split(WeatherConstants.ROW_SPLIT_SEPERATOR)[0], line1);
			line1 = br1.readLine();
		}
		return timezoneMap;
	}
	
	/**
	 * convertWeatherDatatoMap converts the input weather data by lookup timezonemap into Hashmap of String and WeatherDataDTo
	 * input weather data contains location|position|localtimeutc|conditions|temparature|position|humidity 
	 * @param path
	 * @param timezoneMap
	 * @return HashMap<String, WeatherDataDto>
	 * @throws IOException
	 * @throws ParseException
	 */
	public HashMap<String, WeatherDataDto> convertWeatherDatatoMap(String path,HashMap<String, String> timezoneMap) throws IOException, ParseException {
		HashMap<String, WeatherDataDto> weatherMap = new HashMap<String, WeatherDataDto>();

		BufferedReader br =getFileContentinBuffer(path);

		String line = br.readLine();
		while (line != null) {
			WeatherDataParser objParser = new WeatherDataParser();
			WeatherDataDto objWeatherDataDto = objParser
					.parseWeatherTimezoneRecord(line, timezoneMap);
			weatherMap.put(
					objWeatherDataDto.getCity() + WeatherConstants.ROW_SEPERATOR
							+ objWeatherDataDto.getDateval() + WeatherConstants.ROW_SEPERATOR
							+ objWeatherDataDto.getTime(), objWeatherDataDto);
			line = br.readLine();
		}
		
		return weatherMap;
	}
}
