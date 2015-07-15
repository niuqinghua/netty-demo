package netty.demo.nio.tcp.time.unsharablehandler;

import io.netty.channel.ChannelHandler;
import netty.demo.nio.tcp.AbstractNioTcpServer;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class NioTcpTimeServerWithUnSharableChildHandler extends AbstractNioTcpServer {

    public NioTcpTimeServerWithUnSharableChildHandler(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new TimeServerUnsharableChildHandler();
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        NioTcpTimeServerWithUnSharableChildHandler echoServer = new NioTcpTimeServerWithUnSharableChildHandler(port);
        echoServer.run();
    }

}
