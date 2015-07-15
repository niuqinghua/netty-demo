package netty.demo.nio.tcp.halfpackage.delimiterbased;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import netty.demo.nio.tcp.halfpackage.HalfPackageUtils;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class TimeServerUnsharableChildHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        ByteBuf delimiter = HalfPackageUtils.getDelimiter();
        pipeline.addLast("delimiterBasedFrameDecoder", new DelimiterBasedFrameDecoder(1024, delimiter));
        pipeline.addLast("stringDecoder", new StringDecoder());
        pipeline.addLast("timeServerChildHandler", new TimeServerHandler());
    }

}
