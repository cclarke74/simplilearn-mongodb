package com.simplilearn.mongodb;

import java.util.Arrays;
import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

public class _03_InsertOne {

	public static void main(String[] args) {

		String uri = "mongodb://localhost:27017/?appName=MongoDB+Compass&directConnection=true&serverSelectionTimeoutMS=2000";

		try (MongoClient mongoClient = MongoClients.create(uri);) {// Open a connection with MongoDB.

			MongoDatabase database = mongoClient.getDatabase("blog"); // Open the database.

			MongoCollection<Document> collection = database.getCollection("posts"); // Open the Collection (table).
			
			try {
				InsertOneResult result = collection.insertOne(new Document() 
						.append("_id", new ObjectId())
						.append("title", "Post Title 6")
						.append("body", "Body of post.")
						.append("category", "Technology")
						.append("likes", 3)
						.append("tags", Arrays.asList("news","events"))
						.append("date", new Date()));
				System.out.println("Success!, Document id " + result.getInsertedId());
				
			} catch(MongoException me) {
				System.out.println("Error while Inserting! " + me.getMessage());
				
			}
						
		}
		
	}

}
