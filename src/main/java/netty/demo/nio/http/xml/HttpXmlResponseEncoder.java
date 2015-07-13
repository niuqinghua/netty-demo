package netty.demo.nio.http.xml;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * Created by niuqinghua on 2015/7/13.
 */
public class HttpXmlResponseEncoder extends AbstractHttpXmlEncoder<HttpXmlRequest> {

    @Override
    protected void encode(ChannelHandlerContext ctx, HttpXmlRequest msg, List<Object> out) throws Exception {

    }

}
