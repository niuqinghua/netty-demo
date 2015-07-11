package netty.demo.nio.javaserializable;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by niuqinghua on 2015/7/12.
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq) msg;
        if ("userName".equals(req.getUserName())) {
            System.out.println("Service accept client subscribe req: " + req.toString());
            ctx.writeAndFlush(createResp(req));
        }
    }

    private SubscribeResp createResp(SubscribeReq req) {
        SubscribeResp resp = new SubscribeResp();
        resp.setSubReqId(req.getSubReqId());
        resp.setRespCode(0);
        resp.setDesc("Netty book order succeed.");
        return resp;
    }

}
