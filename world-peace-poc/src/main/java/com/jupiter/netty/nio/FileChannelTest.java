/**
 * @className FileChannelTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-04 14:53
 */
package com.jupiter.netty.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-04 14:53
 */

@Slf4j
public class FileChannelTest {

    static final String filePath = "/Users/jupiter/Desktop/FileChannelTest.txt";
    // System.getProperty("file.encoding");

    public static void main(String[] args) throws IOException {
        // 1. new FileOutputStream
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);

        // 2. get FileChannel base on the FileOutputSteam
        FileChannel fileChannel = fileOutputStream.getChannel();

        // 3. new a ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 4. put the content into Buffer
        buffer.put("Hello World!\n".getBytes());

        // 5. flip() to switch Buffer to read mode
        buffer.flip();

        // 6. write from Buffer into Channel
        fileChannel.write(buffer);

        // 7. close steam.
        fileOutputStream.close();
    }

    @Test
    public void testReadFromFile() throws IOException {
        FileChannelTest t = new FileChannelTest();

        // 1. new InputStream on the file
        File file = new File(FileChannelTest.filePath);
        InputStream fileInputStream = new FileInputStream(file);

        // 2. get FileChannel from the inputStream
        FileChannel fileChannel = ((FileInputStream)fileInputStream).getChannel();

        // 3. new ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate((int)file.length());

        // 4. read from the channel and put into the buffer
        fileChannel.read(buffer);

        // 5. read from the buffer
        String str = new String(buffer.array(), Charset.forName("UTF-8"));
        log.info(str);

        // 6. close inputStream
        fileInputStream.close();
    }
}
