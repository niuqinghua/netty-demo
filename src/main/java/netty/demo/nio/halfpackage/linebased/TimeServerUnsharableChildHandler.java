package netty.demo.nio.halfpackage.linebased;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class TimeServerUnsharableChildHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("lineBasedFrameDecoder", new LineBasedFrameDecoder(1024));
        pipeline.addLast("stringDecoder", new StringDecoder());
        pipeline.addLast("timeServerChildHandler", new TimeServerHandler());
    }

}
