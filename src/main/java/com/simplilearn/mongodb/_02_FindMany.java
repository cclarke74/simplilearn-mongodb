package com.simplilearn.mongodb;

import static com.mongodb.client.model.Filters.gt;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;

public class _02_FindMany {

	public static void main(String[] args) {

		String uri = "mongodb://localhost:27017/?appName=MongoDB+Compass&directConnection=true&serverSelectionTimeoutMS=2000";

		try (MongoClient mongoClient = MongoClients.create(uri);) {// Open a connection with MongoDB.

			MongoDatabase database = mongoClient.getDatabase("blog"); // Open the database.

			MongoCollection<Document> collection = database.getCollection("posts"); // Open the Collection (table).

			/*
			 * Create a cursor with the result of the query
			 * The cursor must be closed to avoid memory leaks
			 */
			try (MongoCursor<Document> cursor = collection.find(gt("likes", 4)) // Query by title 'eq' Post Title 1.
													 .sort(Sorts.descending("likes")) // Sorts descendingly by number of likes.
													 .iterator();){	
				/*
				 * Loop over the cursor to get the data.								 										 
				 */
				while(cursor.hasNext()) {
					System.out.println(cursor.next().toJson());
				}
			}
			
		}
		
	}

	
	

}
