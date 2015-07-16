package netty.demo.nio.tcp.http.protocol;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

/**
 * Created by niuqinghua on 2015/7/16.
 */
public class NettyMarshallingDecoder extends MarshallingDecoder {

    public NettyMarshallingDecoder(UnmarshallerProvider provider, int maxObjectSize) {
        super(provider, maxObjectSize);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        return super.decode(ctx, in);
    }

    public static NettyMarshallingDecoder buildMarshallingDecoder() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration marshallingConfiguration = new MarshallingConfiguration();
        marshallingConfiguration.setVersion(5);
        UnmarshallerProvider unmarshallerProvider = new DefaultUnmarshallerProvider(marshallerFactory, marshallingConfiguration);
        return new NettyMarshallingDecoder(unmarshallerProvider, 1024);
    }

}
