
public class ILogInfo {

    private String user;

    ILogInfo()
    {
        Login log = new Login();
        user = log.getCurrentUser();
    }

    String getUser()
    {
        return user;
    }
}
