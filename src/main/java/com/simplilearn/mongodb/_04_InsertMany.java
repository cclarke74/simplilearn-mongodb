package com.simplilearn.mongodb;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;

public class _04_InsertMany {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(_04_InsertMany.class);	public static void main(String[] args) {

		String uri = "mongodb://localhost:27017/?appName=MongoDB+Compass&directConnection=true&serverSelectionTimeoutMS=2000";

		try (MongoClient mongoClient = MongoClients.create(uri);) {// Open a connection with MongoDB.

			MongoDatabase database = mongoClient.getDatabase("blog"); // Open the database.

			MongoCollection<Document> collection = database.getCollection("posts"); // Open the Collection (table).
			
			List<Document> posts = Arrays.asList(
											new Document()
												.append("_id", new ObjectId())
												.append("title", "Post Title Hello 6")
												.append("body", "Body of post.")
												.append("category", "Technology")
												.append("likes", 6)
												.append("tags", Arrays.asList("news","events"))
												.append("date", new Date().toString()),
											new Document()
												.append("_id", new ObjectId())
												.append("title", "Post Title Hello 7")
												.append("body", "Body of post.")
												.append("category", "Technology")
												.append("likes", 7)
												.append("tags", Arrays.asList("news","events"))
												.append("date", new Date().toString())
												);
					
			
			
				InsertManyResult result = collection.insertMany(posts); 
					
				logger.debug("Success!, Document ids " + result.getInsertedIds());
				
			} catch(MongoException me) {
				logger.debug("Error while Inserting! " + me.getMessage());
				
			}
						
		}
		
	}


