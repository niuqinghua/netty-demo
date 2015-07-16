package netty.demo.nio.tcp.http.protocol;

import java.time.temporal.ValueRange;
import java.util.List;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

/**
 * Created by niuqinghua on 2015/7/16.
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

    private NettyMarshallingEncoder marshallingEncoder = NettyMarshallingEncoder.buildMarshallingEncoder();

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
        ByteBuf sendBuf = Unpooled.buffer();
        Header header = msg.getHeader();
        sendBuf.writeInt(header.getCrcCode());
        sendBuf.writeInt(header.getLength());
        sendBuf.writeLong(header.getSessionId());
        sendBuf.writeByte(header.getType());
        sendBuf.writeByte(header.getPriority());
        Map<String, Object> attachment = header.getAttachment();
        sendBuf.writeInt(attachment.size());

        for (Map.Entry<String, Object> param : attachment.entrySet()) {
            String key = param.getKey();
            byte[] keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            Object value = param.getValue();
            marshallingEncoder.encode(ctx, value, sendBuf);
        }

        Object body = msg.getBody();
        if (body != null) {
            marshallingEncoder.encode(ctx, body, sendBuf);
        } else {
            sendBuf.writeInt(0);
            sendBuf.setInt(4, sendBuf.readableBytes());
        }

    }

}
