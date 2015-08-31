package netty.demo.nio.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by niuqinghua on 15/8/31.
 */
public class ServiceProxy {

    public static <T> T createService(Class<T> type) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(),
                new Class[]{type},
                new ProxyHandler(type));
    }

    public static void main(String[] args) {
        ServiceInterface serviceInterface = ServiceProxy.createService(ServiceInterface.class);
        System.out.println(serviceInterface.process("test"));
    }

}
