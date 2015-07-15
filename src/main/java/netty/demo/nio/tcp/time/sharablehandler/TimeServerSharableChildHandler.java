package netty.demo.nio.tcp.time.sharablehandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

/**
 * Created by niuqinghua on 2015/7/4.
 */
@ChannelHandler.Sharable
public class TimeServerSharableChildHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        final ChannelFuture channelFuture = ctx.writeAndFlush(time);
        channelFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                assert channelFuture == future;
                ctx.close();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
