package netty.demo.nio.tcp.http.protocol;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by niuqinghua on 2015/7/18.
 */
public class HeartBeatReqHandler extends ChannelHandlerAdapter {

    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if (NettyMessageTypeUtils.isHeartBeatReq(message)) {
            heartBeat = ctx.executor().scheduleAtFixedRate(new HearBeatTask(ctx), 0, 5000, TimeUnit.MICROSECONDS);
        } else if (NettyMessageTypeUtils.isHeartBeatResp(message)) {

        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }

    private class HearBeatTask implements Runnable {

        private final ChannelHandlerContext ctx;

        public HearBeatTask(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        public void run() {
            ctx.writeAndFlush(buildHeartBeatReqMessage());
        }

        private NettyMessage buildHeartBeatReqMessage() {
            Header header = new Header();
            header.setType(Constants.HEARTBEAT_REQ_TYPE);
            return new NettyMessage(header);
        }
    }

}
