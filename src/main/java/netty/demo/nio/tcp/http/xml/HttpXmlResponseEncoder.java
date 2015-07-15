package netty.demo.nio.tcp.http.xml;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

/**
 * Created by niuqinghua on 2015/7/13.
 */
public class HttpXmlResponseEncoder extends AbstractHttpXmlEncoder<HttpXmlResponse> {

    public static final HttpVersion HTTP_11 = HttpVersion.HTTP_1_1;

    public static final HttpResponseStatus OK = HttpResponseStatus.OK;

    public static final String CONTENT_TYPE = "Content-Type";

    @Override
    protected void encode(ChannelHandlerContext ctx, HttpXmlResponse msg, List<Object> out) throws Exception {
        ByteBuf body = encode0(ctx, msg.getResult());
        FullHttpResponse response = getResponse(msg);
        if (response == null) {
            response = new DefaultFullHttpResponse(HTTP_11, OK, body);
        }

        HttpVersion version = response.protocolVersion();
        HttpResponseStatus status = response.status();
        response = new DefaultFullHttpResponse(version, status, body);
        HttpHeaders headers = response.headers();
        headers.set(CONTENT_TYPE, "text/html");
        HttpHeaderUtil.setContentLength(response, body.readableBytes());
        out.add(response);
    }

    private FullHttpResponse getResponse(HttpXmlResponse msg) {
        return msg.getResponse();
    }

}
