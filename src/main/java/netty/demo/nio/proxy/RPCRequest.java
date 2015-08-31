package netty.demo.nio.proxy;

/**
 * Created by niuqinghua on 15/8/31.
 */
public class RPCRequest {

    private final String methodName;

    private final Class<?>[] parameterTypes;

    private final Object[] arguments;

    public RPCRequest(String methodName, Class<?>[] parameterTypes, Object[] arguments) {
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.arguments = arguments;
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
