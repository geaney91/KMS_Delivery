import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StrategyDeliveryDateExpress implements Strategy{
    @Override
    public String DisplayDate(String country)
    {
        String dt;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Calendar c = Calendar.getInstance();

        //For express delivery type, adds number of days until delivery depending on destination.
        if(country.toLowerCase().equals("ireland"))
            c.add(Calendar.DATE,1);
        else if(country.toLowerCase().equals("uk"))
            c.add(Calendar.DATE,2);
        else if(country.toLowerCase().equals("europe"))
            c.add(Calendar.DATE,4);

        dt = dateFormat.format(c.getTime());
        return dt;
    }
}
