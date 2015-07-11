package netty.demo.nio.time.first;

import io.netty.channel.ChannelHandler;
import netty.demo.nio.AbstractTcpClient;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class TimeTcpClient extends AbstractTcpClient {

    public TimeTcpClient(String ip, int port) {
        super(ip, port);
    }

    @Override
    protected ChannelHandler createChannelHandler() {
        return new TimeClientHandler();
    }

    public static void main(String[] args) throws Exception {
        String ip = args.length > 0 ? args[0] : "127.0.0.1";
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        TimeTcpClient timeClient = new TimeTcpClient(ip, port);
        timeClient.run();
    }

}
