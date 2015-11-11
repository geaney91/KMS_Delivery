
public class IConcreteFramework {

    void event()
    {
        ILogInfo c = new ILogInfo();
        IDispatcher d = new IDispatcher();
        d.dispatch(c);

    }
}
