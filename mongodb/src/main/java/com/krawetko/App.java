package com.krawetko;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Spark;

import java.io.StringWriter;
import java.net.UnknownHostException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient();

        DB database = mongoClient.getDB("course");
        DBCollection collection = database.getCollection("hello");


        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkStyle.class, "/");

        Spark.get("/", (request, response) ->
        {

            Template helloTemplate = null;
            StringWriter writer = new StringWriter();
            try {
                helloTemplate = configuration.getTemplate("hello.ftl");
                DBObject dbObject = collection.findOne();


                helloTemplate.process(dbObject, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return writer;
        });


    }
}
