package netty.demo.nio.http.xml;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderUtil;
import io.netty.handler.codec.http.HttpHeaders;

import java.util.List;

/**
 * Created by niuqinghua on 2015/7/13.
 */
public class HttpXmlRequestEncoder extends AbstractHttpXmlEncoder<HttpXmlRequest> {

    @Override
    protected void encode(ChannelHandlerContext ctx, HttpXmlRequest msg, List<Object> out) throws Exception {
        ByteBuf body = encode0(ctx, msg.getBody());
        FullHttpRequest request = msg.getRequest();

        if (request == null) {
            request = createDefaultFullHttpRequest();
        }

        HttpHeaderUtil.setContentLength(request, body.readableBytes());
        out.add(request);
    }

    private FullHttpRequest createDefaultFullHttpRequest() {
        return null;
    }

}
