
public class ContextInterceptor {

    private String user;

    ContextInterceptor()
    {

    }

    //ContextInterceptor(Login log){
       //user= log.getCurrentUser();
    //}

    void setUser(Login log)
    {
        user = log.getCurrentUser();
    }

    String getUser(){
        return user;
    }
}
