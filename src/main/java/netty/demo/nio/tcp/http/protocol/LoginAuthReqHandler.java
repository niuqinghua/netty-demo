package netty.demo.nio.tcp.http.protocol;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by niuqinghua on 2015/7/18.
 */
public class LoginAuthReqHandler extends ChannelHandlerAdapter {

    private static final byte REQ_TYPE = Constants.REQ_TYPE;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buildLoginReq());
    }

    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(REQ_TYPE);
        message.setHeader(header);
        return message;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;

        if (!isLoginResp(message)) {
            ctx.fireChannelRead(msg);
            return;
        }

        byte loginResult = (Byte) message.getBody();
        if (loginResult == (byte) 0) {
            ctx.close();
        } else {
            ctx.fireChannelRead(msg);
        }

    }

    private boolean isLoginResp(NettyMessage message) {
        return NettyMessageTypeUtils.isLoginResp(message);
    }
}
