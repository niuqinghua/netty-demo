package netty.demo.nio.tcp.http.protocol;

import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Created by niuqinghua on 2015/7/18.
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {


    private NettyMarshallingDecoder marshallingDecoder;

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        this.marshallingDecoder = NettyMarshallingDecoder.buildMarshallingDecoder();
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        ByteBuf frame = (ByteBuf) super.decode(ctx, in);

        if (frame == null) {
            return null;
        }

        Header header = createBasicHeader(ctx,in);
        Object body = getObject(ctx, in);

        return new NettyMessage(header, body);
    }

    private Object getObject(ChannelHandlerContext ctx, ByteBuf in) throws Exception{
        if (in.readableBytes() <= 4) {
            return null;
        }
        return marshallingDecoder.decode(ctx, in);
    }

    private void setAttachmentForHeader(ChannelHandlerContext ctx, ByteBuf in, Header header) throws Exception {
        int size = in.readInt();
        if (size > 0) {
            Map<String, Object> attachment = new HashMap<String, Object>(size);
            for (int i  = 0; i < size; i++) {
                int keySize = in.readInt();
                byte[] keyArray = new byte[keySize];
                in.readBytes(keyArray);
                String key = new String(keyArray, "UTF-8");
                attachment.put(key, marshallingDecoder.decode(ctx, in));
            }
            header.setAttachment(attachment);
        }
    }

    private Header createBasicHeader(ChannelHandlerContext ctx, ByteBuf in) throws Exception{
        Header header = new Header();
        header.setCrcCode(in.readInt());
        header.setLength(in.readInt());
        header.setSessionId(in.readLong());
        header.setType(in.readByte());
        header.setPriority(in.readByte());
        setAttachmentForHeader(ctx, in, header);
        return header;
    }
}
