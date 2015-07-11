package netty.demo.nio.file;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import netty.demo.nio.AbstractNioTcpServer;

import java.nio.charset.Charset;

/**
 * Created by niuqinghua on 2015/7/11.
 */
public class NioTcpFileServer extends AbstractNioTcpServer {

    private static final Charset UTF_8 = CharsetUtil.UTF_8;

    public NioTcpFileServer(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new StringEncoder(UTF_8));
                pipeline.addLast(new LineBasedFrameDecoder(1024));
                pipeline.addLast(new StringDecoder(UTF_8));
                pipeline.addLast(new FileServerHandler());
            }
        };
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        NioTcpFileServer server = new NioTcpFileServer(port);
        server.run();
    }

}
