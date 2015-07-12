package netty.demo.nio.http.xml;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import netty.demo.nio.http.utils.JacksonUtils;

import java.nio.charset.Charset;

/**
 * Created by niuqinghua on 2015/7/13.
 */
public abstract class AbstractHttpXmlDecoder<T> extends MessageToMessageDecoder<T> {

    private final static String CHARSET_NAME = "UTF-8";

    private final static Charset UTF_8 = Charset.forName(CHARSET_NAME);

    protected <T> T decode0(ChannelHandlerContext ctx, ByteBuf body, Class<T> classType) {
        try {
            return JacksonUtils.xmlToPojo(body.toString(UTF_8), classType);
        } catch (Exception e) {
            ctx.close();
            return null;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
