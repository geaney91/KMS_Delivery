
public class Parcel implements Post {
    private String type = "";
    private double weight = 0.0;
    private String country = "";
    private String delivery = "";

    @Override
    public void create(){
        System.out.print("Parcel created");
    }

}
