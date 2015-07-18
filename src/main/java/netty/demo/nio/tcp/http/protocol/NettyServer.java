package netty.demo.nio.tcp.http.protocol;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import netty.demo.nio.tcp.AbstractNioTcpServer;

/**
 * Created by niuqinghua on 2015/7/18.
 */
public class NettyServer extends AbstractNioTcpServer {

    public NettyServer(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline channelPipeline = ch.pipeline();
                channelPipeline.addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                channelPipeline.addLast(new NettyMessageEncoder());
                channelPipeline.addLast("readTimeOutHandler", new ReadTimeoutHandler(5));
                channelPipeline.addLast(new LoginAuthRespHandler());
                channelPipeline.addLast("hearBeatRespHandler", new HeartBeatRespHandler());
            }
        };
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        NettyServer server = new NettyServer(port);
        server.run();
    }
}
