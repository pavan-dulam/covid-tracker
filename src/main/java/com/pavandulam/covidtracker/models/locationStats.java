package com.pavandulam.covidtracker.models;

public class locationStats {
	private String country;
	private String state;
	private int latestTotalCases;
	private int DiffFromPreviousDay;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public int getDiffFromPreviousDay() {
		return DiffFromPreviousDay;
	}

	public void setDiffFromPreviousDay(int diffFromPreviousDay) {
		DiffFromPreviousDay = diffFromPreviousDay;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getLatestTotalCases() {
		return latestTotalCases;
	}

	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}

	@Override
	public String toString() {
		return "locationStats [country=" + country + ", state=" + state + ", latestTotalCases=" + latestTotalCases
				+ "]";
	}

}
