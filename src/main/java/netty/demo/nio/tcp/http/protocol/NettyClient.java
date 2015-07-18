package netty.demo.nio.tcp.http.protocol;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * Created by niuqinghua on 2015/7/18.
 */
public class NettyClient {

    private String ip;

    private int port;

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public NettyClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(createChannelHandler());
            ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        run();
                    } catch (Exception e) {

                    }
                }
            });
        }
    }

    private ChannelHandler createChannelHandler() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                pipeline.addLast("messageEncoder", new NettyMessageEncoder());
                pipeline.addLast("readTimeOutHandler", new ReadTimeoutHandler(50));
                pipeline.addLast("loginAuthHandler", new LoginAuthReqHandler());
                pipeline.addLast("heartBeatHandler", new HeartBeatReqHandler());
            }
        };
    }

    public static void main(String[] args) throws Exception {
        String ip = args.length > 0 ? args[0] : "127.0.0.1";
        int port = args.length > 0 ? Integer.valueOf(args[0]) : 8080;
        NettyClient client = new NettyClient(ip, port);
        client.run();
    }

}
