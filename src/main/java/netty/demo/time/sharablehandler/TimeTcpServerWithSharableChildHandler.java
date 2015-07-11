package netty.demo.time.sharablehandler;

import io.netty.channel.ChannelHandler;
import netty.demo.AbstractTcpServer;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class TimeTcpServerWithSharableChildHandler extends AbstractTcpServer {

    public TimeTcpServerWithSharableChildHandler(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new TimeServerSharableChildHandler();
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        TimeTcpServerWithSharableChildHandler echoServer = new TimeTcpServerWithSharableChildHandler(port);
        echoServer.run();
    }

}
