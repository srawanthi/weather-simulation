package com.tcs.weather.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import com.tcs.weather.constants.WeatherConstants;
import com.tcs.weather.dto.WeatherDataDto;

/**
 * @author srawanthi.samala WeatherDataParser parse Weather input data by
 *         considering 2 input files as timezoneFileLookupPath,
 *         weatherDataInputPath and generates WeatherDataDto
 */
public class WeatherDataParser {

	/**
	 * parseWeatherTimezoneRecord parse the weather data input line by lookup
	 * timezone map and generates the WeatherDataDto with required parameters
	 * 
	 * @param weatherline
	 * @param tzlonglatMap
	 * @return WeatherDataDto
	 * @throws ParseException
	 */
	public WeatherDataDto parseWeatherTimezoneRecord(String weatherline,
			HashMap<String, String> tzlonglatMap) throws ParseException {
		WeatherDataDto objWeatherDto = parseWeatherRecord(weatherline);
		String lookupline = tzlonglatMap.get(objWeatherDto.getPosition());
		objWeatherDto = parseTimeZoneRecord(lookupline, objWeatherDto);

		return objWeatherDto;
	}

	/**
	 * parseWeatherRecord parse weather data input line and generated
	 * WeatherDataDto
	 * 
	 * @param weatherline
	 * @return WeatherDataDto
	 */
	public WeatherDataDto parseWeatherRecord(String weatherline) {
		String[] weathersplit = weatherline
				.split(WeatherConstants.ROW_SPLIT_SEPERATOR);
		WeatherDataDto objWeatherDto = new WeatherDataDto();
		if (weathersplit.length == 7) {
			objWeatherDto.setLocation(weathersplit[0]);
			objWeatherDto.setPosition(weathersplit[1]);
			objWeatherDto.setLocaltimeutc(weathersplit[2]);
			objWeatherDto.setConditions(weathersplit[3]);
			objWeatherDto.setTemparature(weathersplit[4]);
			objWeatherDto.setPressure(weathersplit[5]);
			objWeatherDto.setHumidity(weathersplit[6]);
		}
		return objWeatherDto;
	}

	/**
	 * parseTimeZoneRecord lookup data into timezone file and generated the
	 * required fields in WeatherDataDto
	 * 
	 * @param longlatrec
	 * @param objWeatherDto
	 * @return WeatherDataDto
	 * @throws ParseException
	 */
	public WeatherDataDto parseTimeZoneRecord(String longlatrec,
			WeatherDataDto objWeatherDto) throws ParseException {

		String[] longlatsplit = longlatrec
				.split(WeatherConstants.ROW_SPLIT_SEPERATOR);
		if (longlatsplit.length == 3) {
			objWeatherDto.setCity(longlatsplit[1]);
			objWeatherDto.setTimezone(longlatsplit[2]);
		}
		// UTC standard timezone to running Operating system timezone
		SimpleDateFormat dateformater = new SimpleDateFormat(
				WeatherConstants.TIME_yyyy_MM_dd_T_HH_mm_ss_Z);
		dateformater.setTimeZone(TimeZone
				.getTimeZone(WeatherConstants.TIME_UTC_TIMEZONE));
		Date timeZoneDate = dateformater.parse(objWeatherDto.getLocaltimeutc());
		TimeZone tz = TimeZone.getTimeZone(objWeatherDto.getTimezone());
		// System.out.println("UTC standard timezone "+objWeatherDto.getLocaltimeutc());
		// running Operating system timezone to specified timezone in lookup
		// timezone data
		SimpleDateFormat formatter = new SimpleDateFormat(
				WeatherConstants.TIME_yyyy_MM_dd_T_HH_mm_ss_Z);
		formatter
				.setTimeZone(TimeZone.getTimeZone(objWeatherDto.getTimezone()));

		String dataDateString = formatter.format(timeZoneDate);
		objWeatherDto.setLocaltime(dataDateString);

		// System.out.println("specified timezone "+objWeatherDto.getLocaltime());

		String yyyyMMdd = dataDateString.split("T")[0];
		String HHmmss = dataDateString.split("T")[1].split("Z")[0];
		// converts localdate to LocalDateTime to find out timeoffset value in
		// UTC
		LocalDateTime dt = LocalDateTime.of(Integer.parseInt(yyyyMMdd
				.split(WeatherConstants.DATE_FIELD_SEPERATOR)[0]),
				Integer.parseInt(yyyyMMdd
						.split(WeatherConstants.DATE_FIELD_SEPERATOR)[1]),
				Integer.parseInt(yyyyMMdd
						.split(WeatherConstants.DATE_FIELD_SEPERATOR)[2]),
				Integer.parseInt(HHmmss
						.split(WeatherConstants.TIME_FIELD_SEPERATOR)[0]),
				Integer.parseInt(HHmmss
						.split(WeatherConstants.TIME_FIELD_SEPERATOR)[1]),
				Integer.parseInt(HHmmss
						.split(WeatherConstants.TIME_FIELD_SEPERATOR)[2]));
		ZonedDateTime zdt = dt.atZone(tz.toZoneId());
		ZoneOffset zos = zdt.getOffset();
		// replace Z to +00:00
		String offset = zos.getId().replaceAll("Z", "+00:00");
		objWeatherDto.setTimeOffset(offset);
		objWeatherDto.setTime(HHmmss);
		objWeatherDto.setDateval(yyyyMMdd);
		return objWeatherDto;

	}
}
