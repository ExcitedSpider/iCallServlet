package servletserver;

import com.google.gson.Gson;
import dbserver.qe.com.bean.Notification;
import service.NotificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class QueryNotification extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("json/application;charset=UTF-8");

        Gson gson = new Gson();
        List<Notification> list = null;
        PrintWriter writer = null;
        try{
            writer = response.getWriter();
            list = NotificationService.QueryNotification(request);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            gson.toJson(list,writer);
            if (writer != null) {
                writer.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
