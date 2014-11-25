package com.orange.devheure.javaapp;
import java.net.URI;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.joda.time.DateTime;

import com.couchbase.client.CouchbaseClient;
import com.google.gson.Gson;
import com.orange.devheure.couchbasesession.model.CouchbaseElement;

/**
 * 
 */

/**
 * @author ahdi7503
 * 
 */
public class CreateElementApp {

	private static final String BUCKET = "devheure";

	private static final String COUCHBASE_CLUSTER_WIN7 = "http://10.184.54.135:8091/pools";
	private static final String COUCHBASE_CLUSTER_MACBOOK = "http://10.184.49.205:8091/pools";
	
	final static Gson gson = new Gson();

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		ArrayList<URI> nodes = new ArrayList<URI>();

		// Add one or more nodes of your cluster (exchange the IP with yours)
		nodes.add(URI.create(COUCHBASE_CLUSTER_WIN7));
		nodes.add(URI.create(COUCHBASE_CLUSTER_MACBOOK));

		// Try to connect to the client
		CouchbaseClient client = null;
		try {
			client = new CouchbaseClient(nodes, BUCKET, "");
		} catch (Exception e) {
			System.err.println("Error connecting to Couchbase: "
					+ e.getMessage());
			System.exit(1);
		}

		for (int i=0;i<10;i++)
			addElementToCluster(client);
		
		// Shutdown the client
		client.shutdown();
		
	}

	/**
	 * @param client
	 */
	private static void addElementToCluster(CouchbaseClient client) {
		// Set your first document with a key of "hello" and a value of
		// "couchbase!"
		CouchbaseElement ce = new CouchbaseElement();
		Random ran = new Random();
		int x = ran.nextInt(6) + 5;
		ce.setKey(UUID.randomUUID().toString());
		ce.setValue(x);
		ce.setDate(new DateTime().toString());
		client.set(ce.getKey(), gson.toJson(ce));

		// Return the result and cast it to string
		String result = (String) client.get(ce.getKey());
		System.out.println(result);
	}

}
