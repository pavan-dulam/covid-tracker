package com.pavandulam.covidtracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pavandulam.covidtracker.models.locationStats;

@Service
public class covidDataService { 
	public static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<locationStats> allstats = new ArrayList<>();

	@PostConstruct
	@Scheduled(cron = "* 0 * * * *")
	public void fetchVirusData() throws IOException, InterruptedException {

		List<locationStats> newstats = new ArrayList<>();

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
		HttpResponse<String> httpresponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvBodyReader = new StringReader(httpresponse.body());

		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {

			locationStats locstat = new locationStats();
			
			locstat.setState(record.get("Province/State"));
			locstat.setCountry(record.get("Country/Region"));
			int latestCases = Integer.parseInt(record.get(record.size() - 1));
			int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
			locstat.setLatestTotalCases(latestCases);
			locstat.setDiffFromPreviousDay(latestCases - prevDayCases);
			 //System.out.println(locstat);
			newstats.add(locstat);

		}
		this.allstats = newstats;
	}

	public List<locationStats> getAllstats() {
		return allstats;
	}

	public void setAllstats(List<locationStats> allstats) {
		this.allstats = allstats;
	}

	public static String getVIRUS_DATA_URL() {
		return VIRUS_DATA_URL;
	}
}
