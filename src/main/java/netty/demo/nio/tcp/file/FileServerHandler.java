package netty.demo.nio.tcp.file;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by niuqinghua on 2015/7/11.
 */
public class FileServerHandler extends SimpleChannelInboundHandler<String> {

    private static final String CR = System.getProperty("line.separator");

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        File file = new File(msg);

        if (!file.exists()) {
            ctx.writeAndFlush("File not found: " + file + CR);
            return;
        }

        if(!file.isFile()) {
            ctx.writeAndFlush("Not a file: " + file + CR);
            return;
        }

        ctx.writeAndFlush(file + " " + file.length() + CR);
        RandomAccessFile randomAccessFile = new RandomAccessFile(msg, "r");
        FileChannel fileChannel = randomAccessFile.getChannel();
        FileRegion region = new DefaultFileRegion(fileChannel, 0, randomAccessFile.length());
        ctx.write(region);
        ctx.writeAndFlush(CR);
        randomAccessFile.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
