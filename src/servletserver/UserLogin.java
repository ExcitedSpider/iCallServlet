package servletserver;

import com.google.gson.Gson;
import dbserver.qe.com.TokenDAO;
import dbserver.qe.com.UserProfileDAO;
import dbserver.qe.com.bean.Token;
import dbserver.qe.com.bean.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UserServerServlet")
public class UserLogin extends HttpServlet {
    private static Logger logger = LogManager.getLogger(UserLogin.class.getName());
    private Map<String,String> form = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //报表
        form = new HashMap<>();

        tryLogin(request);
        handleToken(request);

        Gson json = new Gson();
        logger.debug(form);
        try(PrintWriter writer = response.getWriter()){
            json.toJson(form,writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.println("<h1>You shouldn't see this page.</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tryLogin(HttpServletRequest request){
        String username = request.getParameter("account");
        String password = request.getParameter("password");

        UserProfileDAO.start();
        User user = UserProfileDAO.findUserByAccount(username);
        String userPassword = "none";
        //这是一个不会被匹配到的字符串，因为密码必须要8-18位，用来防止NullPointerException
        if (user != null)
            userPassword = user.getPassword();
        if (password.equals(userPassword)) {
            form.put("login","true");
            } else {
            form.put("login","false");
            }
    }

    private void handleToken(HttpServletRequest request){
        String account = request.getParameter("account");
        String imei = request.getParameter("imei");
        long timestamp=new Date().getTime();

        Token token = new Token(account,imei,timestamp);

        TokenDAO.start();
        TokenDAO.insertOnNoDuplicatedKey(token);
        TokenDAO.close();

        if(!imei.equals("imei")){
            form.put("token","true");
        }else {
            form.put("token","false");
        }
    }
}
