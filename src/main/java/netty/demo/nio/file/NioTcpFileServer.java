package netty.demo.nio.file;

import io.netty.channel.ChannelHandler;
import netty.demo.nio.AbstractNioTcpServer;

/**
 * Created by niuqinghua on 2015/7/11.
 */
public class NioTcpFileServer extends AbstractNioTcpServer {

    public NioTcpFileServer(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return null;
    }

}
