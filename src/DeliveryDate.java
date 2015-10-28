import java.util.Calendar;
import java.util.Date;
public class DeliveryDate implements Strategy {
    @Override
    public Date DisplayDate(String deliveryType, String country)
    {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        switch (deliveryType.toLowerCase())
        {
            case "standard":
                switch (country.toLowerCase())
                {
                    case "ireland":
                        c.add(Calendar.DATE,2);
                        break;
                    case "uk":
                        c.add(Calendar.DATE,3);
                        break;
                    case "europe":
                        c.add(Calendar.DATE,5);
                        break;
                }
                break;
            case "express":
                switch (country.toLowerCase())
                {
                    case "ireland":
                        c.add(Calendar.DATE,1);
                        break;
                    case "uk":
                        c.add(Calendar.DATE,2);
                        break;
                    case "europe":
                        c.add(Calendar.DATE,4);
                        break;
                }
                break;
            case "super":
                switch (country.toLowerCase())
                {
                    case "ireland":
                        c.add(Calendar.DATE,1);
                        break;
                    case "uk":
                        c.add(Calendar.DATE,1);
                        break;
                    case "europe":
                        c.add(Calendar.DATE,2);
                        break;
                }
                break;
        }
        date = c.getTime();
        return date;
    }
}
