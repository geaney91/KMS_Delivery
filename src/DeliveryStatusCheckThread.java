import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DeliveryStatusCheckThread implements Runnable {

    private Thread t;

    public DeliveryStatusCheckThread(){}

    public void run(){
        Login log = new Login();
        String user = log.getCurrentUser(); //Gets currently logged in user.
        Library library = new Library();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Date current = new Date();
        Date previous = new Date();
        ArrayList<String> local = library.getTrackingList();    //Gets tracking details.
        String result = "";
        while(true) //Ensures that this runs constantly while the application is running.
        {
            for (int i = 0; i < local.size(); i++)
            {
                String[] details = local.get(i).split(",");
                if (details[0].equals(user))    //Gets post associated with the current user.
                {
                    try
                    {
                        Date delivery = dateFormat.parse(details[3]);   //Gets delivery date.
                        if(delivery.after(previous) && current.after(delivery)){    //If delivery falls between current time and 10 seconds previous.
                            result += "" + details[1] + " delivered\n"; //Adds to result if delivered.
                        }
                    }
                    catch (ParseException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            }
            if (!result.equals(""))
            {
                JOptionPane.showMessageDialog(null, result + "");   //Pops dialog with id of delivered post.
            }
            try
            {
                previous = current; //Sets previous time to current time.
                Thread.sleep(10000);    //Let thread sleep for 10 seconds.
                current = new Date();   //Set current to current time.
                result = "";    //Reset result.
            }
            catch (InterruptedException e)
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
