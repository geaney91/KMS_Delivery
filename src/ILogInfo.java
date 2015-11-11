public class ILogInfo { //Retrieves internal information for the concrete interceptor (ILogger) to use.

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
