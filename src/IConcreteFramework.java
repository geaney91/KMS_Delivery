public class IConcreteFramework {

    void event()
    {
        ILogInfo c = new ILogInfo();    //Create new context object.
        IDispatcher d = new IDispatcher();  //Create new dispatcher object.
        d.dispatch(c);  //Passes context object to dispatcher.
    }
}
