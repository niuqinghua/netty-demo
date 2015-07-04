package netty.demo.echo;

import io.netty.channel.ChannelHandler;
import netty.demo.AbstractServer;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class EchoServer extends AbstractServer {

    public EchoServer(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new EchoServerChildChannelHandler();
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        EchoServer echoServer = new EchoServer(port);
        echoServer.run();
    }
}
