package netty.demo.nio.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by niuqinghua on 15/8/31.
 */
public class ServiceProxy {

    public static <T> T createClientProxy(Class<T> type) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(),
                new Class[]{type},
                new ClientProxyHandler(type));
    }

    public static void main(String[] args) {
        ServiceInterface serviceInterface = ServiceProxy.createClientProxy(ServiceInterface.class);
        System.out.println(serviceInterface.process("test"));
    }

}
