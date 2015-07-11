package netty.demo.nio.halfpackage.issue;

import io.netty.channel.ChannelHandler;
import netty.demo.nio.AbstractNioTcpServer;

/**
 * Created by niuqinghua on 2015/7/9.
 */
public class NioTcpTimeServer extends AbstractNioTcpServer {

    public NioTcpTimeServer(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new TimeServerUnsharableChildHandler();
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        NioTcpTimeServer echoServer = new NioTcpTimeServer(port);
        echoServer.run();
    }

}
