package netty.demo.nio.halfpackage.issue;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class TimeServerUnsharableChildHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("timeServerChildHandler", new TimeServerHandler());
    }

}
