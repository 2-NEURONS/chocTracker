package org.two_neurons;

import java.util.Properties;

public class ConnectDB
{

    //private static final String DATABASE_DRIVER = "jdbc:postgresql://localhost:5432/expense_logs";
    protected   String urlDB;
    protected Properties propertiesDB;

    public ConnectDB(final String url , Properties propertiesDB)
    {
        this.setUrlDB(url);
        this.setPropertiesDB(propertiesDB);
    }

    public String getUrlDB()
    {
        return urlDB;
    }

    public void setUrlDB(String urlDB)
    {
        this.urlDB = urlDB;
    }

    public Properties getPropertiesDB()
    {
        return propertiesDB;
    }

    public void setPropertiesDB(Properties propertiesDB)
    {
        this.propertiesDB = propertiesDB;
    }

    @Override
    public String toString()
    {
        return "DBconnection{" +
                "urlDB='" + urlDB + '\'' +
                ", propertiesDB=" + propertiesDB +
                '}';
    }

}
