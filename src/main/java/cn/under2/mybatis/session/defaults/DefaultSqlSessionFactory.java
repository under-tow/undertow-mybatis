package cn.under2.mybatis.session.defaults;

import cn.under2.mybatis.session.Configuration;
import cn.under2.mybatis.session.SqlSession;
import cn.under2.mybatis.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
