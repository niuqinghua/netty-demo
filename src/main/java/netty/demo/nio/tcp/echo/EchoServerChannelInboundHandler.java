package netty.demo.nio.tcp.echo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by niuqinghua on 2015/7/12.
 */
public class EchoServerChannelInboundHandler extends SimpleChannelInboundHandler<String> {

    private static final String CR = System.getProperty("line.separator");

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
        ctx.writeAndFlush("source is: " + msg + CR);
    }

}
