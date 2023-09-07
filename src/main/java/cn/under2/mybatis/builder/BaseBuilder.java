package cn.under2.mybatis.builder;

import cn.under2.mybatis.session.Configuration;

public class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration){
        this.configuration = configuration;
    }

    public Configuration getConfiguration(){
        return configuration;
    }
}
