package netty.demo.nio.echo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class EchoServerChildChannelHandler extends ChannelInitializer<SocketChannel> {

    private static final Charset UTF_8 = CharsetUtil.UTF_8;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new StringEncoder(UTF_8));
        pipeline.addLast(new LineBasedFrameDecoder(1024));
        pipeline.addLast(new StringDecoder(UTF_8));
        pipeline.addLast(new EchoServerHandler());
    }

}
