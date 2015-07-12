package netty.demo.nio.http.xml;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Created by niuqinghua on 2015/7/13.
 */
public class HttpXmlRequest {

    private FullHttpRequest request;

    private Object body;

    public HttpXmlRequest(FullHttpRequest request, Object body) {
        this.request = request;
        this.body = body;
    }

    public FullHttpRequest getRequest() {
        return request;
    }

    public void setRequest(FullHttpRequest request) {
        this.request = request;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
