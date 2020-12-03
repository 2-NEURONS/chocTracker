package org.two_neurons;

import com.google.gson.Gson;
import spark.ResponseTransformer;
import javax.swing.*;

public class ChocToGson implements ResponseTransformer
{


        private Gson gson = new Gson();
        String chocGson;
        @Override
        public String render(Object entity)
        {

            try
            {
                chocGson = gson.toJson(entity);
            }
            catch(Exception exception)
            {
                JOptionPane.showMessageDialog(null,"error occurred transforming chocolate to Gson");
                exception.printStackTrace();
            }
            return chocGson;

        }


}
