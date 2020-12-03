package org.two_neurons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.Jdbi;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.swing.*;

public class ChocolateRoute
{
    private Jdbi jdbi;
    private Chocolate chocolate;
    public ChocolateRoute(Jdbi jdbi)
    {
      setJdbi(jdbi);
      this.chocolate = new Chocolate();
    }
    public Jdbi getJdbi()
    {
        return jdbi;
    }
    public void setJdbi(Jdbi jdbi)
    {
        this.jdbi = jdbi;
    }
    public String createCocHandler(Request request , Response response)
    {
        String chocName = request.queryParams("name");
        int qty = new Integer(request.queryParams("qty"));
         chocolate.setName(chocName);
         chocolate.setQty(qty);
         jdbi.useExtension(ChocolateCrudAPI.class , chocCrud -> chocCrud.createChoc(chocName,qty));
         JOptionPane.showMessageDialog(null,"success");
         return chocolate.toGgson();
    }
    public String displayAllChocHanlder(Request request , Response response)
    {

                return new ChocToGson().render(jdbi.withHandle( handle ->
                {

                   ChocolateCrudAPI choc = handle.attach(ChocolateCrudAPI.class);
                   return choc.allChocolate();
                }
            ));
      }
    public String chocEditHandler(Request request , Response response)
    {
        String chocName = request.queryParams("name");
        int qty = new Integer(request.queryParams("qty"));
        chocolate.setName(chocName);
        chocolate.setQty(qty);
        jdbi.useExtension(ChocolateCrudAPI.class , chocCrud -> chocCrud.createChoc(chocName,qty));
        JOptionPane.showMessageDialog(null,"success");
        return chocolate.toGgson();
    }
    public String chocByNameHandler(Request request , Response response) throws JsonProcessingException
    {
        String chocName = request.params(":name");
        chocolate.setName(chocName);
        return  new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(jdbi.withExtension(ChocolateCrudAPI.class , chocCrud -> chocCrud.getChocByName(chocName)));
    }
    public String chocByIdHandler(Request request , Response response) throws JsonProcessingException
    {

        int id = new Integer(request.params(":id"));
       return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString( jdbi.withExtension(ChocolateCrudAPI.class , chocCrud -> chocCrud.getChocById(id)));
    }
    public String chocDeleteHandler(Request request , Response response) throws JsonProcessingException
    {
        int id = new Integer(request.params(":id"));
        jdbi.useExtension(ChocolateCrudAPI.class , chocCrud -> chocCrud.chocDelete(id));
        response.redirect("/chocolate/all");
        return "";
    }
}
