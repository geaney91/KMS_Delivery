import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DeliveryStatusCheck implements Runnable {
    private Thread t;

    public DeliveryStatusCheck() {}

    public void run()
    {
        Login log = new Login();
        String user = log.getCurrentUser();
        Library library = new Library();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Date current = new Date();
        Date previous = new Date();
        ArrayList<String> local = library.getTrackingList();    //Puts list of tracking details into local ArrayList
        String result = "";
        while(true) //Ensures this runs until the application is closed.
        {
            for (int i = 0; i < local.size(); i++)  //Iterate through list of tracking details
            {
                String[] details = local.get(i).split(",");
                if (details[0].equals(user))    //Only looks at post created by the current user
                {
                    try
                    {
                        Date delivery = dateFormat.parse(details[3]);   //Gets delivery date
                        if(delivery.after(previous) && current.after(delivery)) //Check to see if post has been delivered in the last 10 seconds
                        {
                            result += "" + details[1] + " delivered\n"; //Adds to the result string
                        }
                    }
                    catch (ParseException e)    //Catches parsing exceptions
                    {
                        System.out.println(e.getMessage());
                    }
                }
            }
            if (result != "")
            {
                JOptionPane.showMessageDialog(null, result + "");   //Pops dialogbox with post id if it has been delivered.
            }
            try
            {
                previous = current; //Sets previous date to current date before resetting current date in 10 seconds.
                Thread.sleep(10000);    //Put thread to sleep for 10 seconds
                current = new Date();
                result = "";
            }
            catch (InterruptedException e)  //Catch if thread is interrupted
            {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void start()
    {
        if (t == null)
        {
            t = new Thread (this);
            t.start ();
        }
    }
}
