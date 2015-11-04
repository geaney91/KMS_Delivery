
public class ConcreteInterceptor implements Interceptor {

    @Override
    public void Log(ContextInterceptor c1) {
            //write to file

        //c1 = new ContextInterceptor();
        String user = c1.getUser();
        System.out.print(user);
    }
}
