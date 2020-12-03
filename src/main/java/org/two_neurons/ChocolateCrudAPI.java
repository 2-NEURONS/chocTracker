package org.two_neurons;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface ChocolateCrudAPI<Chococlate>
{
    @SqlUpdate("insert into chocolate (name, qty) values (?, ?)")
    void createChoc(String name, int qty);
    @SqlQuery("select * from chocolate")
    @RegisterBeanMapper(Chocolate.class)
    List<Chocolate> allChocolate();
    @SqlUpdate("update chocolate set (name, qty) values (:name, :qty) where id = :id")
    void chocEdit(@Bind("id") int id, @Bind("name") String name, @Bind("qty") int qty);
    @SqlQuery("select * from chocolate where name = ?")
    @RegisterBeanMapper(Chocolate.class)
    Chocolate getChocByName(@Bind("name") String name);
    @SqlQuery("select * from chocolate where id = ?")
    @RegisterBeanMapper(Chocolate.class)
    Chocolate getChocById(@Bind("id") int id);
    @SqlUpdate("delete from chocolate where id = ?")
    @RegisterBeanMapper(Chocolate.class)
    void chocDelete(@Bind("id") int id);





}
