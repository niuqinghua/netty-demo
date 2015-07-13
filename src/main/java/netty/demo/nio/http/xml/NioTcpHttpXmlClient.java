package netty.demo.nio.http.xml;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import netty.demo.nio.AbstractNioTcpClient;

/**
 * Created by niuqinghua on 2015/7/14.
 */
public class NioTcpHttpXmlClient extends AbstractNioTcpClient {

    public NioTcpHttpXmlClient(String ip, int port) {
        super(ip, port);
    }

    @Override
    protected ChannelHandler createChannelHandler() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("http-decoder", new HttpResponseDecoder());
                pipeline.addLast("http-aggregator", new HttpObjectAggregator(65536));
                pipeline.addLast("xml-decoder", new HttpXmlResponseDecoder());
                pipeline.addLast("http-encoder", new HttpRequestEncoder());
                pipeline.addLast("xml-encoder", new HttpXmlRequestEncoder());
                pipeline.addLast("xmlClientHandler", new HttpXmlClientHandler());
            }
        };
    }

    public static void main(String[] args) throws Exception {
        String ip = args.length > 0 ? args[0] : "127.0.0.1";
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        NioTcpHttpXmlClient client = new NioTcpHttpXmlClient(ip, port);
        client.run();
    }

}
