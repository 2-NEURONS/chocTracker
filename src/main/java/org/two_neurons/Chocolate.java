package org.two_neurons;

public class Chocolate
{
   private int id , qty ;
   private String name;

    public Chocolate()
    {
    }

    public int getQty()
    {
        return qty;
    }
    public void setQty(int qty)
    {
        this.qty = qty;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String toGgson()
    {
        return new ChocToGson().render(this);
    }

}
