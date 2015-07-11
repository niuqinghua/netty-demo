package netty.demo.nio.echo;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class EchoServerHandler extends ChannelHandlerAdapter {

    private static final String CR = System.getProperty("line.separator");

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String messageReceived = (String) msg;
        System.out.println(messageReceived);
        ctx.writeAndFlush("source is: " + msg + CR);
    }

}
