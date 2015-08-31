package netty.demo.nio.proxy;

import java.lang.reflect.Method;

/**
 * Created by niuqinghua on 15/8/31.
 */
public class RPCRequest {

    private String typeName;

    private final String methodName;

    private final Class<?>[] parameterTypes;

    private final Object[] arguments;

    public RPCRequest(String typeName, Method method, Object[] arguments) {
        this.typeName = typeName;
        this.methodName = method.getName();
        this.parameterTypes = method.getParameterTypes();
        this.arguments = arguments;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public Object[] getArguments() {
        return arguments;
    }
}
