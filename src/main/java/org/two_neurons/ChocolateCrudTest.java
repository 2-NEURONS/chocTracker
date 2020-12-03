package org.two_neurons;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChocolateCrudTest
{
    Jdbi jdbi;
    Properties properties;

    @BeforeAll
    void databaseSetup()
    {
        properties = new Properties();
        properties.setProperty("username","coder");
        properties.setProperty("password","coder123");
        jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/chocolate",properties);
        jdbi.installPlugin(new SqlObjectPlugin());

    }

    @BeforeEach
    void beforeEach()
    {
        jdbi.useHandle(h -> h.execute("delete from chocolate"));
    }

    @Test
    public void shouldBeAbleToCreateChocolateBar()
    {

        jdbi.useHandle(h ->
        {

            ChocolateCrudAPI chocolateAPI = h.attach(ChocolateCrudAPI.class);
            assertEquals(0, chocolateAPI.allChocolate().size());
            chocolateAPI.createChoc("DarkChoc", 7);
            assertEquals(1, chocolateAPI.allChocolate().size());

        });

    }

    @Test
    public void shouldBeAbleToFindChocolateBarByName()
    {

        jdbi.useHandle(h ->
        {

            ChocolateCrudAPI chocCrud = h.attach(ChocolateCrudAPI.class);
            chocCrud.createChoc("LunchBar",7);
            Chocolate choc = chocCrud.getChocByName("LunchBar");
            assertEquals(7, choc.getQty());
            assertEquals("LunchBar", choc.getName());

        });

    }

}
