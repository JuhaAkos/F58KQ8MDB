package client;

import org.bson.Document;

import com.mongodb.BasicDBObject;
//import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.lt;

import java.util.Arrays;
import java.util.List;

public class proba1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("MDB_F58KQ8");
        com.mongodb.client.MongoCollection<Document> table = db.getCollection("meal");
        Document doc = new Document();
        BasicDBObject query = new BasicDBObject();

		System.out.println("Step 1 *************************");
            
        query = new BasicDBObject();
        doc = table.find().first();
        String res = doc.getString("tipus");
        System.out.println(res);
        
		System.out.println("Step 2 *************************");
        query = new BasicDBObject();
        for (Document doc2 :  table.find()) {
        	System.out.println( doc2.getString("tipus") );
        }
        
		System.out.println("Step 3 *************************");
        
        MongoCursor<Document> cursor = (MongoCursor<Document>) table.find(lt("name", "Margaréta")).iterator();
        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
		System.out.println("Step 4 *************************");

		Document ncar = new Document("_id", 111)
			    .append("name", "Margaréta")
			    .append("price", 1410)
			    .append("category", "Pizza");
		
		table.insertOne(ncar);

        System.out.println("Step 5 *************************");

        Document toUpdate = new Document();
        toUpdate.put("name", "Margaréta");

        Document updateTo = new Document();
        updateTo .put("name", "Margaréta2");

        Document updateObject = new Document();
        updateObject.put("$set", updateTo);

        table.updateOne(toUpdate, updateObject);

        System.out.println("Step 6 *************************");

        Document deleteQuery = new Document();
        deleteQuery.put("name", "Margaréta2");

        table.deleteOne(deleteQuery);

        System.out.println("Step 7 *************************");

        List<Document> mealList = Arrays.asList(
                    new Document().append("name", "tesztetel1").append("price", 1).append("category", "Pizza"),
                    new Document().append("name", "tesztetel2").append("price", 2).append("category", "Salad"));
        table.insertMany(mealList);        
		
		System.out.println("OK *************************");

		mongoClient.close();

        
	}
	

}