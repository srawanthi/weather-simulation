package com.tcs.weather;

import org.junit.Assert;
import org.junit.Test;

/**
 * TestWeatherSimulator contains testcases related to Weather simulator class
 * @author srawanthi.samala
 *
 */
public class TestWeatherSimulator {
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void TestValidateArgs() throws Exception{
		String[] args = {"Sydney","2015-12-23","16:02:12","src/main/resources/longlatlookup-place-timezone.txt","src/main/resources/2017-07-14-weatherdata.txt"};
		
		WeatherSimulator objWeatherSimulator = new WeatherSimulator();
		Assert.assertTrue(objWeatherSimulator.validateArgs(args));
		
		String[] argslessParameters = {"Sydney","2015-12-23","16:02:12","src/main/resources/longlatlookup-place-timezone.txt"};
		
		boolean exceptionflag = false;
		try{
			objWeatherSimulator.validateArgs(argslessParameters);
		}
		catch(Exception ex){
			exceptionflag =true;
		}
		
		Assert.assertTrue(exceptionflag);
		
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testparseInputDatatoMap() throws Exception{
		String[] args = {"Sydney","2015-12-23","16:02:12","src/main/resources/longlatlookup-place-timezone.txt","src/main/resources/2017-07-14-weatherdata.txt"};
		String timezoneFileLookupPath= args[3];
		String weatherDataInputPath= args[4];
		
		WeatherSimulator objWeatherSimulator = new WeatherSimulator();
		Assert.assertEquals(6,objWeatherSimulator.parseInputDatatoMap(timezoneFileLookupPath, weatherDataInputPath).size());		
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testsetupProcess() throws Exception{
		String[] args = {"Sydney","2015-12-23","16:02:12","src/main/resources/longlatlookup-place-timezone.txt","src/main/resources/2017-07-14-weatherdata.txt"};
		
		Assert.assertTrue(WeatherSimulator.setupProcess(args));		
	}
}
