package netty.demo.nio.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by niuqinghua on 15/8/31.
 */
public class ProxyHandler<T> implements InvocationHandler {

    private Class<T> proxied;

    public ProxyHandler(Class<T> proxied) {
        this.proxied = proxied;
    }

    public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable {
        System.out.println(proxied.getTypeName());
        System.out.println(method.getName());
        return "123";
    }

}
