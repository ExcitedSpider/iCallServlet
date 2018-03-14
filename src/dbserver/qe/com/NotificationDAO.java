package dbserver.qe.com;

import dbserver.qe.com.bean.Notification;
import dbserver.qe.com.bean.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * 这是通知功能的DAO(data access object)
 * @author 冯秋翼
 */
public class NotificationDAO {

    static private SqlSession session;
    static private final String Mappername = "NotificationMapper.";

    private NotificationDAO() { }

    /**
     * 在任何其他数据库访问操作前，必须使用此方法。此方法用于创建数据库会话实例
     */
    static public void start(){
        SqlSessionFactory factory = MybatisFactory.getFactory();
        if(factory ==null ) System.out.println("factory is null");
        session = MybatisFactory.getFactory().openSession();    // 获取到 SqlSession
    }

    /**
     * 此方法是关闭数据库会话的方法。当会话结束(比如servlet)请使用此方法关闭会话释放内存。
     */
    static public void close(){
        session.close();
    }

    /**
     * 新建通知并且在传入的对象中设置id字段
     * @param notification
     */
    static public void insertAndSetId(Notification notification){
        session.insert(Mappername+"insertAndSetId",notification);
        session.commit();
    }

    /**
     * 通过Notification对象的id字段删除通知
     * @param notification
     */
    static public void deleteById(Notification notification){
        session.delete(Mappername+"deleteById",notification);
        session.commit();
    }

    /**
     * 查询用户创建的所有通知。通过User对象的id字段
     * @param user 包含id字段的User对象
     * @return 包含id字段的User对象
     */
    static public List<Notification> selectByCreaterID(User user){
        return session.selectList(Mappername+"selectByCreaterID",user);
    }

    /**
     * 查询用户加入的所有班级的所有通知
     * @param user 包含id字段的User对象
     * @return 包含id字段的User对象
     */
    static public List<Notification> selectByUsersGroup(User user){
        return session.selectList(Mappername+"selectByUsersGroup",user);
    }

    /**
     * 再call一下的实现，更新LastCallTime
     * @param notification 至少包含id,lastCallTime字段的notification对象
     */
    static public void updateLastCallTime(Notification notification){
        session.update(Mappername+"updateLastCallTime",notification);
        session.commit();
    }
}
