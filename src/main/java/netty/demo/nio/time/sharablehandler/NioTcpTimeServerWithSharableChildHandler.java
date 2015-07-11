package netty.demo.nio.time.sharablehandler;

import io.netty.channel.ChannelHandler;
import netty.demo.nio.AbstractNioTcpServer;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class NioTcpTimeServerWithSharableChildHandler extends AbstractNioTcpServer {

    public NioTcpTimeServerWithSharableChildHandler(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new TimeServerSharableChildHandler();
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        NioTcpTimeServerWithSharableChildHandler echoServer = new NioTcpTimeServerWithSharableChildHandler(port);
        echoServer.run();
    }

}
