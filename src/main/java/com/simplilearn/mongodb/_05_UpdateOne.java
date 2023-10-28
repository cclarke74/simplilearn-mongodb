package com.simplilearn.mongodb;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

public class _05_UpdateOne {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(_05_UpdateOne.class);	

	public static void main(String[] args) {

		String uri = "mongodb://localhost:27017/?appName=MongoDB+Compass&directConnection=true&serverSelectionTimeoutMS=2000";

		try (MongoClient mongoClient = MongoClients.create(uri);) {// Open a connection with MongoDB.

			MongoDatabase database = mongoClient.getDatabase("blog"); // Open the database.

			MongoCollection<Document> collection = database.getCollection("posts"); // Open the Collection (table).
			
			Document query = new Document().append("title", "Post Title Hello 9");
			
			Bson updates = Updates.combine(
							Updates.set("category", "Event"),
							Updates.addToSet("tags","hello"),
							Updates.currentTimestamp("date"));
			
			
				UpdateOptions options = new UpdateOptions().upsert(true);
				
					
				UpdateResult result = collection.updateOne(query, updates, options);
						
				logger.debug("Modified Document count: " + result.getModifiedCount());
				logger.debug("Upserted id " + result.getUpsertedId());
				
			} catch(MongoException me) {
				logger.error("Error while Updating! " + me.getMessage());
				
			}
						
		}
		
	}


