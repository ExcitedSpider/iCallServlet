package dbserver.qe.com;

import dbserver.qe.com.bean.Group;
import dbserver.qe.com.bean.Roster;
import dbserver.qe.com.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
*这个类是用于'群'相关的数据库操作的DAO(Data Access Object)。
* 为了优化性能，其中全都是静态方法，数据库的连接在任何情况下只会出现一例。
 * 如果有功能缺失，请联系我。
*@author 冯秋翼
*/
public class GroupDAO {

    static private SqlSession session;
    static final String Mappername = "GroupMapper.";

    private GroupDAO() { }

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
     * 此方法是由群id查询群资料的方法
     * @param group 使用group的id字段查询，输入的group其他字段都可以为空
     * @return 完整的group
     */
    static public Group selectGroupById(Group group){
        return session.selectOne(Mappername+"selectGroupById",group);
    }

    /**
     * 查询用户创建的所有群
     * @param user 欲查询的用户
     * @return 该用户创建的所有群组成的List对象
     */
    static public List<Group> selectGroupsByHost(User user){
        return session.selectList(Mappername+"selectGroupsByHost",user);
    }

    /**
     * 创建群，并在该群的id字段中设置数据库自动设置的id
     * @param group 包含群名称name,创建者host,群简介about,群类型type的Group对象。
     */
    static public void insertAndSetId(Group group){
        session.insert(Mappername+"insertAndSetId",group);
        session.insert(Mappername+"joinGroup",new Roster(group.getId(),group.getHost(),0));
        session.commit();
    }

    /**
     * 通过群的id字段删除群
     * @param group 包含欲删除群id字段的group对象
     */
    static public void deleteGroup(Group group){
        session.delete(Mappername+"deleteGroupById");
        session.commit();
    }

    /**
     * 更新群简介
     * @param group 包含欲更新群id字段和新简介about字段的Group对象
     */
    static public void updateGroupAbout(Group group){
        session.update(Mappername+"updateGroupAbout",group);
        session.commit();
    }

    /**
     * 插入群名单
     * @param roster 包含群编号groupID,使用者编号userID,群员类型type的Roster对象
     */
    static public void insertRoster(Roster roster){
        session.insert(Mappername+"joinGroup",roster);
        session.commit();
    }

    /**
     * 查询一个用户所加入的所有群
     * @param user 欲查询的用户，至少包含id字段
     * @return 所有相关群组成的List对象
     */
    static public List<Group> selectUserGroup(User user){
        return session.selectList(Mappername+"selectUserGroup",user);
    }

    /**
     * 查询一条名单信息。
     * @param roster 欲查询的名单，至少包含群编号groupID,用户编号userID字段
     * @return 一条名单 Roster
     */
    static public Roster selectRoster(Roster roster){
        return session.selectOne(Mappername+"selectRoster",roster);
    }

    /**
     * 删除一条群名单，功能上等同于退出群
     * @param roster 欲删除的群名单，至少包含群编号groupID,用户编号userID字段
     */
    static public void deleteRoster(Roster roster){
        session.delete(Mappername+"deleteRoster",roster);
        session.commit();
    }



}
