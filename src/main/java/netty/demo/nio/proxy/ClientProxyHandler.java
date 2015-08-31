package netty.demo.nio.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by niuqinghua on 15/8/31.
 */
public class ClientProxyHandler<T> implements InvocationHandler {

    private Class<T> proxied;

    public ClientProxyHandler(Class<T> proxied) {
        this.proxied = proxied;
    }

    public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable {
        RPCRequest rpcRequest = new RPCRequest(proxied.getTypeName(), method, args);
        return "123";
    }

}
