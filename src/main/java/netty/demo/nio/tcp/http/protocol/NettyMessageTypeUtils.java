package netty.demo.nio.tcp.http.protocol;

/**
 * Created by niuqinghua on 2015/7/18.
 */
public class NettyMessageTypeUtils {

    public static boolean isReqMessage(NettyMessage message) {
        return message.getHeader() != null && message.getHeader().getType() == Constants.REQ_TYPE;
    }

    public static boolean isLoginResp(NettyMessage message) {
        return message.getHeader() != null && message.getHeader().getType() == Constants.RESP_TYPE;
    }

    public static boolean isHeartBeatReq(NettyMessage message) {
        return message.getHeader() != null && message.getHeader().getType() == Constants.HEARTBEAT_REQ_TYPE;
    }

    public static boolean isHeartBeatResp(NettyMessage message) {
        return message.getHeader() != null && message.getHeader().getType() == Constants.HEARTBEAT_RESP_TYPE;
    }

}
