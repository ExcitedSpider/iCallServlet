package dbserver.qe.com;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisFactory {
    private static SqlSessionFactory sqlSessionFactory;
    private static final String resource = "dbserver/sqlMapConfig.xml";

    public static SqlSessionFactory getFactory() {
        try {
            if (sqlSessionFactory == null) {
                InputStream inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                return sqlSessionFactory;
            } else {
                return sqlSessionFactory;
            }
        }catch (IOException e) {
            System.out.println("ERROR ON OPEN SESSION FACTORY.");
        }
        return sqlSessionFactory;
    }
}
