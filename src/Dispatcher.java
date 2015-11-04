import java.util.ArrayList;

public class Dispatcher {

    private static ArrayList<Interceptor> interceptors = new ArrayList<Interceptor>();

    static void register(Interceptor i)
    {
        interceptors.add(i);
    }

    void dispatch(ContextInterceptor c1)
    {

    }
}
