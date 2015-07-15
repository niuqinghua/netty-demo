package netty.demo.nio.tcp.http.xml;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;

import java.util.List;

/**
 * Created by niuqinghua on 2015/7/13.
 */
public class HttpXmlResponseDecoder extends AbstractHttpXmlDecoder<DefaultFullHttpResponse> {

    @Override
    protected void decode(ChannelHandlerContext ctx, DefaultFullHttpResponse msg, List<Object> out) throws Exception {
        HttpXmlResponse response = new HttpXmlResponse(msg, decode0(ctx, msg.content(), DefaultFullHttpResponse.class));
        out.add(response);
    }

}
