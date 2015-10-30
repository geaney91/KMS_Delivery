import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class DeliveryDate implements Strategy {
    @Override
    public String DisplayDate(String deliveryType, String country)
    {
        String dt = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        dateFormat.format(date);

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
        dt = dateFormat.format(c.getTime());
        return dt;
    }
}
