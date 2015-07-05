package netty.demo.time.unsharablehandler;

import io.netty.channel.ChannelHandler;
import netty.demo.AbstractServer;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class TimeServerWithUnSharableChildHandler extends AbstractServer {

    public TimeServerWithUnSharableChildHandler(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new TimeServerUnsharableChildHandler();
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        TimeServerWithUnSharableChildHandler echoServer = new TimeServerWithUnSharableChildHandler(port);
        echoServer.run();
    }

}
