package dbserver.qe.com;

import dbserver.qe.com.bean.Token;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TokenDAO {
    static private SqlSession session;

    static public void start(){
            session = MybatisFactory.getFactory().openSession();    // 获取到 SqlSession
    }
    static public void close(){
        session.close();
    }

    static public Token selectByImei(String imei){
        return session.selectOne("TokenMapper.selectByImei",imei);
    }

    static public Token selectByAccount(String account){
        return session.selectOne("TokenMapper.selectByAccount",account);
    }

    static public List<Token> selectAll(){
        return session.selectList("TokenMapper.selectAll");
    }

    static public void insertToken(Token token){
        session.insert("TokenMapper.insertToken",token);
        session.commit();
    }

    static public void deleteTokenByTimestamp(long timestamp){
        session.delete("TokenMapper.deleteTokenByTimestamp",timestamp);
        session.commit();
    }

    static public void updateByTokenImei(Token token){
        session.update("TokenMapper.updateByTokenImei",token);
        session.commit();
    }

    static public void insertOnNoDuplicatedKey(Token token){
        session.update("TokenMapper.insertOnNoDuplicatedKey",token);
        session.commit();
    }
}
