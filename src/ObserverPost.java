/**
 * Created by 09006819 on 22/10/2015.
 */
public class ObserverPost extends Observer {
    public ObserverPost(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Binary String: " +( subject.getState() ) );
    }
}
