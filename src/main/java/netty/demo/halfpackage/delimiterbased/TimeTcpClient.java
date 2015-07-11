package netty.demo.halfpackage.delimiterbased;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import netty.demo.AbstractTcpClient;
import netty.demo.halfpackage.HalfPackageUtils;

/**
 * Created by niuqinghua on 2015/7/9.
 */
public class TimeTcpClient extends AbstractTcpClient {

    public TimeTcpClient(String ip, int port) {
        super(ip, port);
    }

    @Override
    protected ChannelHandler createChannelHandler() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) {
                ChannelPipeline pipeline = ch.pipeline();
                ByteBuf delimiter = HalfPackageUtils.getDelimiter();
                pipeline.addLast("delimiterBasedFrameDecoder", new DelimiterBasedFrameDecoder(1024, delimiter));
                pipeline.addLast("stringDecoder", new StringDecoder());
                pipeline.addLast("timeServerChildHandler", new TimeClientHandler());
            }
        };
    }

    public static void main(String[] args) throws Exception {
        String ip = args.length > 0 ? args[0] : "127.0.0.1";
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        TimeTcpClient timeClient = new TimeTcpClient(ip, port);
        timeClient.run();
    }

}
