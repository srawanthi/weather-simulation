package com.tcs.weather.dto;

/**
 * 
 * @author srawanthi.samala WeatherDataDto contains all setters and getters
 *         methods
 * 
 */
public class WeatherDataDto {

	// location|position|localtimeutc|conditions|temparature|position|humidity
	private String location;
	private String position;
	private String localtimeutc;
	private String conditions;
	private String temparature;
	private String pressure;
	private String humidity;

	private String city;
	private String localtime;
	private String timezone;
	private String timeOffset;
	private String dateval;
	private String time;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLocaltimeutc() {
		return localtimeutc;
	}

	public void setLocaltimeutc(String localtimeutc) {
		this.localtimeutc = localtimeutc;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getTemparature() {
		return temparature;
	}

	public void setTemparature(String temparature) {
		this.temparature = temparature;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocaltime() {
		return localtime;
	}

	public void setLocaltime(String localtime) {
		this.localtime = localtime;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getTimeOffset() {
		return timeOffset;
	}

	public void setTimeOffset(String timeOffset) {
		this.timeOffset = timeOffset;
	}

	public String getDateval() {
		return dateval;
	}

	public void setDateval(String dateval) {
		this.dateval = dateval;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * return the value as
	 * 
	 * @return String position, conditions, temparature, pressure, humidity,
	 *         city, localtime, timeOffset
	 */
	@Override
	public String toString() {
		return "position: " + position + ", conditions: " + conditions
				+ ", temparature: " + temparature + ", pressure: " + pressure
				+ ", humidity: " + humidity + ", city: " + city
				+ ", localtime: " + localtime + ", timeOffset: " + timeOffset;
	}

}
