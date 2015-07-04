/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package netty.demo.discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import netty.demo.AbstractServer;

/**
 * Created by niuqinghua on 2015/6/12.
 */
public class DiscardServer extends AbstractServer {

    public DiscardServer(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new DiscardServerClildChannelHadler();
    }

    public static void main(String[] args) throws Exception{
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        DiscardServer discardServer = new DiscardServer(port);
        discardServer.run();
    }

}
