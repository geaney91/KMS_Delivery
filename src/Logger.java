import java.io.IOException;

public class Logger implements Interceptor  {

    @Override
    public void Log(LogInfo c1)
    {
        String user = c1.getUser();
        //System.out.print(user);
        Library library = new Library();
        try {
            library.writeFile(user);
        }
        catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
}
