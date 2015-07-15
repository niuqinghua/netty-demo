package netty.demo.nio.tcp.discard;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by niuqinghua on 2015/7/4.
 */
public class DiscardServerClildChannelHadler extends ChannelInitializer<SocketChannel> {

    @Override
    public void initChannel(SocketChannel socketChannel) throws Exception{
        socketChannel.pipeline().addLast(new DiscardServerHandler());
    }

}
