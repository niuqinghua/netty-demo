package netty.demo.nio.tcp.http.protocol;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by niuqinghua on 2015/7/18.
 */
public class HeartBeatRespHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;

        if (NettyMessageTypeUtils.isHeartBeatReq(message)) {
            ctx.writeAndFlush(buildHeartBeatReqMessage());
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildHeartBeatReqMessage() {
        Header header = new Header();
        header.setType(Constants.HEARTBEAT_RESP_TYPE);
        return new NettyMessage(header);
    }

}
