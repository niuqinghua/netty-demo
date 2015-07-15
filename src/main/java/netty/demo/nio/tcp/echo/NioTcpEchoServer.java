package netty.demo.nio.tcp.echo;

import io.netty.channel.ChannelHandler;
import netty.demo.nio.tcp.AbstractNioTcpServer;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class NioTcpEchoServer extends AbstractNioTcpServer {

    public NioTcpEchoServer(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new EchoServerChildChannelHandler();
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        NioTcpEchoServer echoServer = new NioTcpEchoServer(port);
        echoServer.run();
    }
}
