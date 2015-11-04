
public class ConcreteFramework {

   /* void service()
    {
        Dispatcher d = new Dispatcher();
        d.dispatch();

    }*/
    void event()
    {
        ContextInterceptor c = new ContextInterceptor();
        Dispatcher d = new Dispatcher();
        d.dispatch(c);

    }
}
