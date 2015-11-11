public class DecoratorDeliveryPost extends DecoratorPost {

    public DecoratorDeliveryPost(Post weightedPost, String delivery)
    {
        super(weightedPost);
        if (delivery.equalsIgnoreCase("standard"))
        {
            this.price = 2;
        }
        else if (delivery.equalsIgnoreCase("express"))
        {
            this.price = 4;
        }
        else if (delivery.equalsIgnoreCase("super"))
        {
            this.price = 10;
        }

    }
}
