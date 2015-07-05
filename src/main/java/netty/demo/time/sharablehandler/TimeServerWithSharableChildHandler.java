package netty.demo.time.sharablehandler;

import io.netty.channel.ChannelHandler;
import netty.demo.AbstractServer;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class TimeServerWithSharableChildHandler extends AbstractServer {

    public TimeServerWithSharableChildHandler(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new TimeServerSharableChildHandler();
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        TimeServerWithSharableChildHandler echoServer = new TimeServerWithSharableChildHandler(port);
        echoServer.run();
    }

}
