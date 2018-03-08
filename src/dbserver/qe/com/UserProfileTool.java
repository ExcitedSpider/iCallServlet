package dbserver.qe.com;

import dbserver.qe.com.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.io.InputStream;

public class UserProfileTool {

    static private SqlSession session;
    static public void start(){
        try {
            String resource = "dbserver/sqlMapConfig.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(inputStream);

            session = sqlSessionFactory.openSession();    // 获取到 SqlSession
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    static public void close(){
        session.close();
    }
    static public void addUser(User user){
        session.insert("UserMapper.insert",user);  ////默认是不自动提交，必须手工提交
        session.commit();
    }
    static public void deleteUser(int id){
        session.delete("UserMapper.delete",id);
        session.commit();
    }
    static public void updateUserPassword(int id,String passWord){
        session.update("UserMapper.updatePassword",passWord);
        session.commit();
    }
    static public User findUserById(int id){
        return session.selectOne("UserMapper.findById",id);
    }
    static public User findUserByAccount(String account){
        return session.selectOne("UserMapper.findByAccount",account);
    }
    static public void updateUserProfile(User user){
        session.update("UserMapper.updateProfile",user);
        session.commit();
    }
}
