package netty.demo.nio.tcp.http.xml;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import netty.demo.nio.tcp.AbstractNioTcpServer;

/**
 * Created by niuqinghua on 2015/7/15.
 */
public class NioTcpHttpXmlServer extends AbstractNioTcpServer {

    public NioTcpHttpXmlServer(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("http-decoder", new HttpRequestDecoder());
                pipeline.addLast("http-aggregator", new HttpObjectAggregator(65536));
                pipeline.addLast("xml-decoder", new HttpXmlRequestDecoder());
                pipeline.addLast("http-encoder", new HttpRequestEncoder());
                pipeline.addLast("xml-encoder", new HttpXmlRequestEncoder());
                pipeline.addLast("xmlClientHandler", new HttpXmlClientHandler());
            }
        };
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        NioTcpHttpXmlServer server = new NioTcpHttpXmlServer(port);
        server.run();
    }

}
