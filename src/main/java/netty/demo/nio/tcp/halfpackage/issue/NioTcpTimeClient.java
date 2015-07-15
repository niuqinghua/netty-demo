package netty.demo.nio.tcp.halfpackage.issue;

import io.netty.channel.ChannelHandler;
import netty.demo.nio.tcp.AbstractNioTcpClient;

/**
 * Created by niuqinghua on 2015/7/9.
 */
public class NioTcpTimeClient extends AbstractNioTcpClient {

    public NioTcpTimeClient(String ip, int port) {
        super(ip, port);
    }

    @Override
    protected ChannelHandler createChannelHandler() {
        return new TimeClientHandler();
    }

    public static void main(String[] args) throws Exception {
        String ip = args.length > 0 ? args[0] : "127.0.0.1";
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        NioTcpTimeClient timeClient = new NioTcpTimeClient(ip, port);
        timeClient.run();
    }

}
