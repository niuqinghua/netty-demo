package netty.demo.nio.tcp.http.protocol;

/**
 * Created by niuqinghua on 2015/7/16.
 */
public final class NettyMessage {

    private Header header;

    private Object body;

    public final Header getHeader() {
        return header;
    }

    public final void setHeader(Header header) {
        this.header = header;
    }

    public final Object getBody() {
        return body;
    }

    public final void setBody(Object body) {
        this.body = body;
    }
}
