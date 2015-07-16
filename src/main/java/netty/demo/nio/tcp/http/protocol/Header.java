package netty.demo.nio.tcp.http.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niuqinghua on 2015/7/16.
 */
public final class Header {

    private int crcCode = 0xabef0101;

    private int length;

    private long sessionId;

    private byte type;

    private byte priority;

    private Map<String, Object> attachment = new HashMap<String, Object>();

    public final int getCrcCode() {
        return crcCode;
    }

    public final void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public final int getLength() {
        return length;
    }

    public final void setLength(int length) {
        this.length = length;
    }

    public final long getSessionId() {
        return sessionId;
    }

    public final void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public final byte getType() {
        return type;
    }

    public final void setType(byte type) {
        this.type = type;
    }

    public final byte getPriority() {
        return priority;
    }

    public final void setPriority(byte priority) {
        this.priority = priority;
    }

    public final Map<String, Object> getAttachment() {
        return attachment;
    }

    public final void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }
}
