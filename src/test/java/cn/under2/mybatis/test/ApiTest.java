package cn.under2.mybatis.test;

import cn.under2.mybatis.binding.MapperRegistry;
import cn.under2.mybatis.io.Resources;
import cn.under2.mybatis.session.SqlSession;
import cn.under2.mybatis.session.SqlSessionFactory;
import cn.under2.mybatis.session.SqlSessionFactoryBuilder;
import cn.under2.mybatis.session.defaults.DefaultSqlSessionFactory;
import cn.under2.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

public class ApiTest {

    private static final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_MapperProxyFactory() throws IOException {
//        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
//
//        Map<String, String> sqlSession = new HashMap<>();
//
//        sqlSession.put("cn.under2.mybatis.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
//        sqlSession.put("cn.under2.mybatis.test.dao.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");

//        IUserDao userDao = factory.newInstance(sqlSession);
//
//        String res = userDao.queryUserName("10001");

//        logger.info("测试结果：{}", res);

        // 1. 注册Mapper
//        MapperRegistry registry = new MapperRegistry();
//        registry.addMappers("cn.under2.mybatis.test.dao");
//        // 2. 从SqlSession 工程获取 Session
//        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        // 3. 获取映射对象
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//        // 4. 测试验证
//        String res = userDao.queryUserName("10001");
//        logger.info("测试结果：{}",res);

        // 文件读取为Reader
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        // 根据Reader转为sql会话的工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        String res = userDao.queryUserInfoById("10001");
        logger.info("测试结果 = {}", res);

    }

}
