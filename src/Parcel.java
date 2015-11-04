
public class Parcel implements Post {
    private double price = 2.0;

    @Override
    public double GetPrice()
    {
        return price;
    }

    @Override
    public void create(){
        System.out.print("Parcel created");
    }

}
