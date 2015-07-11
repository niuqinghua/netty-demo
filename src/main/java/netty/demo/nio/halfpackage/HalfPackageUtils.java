package netty.demo.nio.halfpackage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by niuqinghua on 2015/7/9.
 */
public class HalfPackageUtils {

    public static final String QUERY_TIME_ORDER = "query time order";

    public static final String BAD_ORDER = "bad order";

    public static final String DOLLAR_DELIMITER = "$_";

    public static int getLineSeparatorLength() {
        String lineSeparator = getLineSeparator();
        return lineSeparator.length();
    }

    public static String getLineSeparator() {
        return System.getProperty("line.separator");
    }

    public static ByteBuf getDelimiter() {
        return Unpooled.copiedBuffer(HalfPackageUtils.DOLLAR_DELIMITER.getBytes());
    }
}
