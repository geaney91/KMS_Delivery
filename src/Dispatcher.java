import java.util.ArrayList;

public class Dispatcher {

    private static ArrayList<Interceptor> interceptors = new ArrayList<Interceptor>();

    static void register(Interceptor i)
    {
        interceptors.add(i);
    }

    void dispatch(LogInfo c1)
    {
        iterate_list(c1);
    }

    void iterate_list(LogInfo c1)
    {
        for(int i = 0; i < interceptors.size();i++)
        {
            Logger l = (Logger)interceptors.get(i);
            l.Log(c1);
        }
    }
}
