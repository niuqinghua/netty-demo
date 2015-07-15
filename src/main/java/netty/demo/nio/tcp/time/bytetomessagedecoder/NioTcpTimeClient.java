package netty.demo.nio.tcp.time.bytetomessagedecoder;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import netty.demo.nio.tcp.AbstractNioTcpClient;
import netty.demo.nio.tcp.time.TimeClientHandler;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class NioTcpTimeClient extends AbstractNioTcpClient {

    public NioTcpTimeClient(String ip, int port) {
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
        NioTcpTimeClient timeClient = new NioTcpTimeClient(ip, port);
        timeClient.run();
    }

}
