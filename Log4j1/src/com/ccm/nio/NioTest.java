package com.ccm.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;


public class NioTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException  {
		
		/*String relativelyPath = System.getProperty("user.dir");
		System.out.println("relativelyPath");*/
		 
        FileInputStream input = new FileInputStream("D:/logsmarginBatch-2018-02-01.0.log");
        //	从流中获取通道
        ReadableByteChannel source = input.getChannel();
        FileOutputStream output = new FileOutputStream("D:/ccm.log");
        //  从流中获取通道
        WritableByteChannel destination = output.getChannel();
        
        //	通信：读和写
        copyData(source, destination);
        source.close();
        destination.close();
        System.out.println("Copy Data finished.");
	}
	
	
	
	private static void copyData(ReadableByteChannel src, WritableByteChannel dest) throws IOException  {
        ByteBuffer buffer = ByteBuffer.allocateDirect(20 * 1024);
        while (src.read(buffer) != -1) {
            // The buffer is used to drained
            buffer.flip();
            // keep sure that buffer was fully drained
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
            // Now the buffer is empty, ready for the filling
            buffer.clear(); 
        }
    }

}
