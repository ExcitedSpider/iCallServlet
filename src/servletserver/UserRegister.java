package servletserver;

import com.google.gson.Gson;
import dbserver.qe.com.UserProfileDAO;
import dbserver.qe.com.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserRegister extends HttpServlet {
    private Map<String,String> form;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = buildUser(request);

        form = new HashMap<>();

        if(!checkIfExist(user)){
            UserProfileDAO.start();
            UserProfileDAO.addUser(user);
            UserProfileDAO.close();

            System.out.println("Success Create User:"+user.getAccount());
            form.put("register","true");
        }else {

            System.out.println("Failed Create User:"+user.getAccount());
            form.put("register","false");
        }
        try(PrintWriter writer = response.getWriter()){
            new Gson().toJson(form,writer);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = null;
        try{
            writer = response.getWriter();
            writer.println("<h1>You shouldn't see this page.</h1>");
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            writer.close();
        }
    }

    private boolean checkIfExist(User user){
        if(user.getAccount()!=null)
            System.out.println(user.getAccount());
        UserProfileDAO.start();
        User user1= UserProfileDAO.findUserByAccount(user.getAccount());
        if(user1!=null){
            UserProfileDAO.close();
            return true;
        }else {
            UserProfileDAO.close();
            return false;
        }

    }

    private User buildUser(HttpServletRequest request){
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String school = request.getParameter("school");
        String job = request.getParameter("job");

        return new User(account,password,school,job);
    }
}
