package service;

import dbserver.qe.com.NotificationDAO;
import dbserver.qe.com.bean.Notification;
import dbserver.qe.com.bean.User;
import dbserver.qe.com.tool.DateTools;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class NotificationService {

    public static void createNotification(HttpServletRequest request) throws Exception{

        Notification notification = new Notification();
        getNotificationFromRequest(request, notification);

        NotificationDAO.start();
        NotificationDAO.insertAndSetId(notification);
        NotificationDAO.close();
    }

    public static List<Notification> QueryNotification(HttpServletRequest request) throws IOException {
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("user")));

        NotificationDAO.start();
        List<Notification> list = NotificationDAO.selectByUsersGroup(user);
        NotificationDAO.close();

        return list;
    }

    public static void deleteNotification(HttpServletRequest request) throws IOException{
        Notification notification = new Notification();
        notification.setId(Integer.parseInt(request.getParameter("notificationID")));

        NotificationDAO.start();
        NotificationDAO.deleteById(notification);
        NotificationDAO.close();
    }

    private static void getNotificationFromRequest(HttpServletRequest request, Notification notification) throws Exception {
        notification.setGroupId(Integer.parseInt(request.getParameter("class")));
        notification.setLastCallTime(new Timestamp(System.currentTimeMillis()));
        notification.setCreateTime(DateTools.getDate(request.getParameter("create_time")));
        notification.setFailTime(DateTools.getDate(request.getParameter("fail_time")));
        notification.setCreater(Integer.parseInt(request.getParameter("creater")));
        notification.setContent(request.getParameter("content"));
    }
}
