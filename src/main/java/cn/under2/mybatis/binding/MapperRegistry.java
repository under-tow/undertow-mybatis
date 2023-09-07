package cn.under2.mybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import cn.under2.mybatis.session.Configuration;
import cn.under2.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapperRegistry {

    private Configuration config;

    public MapperRegistry(Configuration config){
        this.config = config;
    }

    private final Map<Class<?>,MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {

        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);

        if(mapperProxyFactory == null){
            throw new RuntimeException("Type" + type + " is not known to the MapperRegistry ");
        }

        try {
            return mapperProxyFactory.newInstance(sqlSession);
        }catch (Exception e){
            throw new RuntimeException("Error getting mapper instance. Cause " + e ,e);
        }
    }

    public <T> void addMapper(Class<T> type){
        if(type.isInterface()){
            if(hasMapper(type)){
                // 重复添加报错
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry. ");
            }
            // 注册映射代理工程
            knownMappers.put(type,new MapperProxyFactory<>(type));
        }
    }

    public  <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public void addMappers(String packageName){
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }
}
