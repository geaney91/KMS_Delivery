import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Michael local on 05/11/2015.
 */
public class DeliveryStatusCheck implements Runnable {

    private Subject subject;
    private Date login;
    private Date current;
    private Thread t;

    public DeliveryStatusCheck(Subject subject, Date login){

        this.subject=subject;
        this.login = login;
    }

    public void run(){
        Login log = new Login();
        String user = log.getCurrentUser();
        Library library = new Library();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Date current = new Date();
        Date previous = new Date();
        ArrayList<String> local = library.getTrackingList();
        String result = "";
        while(true)
        {
            for (int i = 0; i < local.size(); i++)
            {
                String[] details = local.get(i).split(",");
                if (details[0].equals(user))
                {
                    try
                    {
                        Date delivery = dateFormat.parse(details[3]);
                        //long diff1 = delivery.getTime() - login.getTime();
                        //long diff = delivery.getTime() - current.getTime();
                        //if (diff1 > 0 && diff < 0)
                        if(delivery.after(previous) && current.after(delivery)){
                            result += "" + details[1] + " delivered\n";
                        }
                    }
                    catch (ParseException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            }
            if (result != "")
            {
                JOptionPane.showMessageDialog(null, result + "");
            }
            try
            {
                Thread.sleep(10000);
                previous = current;
                current = new Date();
                result = "";
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt(); // restore interrupted status
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
