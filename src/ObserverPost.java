import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ObserverPost extends Observer {
    public ObserverPost(ObserverSubject observerSubject){
        this.observerSubject = observerSubject;
        this.observerSubject.attach(this);
    }

    @Override
    public void update()
    {
        Login log = new Login();
        String user = log.getCurrentUser();
        Date logIn = observerSubject.getState();
        Library library = new Library();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        ArrayList<String> local = library.getTrackingList();
        for (int i = 0; i < local.size(); i++) {
            String[] details = local.get(i).split(",");
            if(details[0].equals(user))
            {
                try {
                    Date delivery = dateFormat.parse(details[3]);
                    long diff = delivery.getTime() - logIn.getTime();
                    if (diff < 0) {
                        System.out.println("Post Id: " + details[1] + " has been delivered\n");
                    } else {
                        System.out.println("Post Id: " + details[1] + " is due to be delivered on " + details[3]);
                    }
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
