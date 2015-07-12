package netty.demo.nio.http.xml;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.List;

/**
 * Created by niuqinghua on 2015/7/13.
 */
public class HttpXmlRequestDecoder extends AbstractHttpXmlDecoder<FullHttpRequest> {

    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpRequest msg, List<Object> out) throws Exception {
        if (!msg.decoderResult().isSuccess()) {
            sendError(ctx, HttpResponseStatus.BAD_REQUEST);
            return;
        }

        out.add(new HttpXmlRequest(msg, decode0(ctx, msg.content(), FullHttpRequest.class)));
    }

    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {

    }

}
