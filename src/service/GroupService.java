package service;

import dbserver.qe.com.GroupDAO;
import dbserver.qe.com.NotificationDAO;
import dbserver.qe.com.bean.Group;
import dbserver.qe.com.bean.Notification;
import dbserver.qe.com.bean.Roster;
import dbserver.qe.com.bean.User;
import dbserver.qe.com.tool.DateTools;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class GroupService {
    public static int createGroup(HttpServletRequest request) throws IOException{
        Group group = new Group();

        group.setName(request.getParameter("class_name"));
        group.setType(Integer.parseInt(request.getParameter("class_type")));
        group.setHost(Integer.parseInt(request.getParameter("creater")));
        group.setAbout(request.getParameter("about"));

        System.out.println("INSERT GROUP:"+group);

        GroupDAO.start();
        GroupDAO.insertAndSetId(group);
        GroupDAO.close();

        return group.getId();
    }

    public static void dismissGroup(HttpServletRequest request) throws IOException{
        Group group = new Group();

        group.setId(Integer.parseInt(request.getParameter("class")));

        GroupDAO.start();
        GroupDAO.deleteGroup(group);
        GroupDAO.close();
    }

    public static void joinGroup(HttpServletRequest request) throws IOException {
        Roster roster = new Roster();

        roster.setGroupId(Integer.parseInt(request.getParameter("class")));
        roster.setUserID(Integer.parseInt(request.getParameter("user")));
        roster.setType(0);

        System.out.println(roster);

        GroupDAO.start();
        GroupDAO.insertRoster(roster);
        GroupDAO.close();
    }

    public static List<Group> queryGroup(HttpServletRequest request) throws IOException{
        User user = new User();

        user.setId(Integer.parseInt(request.getParameter("user")));
        System.out.println(user);

        GroupDAO.start();
        List<Group> groups = GroupDAO.selectUserGroup(user);
        GroupDAO.close();

        return groups;
    }

    public static void quitGroup(HttpServletRequest request) throws  IOException{
        Roster roster = new Roster();

        roster.setUserID(Integer.parseInt(request.getParameter("user")));
        roster.setGroupId(Integer.parseInt(request.getParameter("class")));

        GroupDAO.start();
        GroupDAO.deleteRoster(roster);
        GroupDAO.close();
    }

}
