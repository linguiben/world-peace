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
import java.nio.MappedByteBuffer;
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

    /**
    * @desc write to a file
    * @author  Jupiter.Lin
    * @date  2024-02-04 16:22
    *
    */
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

    /**
    * @desc read from a file
    * @author  Jupiter.Lin
    * @date  2024-02-04 16:22
    *
    */
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

    /**
    * @desc file -> fileInputStream(channel.read()) -> buffer -> fileOutputSteam(channel.write()) -> file2
    * @author  Jupiter.Lin
    * @date  2024-02-04 16:09
    *
    */
    @Test
    public void testCopyFile() throws IOException{

        // 1. new FileInputStream, Buffer and FileOutputStream
        FileInputStream fileInputStream = new FileInputStream(FileChannelTest.filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(FileChannelTest.filePath+".bk");
        ByteBuffer buffer = ByteBuffer.allocate(10); // 测试极小buffer

        // 2. get channel from Stream
        FileChannel srcChannel = fileInputStream.getChannel();
        FileChannel destChannel = fileOutputStream.getChannel();

        // 3. inputChannel.read() from file and put into buffer
        //    outputChannel.write() to the file form the buffer
        while (true){
            buffer.clear(); // It's very important to reset the buffer
            int read = srcChannel.read(buffer);
            if(read == -1){
                break;
            }
            buffer.flip(); // 3.1 buffer.flip() to read mode
            destChannel.write(buffer);
        }
        // 也可以用transferFrom完成文件拷贝
//        destChannel.transferFrom(srcChannel,0,srcChannel.size());

        // 4. close Stream
        srcChannel.close();
        destChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    @Test
    void testMappedByteBuffer() throws IOException {
        // 1. create a wr RandomAccessFile
        RandomAccessFile randomAccessFile = new RandomAccessFile(FileChannelTest.filePath,"rw");

        // 2. get the fileChannel
        FileChannel channel = randomAccessFile.getChannel();

        // 3. get the MappedByteBuffer
        /**
        * @desc 参数: 读写模式, 映射的起始位置, 大小
        * @author  Jupiter.Lin
        * @date  2024-02-04 17:24
        */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 10);

        // 4. 操作 buffer
        mappedByteBuffer.put(0,(byte) 'H');
        mappedByteBuffer.put(4,(byte) 'A');

        // 5. close
        randomAccessFile.close();
        log.info("mappedByteBuffer done.");
    }
}
