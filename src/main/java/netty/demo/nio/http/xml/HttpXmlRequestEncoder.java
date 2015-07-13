package netty.demo.nio.http.xml;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpHeaderValues.*;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by niuqinghua on 2015/7/13.
 */
public class HttpXmlRequestEncoder extends AbstractHttpXmlEncoder<HttpXmlRequest> {

    public static final HttpMethod GET = HttpMethod.GET;

    public static final HttpVersion HTTP_11 = HttpVersion.HTTP_1_1;

    @Override
    protected void encode(ChannelHandlerContext ctx, HttpXmlRequest msg, List<Object> out) throws Exception {
        ByteBuf body = encode0(ctx, msg.getBody());
        FullHttpRequest request = msg.getRequest();

        if (request == null) {
            request = createDefaultFullHttpRequest(body);
        }

        HttpHeaderUtil.setContentLength(request, body.readableBytes());
        out.add(request);
    }

    private FullHttpRequest createDefaultFullHttpRequest(ByteBuf body) {
        FullHttpRequest request = new DefaultFullHttpRequest(HTTP_11, GET, "/do", body);
        try {
            HttpHeaders httpHeaders = request.headers();
            httpHeaders.set(HOST, getHostAddress());
            httpHeaders.set(CONNECTION, CLOSE);
            httpHeaders.set(ACCEPT_ENCODING, GZIP.toString() + "," + DEFLATE.toString());
            httpHeaders.set(ACCEPT_CHARSET, "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
            httpHeaders.set(ACCEPT_LANGUAGE, "zh");
            httpHeaders.set(USER_AGENT, "Netty xml Http Client side");
            httpHeaders.set(ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            return request;
        } catch (UnknownHostException e) {
            return null;
        }
    }

    private String getHostAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

}
