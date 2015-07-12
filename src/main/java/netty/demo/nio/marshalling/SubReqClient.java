package netty.demo.nio.marshalling;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import netty.demo.nio.AbstractNioTcpClient;
import netty.demo.nio.javaserializable.SubReqClientHandler;

/**
 * Created by niuqinghua on 2015/7/12.
 */
public class SubReqClient extends AbstractNioTcpClient {

    public SubReqClient(String ip, int port) {
        super(ip, port);
    }

    @Override
    protected ChannelHandler createChannelHandler() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(MarshallingCodecFactory.buildMarshallingDecoder());
                pipeline.addLast(MarshallingCodecFactory.buildMarshallingEncoder());
                pipeline.addLast(new SubReqClientHandler());
            }
        };
    }

    public static void main(String[] args) throws Exception {
        String ip = args.length > 0 ? args[0] : "127.0.0.1";
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        SubReqClient client = new SubReqClient(ip, port);
        client.run();
    }

}
