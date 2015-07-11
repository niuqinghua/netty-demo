package netty.demo.nio.javaserializable;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import netty.demo.nio.AbstractNioTcpServer;

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
                pipeline.addLast(new ObjectDecoder(1024 * 1024, ClassResolvers.weakCachingConcurrentResolver(getClassLoader())));
                pipeline.addLast(new ObjectEncoder());
                pipeline.addLast(new SubReqServerHandler());
            }

            private ClassLoader getClassLoader() {
                Class<? extends ChannelInitializer<SocketChannel>> classType = this.getClass();
                return classType.getClassLoader();
            }
        };
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        SubReqServer server = new SubReqServer(port);
        server.run();
    }

}
