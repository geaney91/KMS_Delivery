import java.util.ArrayList;

public class IDispatcher {

    private static ArrayList<Interceptor> interceptors = new ArrayList<Interceptor>();

    static void register(Interceptor i)
    {
        interceptors.add(i);
    }

    void dispatch(ILogInfo c1)
    {
        iterate_list(c1);
    }

    void iterate_list(ILogInfo c1)
    {
        for(int i = 0; i < interceptors.size();i++)
        {
            ILogger l = (ILogger)interceptors.get(i);
            l.Log(c1);
        }
    }
}
