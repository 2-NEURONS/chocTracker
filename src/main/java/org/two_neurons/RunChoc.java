package org.two_neurons;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static spark.Spark.port;
import static spark.Spark.*;


public class RunChoc
{
    private static Properties propertiesDB = new Properties(); // username and password properties
    private static HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

    public static void main(String[] args)
    {
        //=========================================================================================
        propertiesDB.setProperty("username","postgres"); // db username
        propertiesDB.setProperty("password","coder123"); // db password
        ConnectDB dbc = new ConnectDB("jdbc:postgresql://localhost:5432/chocolate", propertiesDB); // db setup
        Jdbi jdbi = Jdbi.create(dbc.getUrlDB(), dbc.getPropertiesDB()); // create an instance of jdbi so you can use it later to send queries
        jdbi.installPlugin(new SqlObjectPlugin()); // bcz i will using sql oject annotations
        ChocolateRoute chocRoute = new ChocolateRoute(jdbi); // my routes
        //=========================================================================================
        port(8080); // start at 8080
        post("/create",chocRoute::createCocHandler);
        get("/find/:id",chocRoute::chocByIdHandler);
        get("/chocolates",chocRoute::displayAllChocHanlder);
        put("/edit/chocolate",chocRoute::chocEditHandler);
        get("/chocolate/:name",chocRoute::chocByNameHandler);
        delete("/delete/:id",chocRoute::chocDeleteHandler);
    }

}
