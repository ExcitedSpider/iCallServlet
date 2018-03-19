package servletserver;

import com.google.gson.Gson;
import dbserver.qe.com.bean.Group;
import service.GroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class QueryClass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("json/application;charset=UTF-8");

        PrintWriter writer = null;
        List<Group> groups=null;
        Gson gson = new Gson();
        try{
            groups = GroupService.queryGroup(request);
            writer = response.getWriter();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            gson.toJson(groups,writer);
            writer.close();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
