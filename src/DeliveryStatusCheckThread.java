import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DeliveryStatusCheckThread implements Runnable {

    private ObserverSubject observerSubject;
    private Date login;
    private Date current;
    private Thread t;

    public DeliveryStatusCheckThread(ObserverSubject observerSubject, Date login){

        this.observerSubject = observerSubject;
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
                previous = current;
                Thread.sleep(10000);
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
