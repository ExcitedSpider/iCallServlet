package servletserver;

import com.google.gson.Gson;
import service.GroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class JoinClass extends HttpServlet {

    private HashMap<String,String> form = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        try{
            GroupService.joinGroup(request);
            form.put("error","0");
        }catch (Exception e){
            e.printStackTrace();
            form.put("error","3");
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
