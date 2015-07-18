package netty.demo.nio.tcp.http.protocol;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by niuqinghua on 2015/7/18.
 */
public class LoginAuthRespHandler extends ChannelHandlerAdapter {

    private static final byte REQ_TYPE = Constants.REQ_TYPE;

    private static final byte RESP_TYPE = Constants.RESP_TYPE;

    private Map<String, Boolean> nodeCheck = new HashMap<String, Boolean>();

    private String[] whiteList = {"127.0.0.1"};

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        NettyMessage message = (NettyMessage) msg;

        if (!isReqMessage(message)) {
            ctx.fireChannelRead(msg);
            return;
        }

        String nodeIndex = ctx.channel().remoteAddress().toString();
        NettyMessage loginResp = null;

        if (nodeCheck.containsKey(nodeIndex)) {
            loginResp = buildRespMessage((byte) -1);
        } else {
            InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
            String ip = address.getAddress().getHostAddress();
            boolean isOk = false;
            for (String whiteIp : whiteList) {
                if (whiteIp.equals(ip)) {
                    isOk = true;
                    break;
                }
            }
            loginResp = isOk ? buildRespMessage((byte) 0) : buildRespMessage((byte) -1);
            if(isOk) {
                nodeCheck.put(nodeIndex, true);
            }
        }
        ctx.writeAndFlush(loginResp);
    }

    private boolean isReqMessage(NettyMessage message) {
        return message.getHeader() != null && message.getHeader().getType() == REQ_TYPE;
    }

    private NettyMessage buildRespMessage(byte result) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(RESP_TYPE);
        message.setHeader(header);
        message.setBody(result);
        return message;
    }

}
