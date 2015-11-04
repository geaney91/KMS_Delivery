
public class ContextInterceptor {

    private String user;

    ContextInterceptor()
    {
        Login log = new Login();
        user = log.getCurrentUser();
    }

 /*   ContextInterceptor(Login log){
       user= log.getCurrentUser();
    }

   void setUser(Login log)
    {

     } */

    String getUser(){
        return user;
    }
}
