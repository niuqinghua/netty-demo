/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package netty.demo.discard;

import io.netty.channel.*;
import netty.demo.AbstractTcpServer;

/**
 * Created by niuqinghua on 2015/6/12.
 */
public class DiscardTcpServer extends AbstractTcpServer {

    public DiscardTcpServer(int port) {
        super(port);
    }

    @Override
    protected ChannelHandler createChildChannelHandler() {
        return new DiscardServerClildChannelHadler();
    }

    public static void main(String[] args) throws Exception{
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        DiscardTcpServer discardServer = new DiscardTcpServer(port);
        discardServer.run();
    }

}
