package netty.demo.nio.tcp.http.xml;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import netty.demo.nio.tcp.http.utils.JacksonUtils;

import java.nio.charset.Charset;

/**
 * Created by niuqinghua on 2015/7/13.
 */
public abstract class AbstractHttpXmlEncoder<T> extends MessageToMessageEncoder<T> {

    private final static String CHARSET_NAME = "UTF-8";

    private final static Charset UTF_8 = Charset.forName(CHARSET_NAME);

    protected ByteBuf encode0(ChannelHandlerContext ctx, Object object) {
        try {
            return Unpooled.copiedBuffer(JacksonUtils.objToXml(object), UTF_8);
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
