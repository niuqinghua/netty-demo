package netty.demo.nio.halfpackage.fixedlength;

import io.netty.channel.ChannelHandler;
import netty.demo.nio.AbstractTcpServer;

/**
 * Created by niuqinghua on 2015/7/9.
 */
public class TimeTcpServer extends AbstractTcpServer {

    public TimeTcpServer(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new TimeServerUnsharableChildHandler();
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        TimeTcpServer echoServer = new TimeTcpServer(port);
        echoServer.run();
    }

}
