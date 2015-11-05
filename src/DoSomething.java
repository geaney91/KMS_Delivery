import java.util.Date;

public class DoSomething implements Runnable {

    private Subject subject;
    private Date login;

    public DoSomething(Subject subject, Date login){

        this.subject=subject;
        this.login = login;
    }

    public void run(){
        new ObserverPost(subject);
        subject.setState(login);
    }
}
