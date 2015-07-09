package netty.demo.halfpackage.linebased;

import io.netty.channel.ChannelHandler;
import netty.demo.AbstractServer;

/**
 * Created by niuqinghua on 2015/7/9.
 */
public class TimeServer extends AbstractServer {

    public TimeServer(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new TimeServerUnsharableChildHandler();
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        TimeServer echoServer = new TimeServer(port);
        echoServer.run();
    }

}
