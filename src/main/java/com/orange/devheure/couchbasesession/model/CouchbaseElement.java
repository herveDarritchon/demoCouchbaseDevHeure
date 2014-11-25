package com.orange.devheure.couchbasesession.model;


public class CouchbaseElement {

	private String key;
	
	private int value;
	
	private String date;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param x the value to set
	 */
	public void setValue(int x) {
		this.value = x;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param dateTime the date to set
	 */
	public void setDate(String dateTime) {
		this.date = dateTime;
	}
	
}
