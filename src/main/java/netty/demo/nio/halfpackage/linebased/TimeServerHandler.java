package netty.demo.nio.halfpackage.linebased;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import netty.demo.nio.halfpackage.HalfPackageUtils;

import java.util.Date;

/**
 * Created by niuqinghua on 2015/7/9.
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    private static final String QUERY_TIME_ORDER = "query time order";

    private static final String BAD_ORDER = "bad order";

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("The time server receive order : " + body + " the counter is: " + ++counter);
        String currentTime = HalfPackageUtils.QUERY_TIME_ORDER.equals(body)
                ? new Date(System.currentTimeMillis()).toString() : HalfPackageUtils.BAD_ORDER;
        currentTime += HalfPackageUtils.getLineSeparator();
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
