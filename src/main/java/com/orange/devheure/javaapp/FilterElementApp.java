package com.orange.devheure.javaapp;

import java.net.URI;
import java.util.ArrayList;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.ComplexKey;
import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.View;
import com.couchbase.client.protocol.views.ViewResponse;
import com.google.gson.Gson;

public class FilterElementApp {

	private static final String VIEW_FILTER = "filterGoodResult";

	private static final String DESIGN_DOC = "devheure";

	private static final String BUCKET = "devheure";

	private static final String COUCHBASE_CLUSTER_WIN7 = "http://10.184.54.135:8091/pools";
	private static final String COUCHBASE_CLUSTER_MACBOOK = "http://10.184.49.205:8091/pools";

	final static Gson gson = new Gson();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

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

		// 1: Get the View definition from the cluster
		View view = client.getView(DESIGN_DOC, VIEW_FILTER);

		// 2: Create the query object
		Query query = new Query();

		// Define the query params
		query.setIncludeDocs(true) // include the full documents
				.setLimit(20) // only show 20 results
				.setRangeStart(ComplexKey.of(9)).setRangeEnd(ComplexKey.of(10));

		// 3: Query the cluster with the view and query information
		ViewResponse result = client.query(view, query);

		System.out.println("Number of result : " + result.size());
		
		// Shutdown the client
		client.shutdown();
	}

}
