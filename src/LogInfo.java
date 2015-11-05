
public class LogInfo {

    private String user;

    LogInfo()
    {
        Login log = new Login();
        user = log.getCurrentUser();
    }

    String getUser()
    {
        return user;
    }
}
