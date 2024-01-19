package com.stein.net.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author njl
 * @date 2023/1/30
 */
public class ClientSide {
    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 9000)) {
            InputStream is = socket.getInputStream();
            byte[] buf = new byte[8096];
            int read;
            StringBuilder msgFromServer = new StringBuilder();
            while ((read = is.read(buf)) != -1) {
                msgFromServer.append(new String(buf, 0, read, StandardCharsets.UTF_8));
            }
            System.out.println("msgFromServer = " + msgFromServer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
