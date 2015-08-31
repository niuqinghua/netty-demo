package netty.demo.nio.proxy;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by niuqinghua on 15/8/31.
 */
@Component("serverProxyHandler")
public class ServerProxyHandler extends ChannelHandlerAdapter {

    @Autowired
    private BeanFactory beanFactory;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RPCRequest rpcRequest = (RPCRequest) msg;
        Class serviceClass = Class.forName(rpcRequest.getTypeName());
        Object service = beanFactory.getBean(serviceClass);
        Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
        Object result = method.invoke(service, rpcRequest.getArguments());
        ctx.writeAndFlush(result);
    }
}
