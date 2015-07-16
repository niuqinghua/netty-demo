package netty.demo.nio.tcp.http.protocol;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

/**
 * Created by niuqinghua on 2015/7/16.
 */
public class NettyMarshallingEncoder extends MarshallingEncoder {

    public NettyMarshallingEncoder(MarshallerProvider provider) {
        super(provider);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        super.encode(ctx, msg, out);
    }

    public static NettyMarshallingEncoder buildMarshallingEncoder() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration marshallingConfiguration = new MarshallingConfiguration();
        marshallingConfiguration.setVersion(5);
        MarshallerProvider marshallerProvider = new DefaultMarshallerProvider(marshallerFactory, marshallingConfiguration);
        return new NettyMarshallingEncoder(marshallerProvider);
    }

}
