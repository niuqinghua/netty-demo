package netty.demo.nio.echo;

import io.netty.channel.ChannelHandler;
import netty.demo.nio.AbstractTcpServer;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class EchoTcpServer extends AbstractTcpServer {

    public EchoTcpServer(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new EchoServerChildChannelHandler();
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        EchoTcpServer echoServer = new EchoTcpServer(port);
        echoServer.run();
    }
}
