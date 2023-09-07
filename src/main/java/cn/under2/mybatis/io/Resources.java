package cn.under2.mybatis.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 文件资源处理类
 */
public class Resources {

    /**
     * 通过路径转为inputStream，再将inputStream包装为InputSteamReader(Reader)
     */
    public static Reader getResourceAsReader(String resource) throws IOException {
        return new InputStreamReader(getResourceAsStream(resource));
    }

    /**
     * 根据输入的路径字符串，返回二进制流
     */
    private static InputStream getResourceAsStream(String resource) throws IOException {
        // 获取类加载器
        ClassLoader[] classLoaders = getClassLoaders();
        // 遍历类加载器 加载路径 转为二进制流
        for (ClassLoader classLoader : classLoaders) {
            InputStream inputStream = classLoader.getResourceAsStream(resource);
            if(null!=inputStream){
                return inputStream;
            }
        }
        throw new IOException("Could not find resource " + resource);
    }

    /**
     * 直接初始化数组，返回appClassLoader 与 当前线程中的上下文类加载器
     */
    private static ClassLoader[] getClassLoaders(){
        return new ClassLoader[]{
                ClassLoader.getSystemClassLoader(),
                Thread.currentThread().getContextClassLoader()
        };
    }

    public static Class<?> classForName(String className) throws ClassNotFoundException{
        return Class.forName(className);
    }
}
