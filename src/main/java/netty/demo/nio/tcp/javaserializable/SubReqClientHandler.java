package netty.demo.nio.tcp.javaserializable;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by niuqinghua on 2015/7/12.
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(createSubscribeReq(i));
        }
        ctx.flush();
    }

    private SubscribeReq createSubscribeReq(int subReqId) {
        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setSubReqId(subReqId);
        subscribeReq.setAddress("address");
        subscribeReq.setPhoneNumber("131XXXXXXXX");
        subscribeReq.setProductName("productName");
        subscribeReq.setSubReqId(subReqId);
        subscribeReq.setUserName("userName");
        return subscribeReq;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive server response: " + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
