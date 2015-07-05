package netty.demo.time.replayingdecoder;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import netty.demo.AbstractClient;
import netty.demo.time.TimeClientHandler;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class TimeClient extends AbstractClient {

    public TimeClient(String ip, int port) {
        super(ip, port);
    }

    @Override
    protected ChannelHandler createChannelHandler() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
            }
        };
    }

    public static void main(String[] args) throws Exception {
        String ip = args.length > 0 ? args[0] : "127.0.0.1";
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        TimeClient timeClient = new TimeClient(ip, port);
        timeClient.run();
    }

}
