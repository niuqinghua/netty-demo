package netty.demo.nio.tcp.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf message = (ByteBuf) msg;
        try {
            long timeInlong = (((ByteBuf) msg).readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(timeInlong));
            ctx.close();
        } finally {
            message.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
