package dev.t3rry327.hypix.api.nex1.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static MongoClient mongoClient = null;

    public static void setupConnection() {

        ConnectionString connectionString = new ConnectionString("mongodb+srv://dev:UA.e4-u-XGvwKAr@cluster0.o82l9fw.mongodb.net/?retryWrites=true&w=majority");


        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        mongoClient = MongoClients.create(clientSettings);

    }

    public static MongoDatabase getMongoDB() {
        return mongoClient.getDatabase("hypix");
    }


}