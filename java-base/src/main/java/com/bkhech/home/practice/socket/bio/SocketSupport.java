package com.bkhech.home.practice.socket.bio;

import java.io.*;
import java.net.Socket;

import static com.bkhech.home.practice.socket.bio.Constants.BUFFER;

/**
 * 支持类
 *
 * @author guowm
 * @date 2021/12/10
 */
public final class SocketSupport {

    /**
     * 分段读
     *
     * @param br
     * @return
     * @throws IOException
     */
    public static StringBuffer segmentRead(BufferedReader br) throws IOException {
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb;
    }


    /**
     * 分段写
     *
     * @param bytes
     * @param outputStream
     * @throws IOException
     */
    public static void segmentWrite(byte[] bytes, OutputStream outputStream) throws IOException {
        int length = bytes.length;
        int start, end = 0;
        int i = 0;
        while (end != bytes.length) {
            start = i == 0 ? 0 : i * BUFFER;
            end = length > BUFFER ? start + BUFFER : bytes.length;
            length -= BUFFER;
            outputStream.write(bytes, start, end - start);
            outputStream.flush();
            i++;
        }
    }

    /**
     * 关闭各种流
     *
     * @param socket
     * @param outputStream
     * @param inputStream
     * @param inputStreamReader
     * @param bufferedReader
     * @throws IOException
     */
    public static void close(Socket socket,
                             OutputStream outputStream,
                             InputStream inputStream,
                             InputStreamReader inputStreamReader,
                             BufferedReader bufferedReader) throws IOException {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.shutdownOutput();
            } catch (IOException e) {
            }
            try {
                socket.shutdownInput();
            } catch (IOException e) {
            }
            try {
                socket.close();
            } catch (IOException e) {
            }
        }

        if (outputStream != null) {
            outputStream.close();
        }

        if (inputStream != null) {
            inputStream.close();
        }

        if (inputStreamReader != null) {
            inputStreamReader.close();
        }

        if (bufferedReader != null) {
            bufferedReader.close();
        }
    }

}
