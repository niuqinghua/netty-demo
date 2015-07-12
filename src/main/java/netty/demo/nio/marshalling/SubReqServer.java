package netty.demo.nio.marshalling;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import netty.demo.nio.AbstractNioTcpServer;
import netty.demo.nio.javaserializable.SubReqServerHandler;

/**
 * Created by niuqinghua on 2015/7/12.
 */
public class SubReqServer extends AbstractNioTcpServer {

    public SubReqServer(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(MarshallingCodecFactory.buildMarshallingDecoder());
                pipeline.addLast(MarshallingCodecFactory.buildMarshallingEncoder());
                pipeline.addLast(new SubReqServerHandler());
            }
        };
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        SubReqServer server = new SubReqServer(port);
        server.run();
    }

}
