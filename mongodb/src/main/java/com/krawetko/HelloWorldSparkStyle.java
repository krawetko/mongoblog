package com.krawetko;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;

/**
 * Created by krawetko on 15/10/14.
 */
public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkStyle.class, "/");

        Spark.get("/", (request, response) ->
        {

            Template helloTemplate = null;
            StringWriter writer = new StringWriter();
            try {
                helloTemplate = configuration.getTemplate("hello.ftl");
                HashMap<String, Object> helloMap = new HashMap<>();
                helloMap.put("name", "Lolek!");

                helloTemplate.process(helloMap, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return writer;
        });

    }
}
