package servletserver;

import com.google.gson.Gson;
import dbserver.qe.com.bean.Group;
import org.apache.log4j.Logger;
import service.GroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

@WebServlet(name = "CreateClass")
public class CreateClass extends HttpServlet {
    static private Logger logger = Logger.getLogger(CreateClass.class);

    private HashMap<String,String> form = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        try{
            Integer id = GroupService.createGroup(request);
            form.put("error","0");
            form.put("classId",id.toString());
        }catch (Exception e){
            e.printStackTrace();
            form.put("error","3");
            form.put("classID","-1");
        }finally {
            Gson json = new Gson();
            json.toJson(form,writer);
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
