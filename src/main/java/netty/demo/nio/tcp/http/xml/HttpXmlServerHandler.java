package netty.demo.nio.tcp.http.xml;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpRequest;
import netty.demo.nio.tcp.http.business.Address;
import netty.demo.nio.tcp.http.business.Customer;
import netty.demo.nio.tcp.http.business.Order;

/**
 * Created by niuqinghua on 2015/7/14.
 */
public class HttpXmlServerHandler extends SimpleChannelInboundHandler<HttpXmlRequest> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, HttpXmlRequest msg) throws Exception {
        HttpRequest request = msg.getRequest();
        Order order = (Order) msg.getBody();
        System.out.println("request: " + order);
        doBusiness(order);
        ChannelFuture future = ctx.writeAndFlush(new HttpXmlResponse(null, order));
    }

    private void doBusiness(Order order) {
        setName(order);
        setAddress(order);
    }

    private void setAddress(Order order) {
        Address address = order.getBillTo();
        address.setCity("city");
        address.setCountry("country");
        address.setState("state");
        address.setPostCode("100000");
        order.setBillTo(address);
        order.setShipTo(address);
    }

    private void setName(Order order) {
        Customer customer = order.getCustomer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        List<String> middleNames = new ArrayList<String>();
        middleNames.add("middleName");
        customer.setMiddleName(middleNames);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
