import java.io.IOException;

public class ILogger implements Interceptor  {

    @Override
    public void Log(ILogInfo c1)    //Functionality to write log details to log file.
    {
        String user = c1.getUser();
        Library library = new Library();
        try {
            library.writeFile(user);
        }
        catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
}
