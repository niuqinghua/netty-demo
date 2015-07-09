package netty.demo.halfpackage.fixedlength;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class TimeServerUnsharableChildHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("fixedLengthFrameDecoder", new FixedLengthFrameDecoder(20));
        pipeline.addLast("stringDecoder", new StringDecoder());
        pipeline.addLast("timeServerChildHandler", new TimeServerHandler());
    }

}
