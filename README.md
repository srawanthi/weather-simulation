# Weather Simulator - POC

Weather Simulator project provides the prototype of a program which artificially simulates the weather and outputs weather data in a standard format. The prediction is based on assumption that weather measurements are slighltly same by calculating mean and average of historical weather data.

### Documents
  - Documents Location : https://github.com/srawanthi/weather-simulator/tree/master/doc
  --  Doc folder contains 2 documents
      - Weather Data Simulation Design Document.doc explains about the weather simulator module, assumptions and constaints, design of the module
      - Weather Data simulation Installation.doc explains about installation of Pre-Requisites, Project deployment and running steps, Unit test cases

### Pre-Requisites
   - Java
   - Maven
   - GitHub

### Data Sets
  - World_Cities_Coordinators.txt file contains world wide cities coordinator information ex. latitude, longitude   
  - threelocations_365days_hystoricaldata.txt contains weather data for 365 days for the below locations.
    - Sydney
    - Hyderabad
    - Los Angeles

Hystorical weather data downloaded from [wunderground history](https://www.wunderground.com/history/)
### Functional Design
Weather Simulator predicts weather data by using two techniques
  - Longitude and Latitude Adjustments
      - Get the latitude and longitude from worldcoitiescoordinators.txt and try to get the historical year or previous day weather measurements. 
      - If we didn't get the historical data with respect to latitude and longitude then we are removing decimal points to get the weather historical data
      - Still if we didn't get then we are removing one character from latitude and longitude and tried to capture weather historical data from dataset. Still If we didn't get then consider one key(latitude and longitude) from exisitng dataset
  - by based on historical Year wise here slightly using sliding window algorithm
      - check the previous day and calculate average of max and min. consider the mean.
      - check the previous year. Here mainly used recursive alogorithm to check the historical year data.
      - This Project can generate weather report from 2000 to 2025 by based on year for the given input

This module calculates the average or mean value for each weather measurement (temperature, pressure and humidity) by using above two techniques.

### Weather Predictor Class
WeatherDataPredictor class is mainly used to predict weather factors. It contains
  - predictYearWiseWeatherData ()
  - mapWeatherData()
  - predictlonglat()
  - lookupinHistoryYearData()
  - lookupinFutureYearData()

### Input Arguments
Input as city, country as city, country,  datewithtime,  worldcitiescoordinatorslistpath,  weatherhistoricalyearwisedataPath
  - Ex1: Sydney Australia 2015-12-23T16:02:13Z < worldcitiescoordinatorslistpath > < weatherhistoricalyearwisedataPath >
  - Ex2: Sydney Australia 2015-12-23 < worldcitiescoordinatorslistpath > < weatherhistoricalyearwisedataPath >
Sydney 2015-12-23 16:02:13


### Assumptions
  - This module is developed as POC by considering sample data sets to derive the weather factors.
  - Mentioned remaining assumptions in [Weather Data Simulation Design Document](https://github.com/srawanthi/weather-simulator/tree/master/doc)

### Enhancement Plans
  - Create inputConfiguration.conf file to feed the arguments to the program.
  - Include log4j for displaying log info in this module
  - Implement mathematical prediction algorithms to calculate mean, deviation and variation by gathering all historical years data to get the accurate weather factors. 
  - Load more weather historical data to get more accuracy.
  - Develop Spark application by feeding the weather historical data. Here we can have in-memory computation.
  - Implement Spark streaming application to read the data from queues/ file-path by using custom receiver.

### References
##### Testcases
  - Junit test cases: weather-simulator/src/test/java

##### Documents
  - https://github.com/srawanthi/weather-simulator/tree/master/doc