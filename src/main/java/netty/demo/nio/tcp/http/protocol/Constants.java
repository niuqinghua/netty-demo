package netty.demo.nio.tcp.http.protocol;

/**
 * Created by niuqinghua on 2015/7/18.
 */
public class Constants {

    public static final byte REQ_TYPE = Integer.valueOf(3).byteValue();

    public static final byte RESP_TYPE = Integer.valueOf(4).byteValue();

    public static final byte HEARTBEAT_REQ_TYPE = Integer.valueOf(5).byteValue();

    public static final byte HEARTBEAT_RESP_TYPE = Integer.valueOf(6).byteValue();

}
