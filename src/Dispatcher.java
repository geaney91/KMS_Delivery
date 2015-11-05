import java.util.ArrayList;

public class Dispatcher {

    private static ArrayList<Interceptor> interceptors = new ArrayList<Interceptor>();
   // private ContextInterceptor c1 = new ContextInterceptor();

    static void register(Interceptor i)
    {
        interceptors.add(i);
    }

    void dispatch(ContextInterceptor c1)
    {
        iterate_list(c1);
    }

    void iterate_list(ContextInterceptor c1)
    {
        for(int i = 0; i< interceptors.size();i++)
        {
            interceptors.get(i).Log(c1);
        }
    }
}
