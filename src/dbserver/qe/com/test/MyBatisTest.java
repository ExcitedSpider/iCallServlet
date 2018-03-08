package dbserver.qe.com.test;

import dbserver.qe.com.UserProfileTool ;
import dbserver.qe.com.bean.Token;
import dbserver.qe.com.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyBatisTest {
    public static void main(String[] args) throws IOException {
        String resource = "dbserver/sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session= sqlSessionFactory.openSession();

        Long l = (long)10000060;
        session.delete("deleteTokenByTimestamp",l);
        session.commit();

        List<Token> list = session.selectList("TokenMapper.selectAll");
        System.out.println(list);
    }


}
