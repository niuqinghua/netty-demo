package netty.demo.nio.tcp.halfpackage.linebased;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import netty.demo.nio.tcp.AbstractNioTcpClient;

/**
 * Created by niuqinghua on 2015/7/9.
 */
public class NioTcpTimeClient extends AbstractNioTcpClient {

    public NioTcpTimeClient(String ip, int port) {
        super(ip, port);
    }

    @Override
    protected ChannelHandler createChannelHandler() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("lineBasedFrameDecoder", new LineBasedFrameDecoder(1024));
                pipeline.addLast("stringDecoder", new StringDecoder());
                pipeline.addLast("timeServerChildHandler", new TimeClientHandler());
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
