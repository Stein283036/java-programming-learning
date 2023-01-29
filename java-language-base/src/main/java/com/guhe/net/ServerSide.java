package com.guhe.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author njl
 * @date 2023/1/30
 */
public class ServerSide {
	public static void main(String[] args) {
		while (true) {
			try (ServerSocket serverSocket = new ServerSocket(9000);) {
				Socket socket = serverSocket.accept();
				System.out.println("socket = " + socket);
				OutputStream os = socket.getOutputStream();
				os.write("Hello client".getBytes(StandardCharsets.UTF_8));
				socket.close();
			} catch (IOException e) {
				return;
			}
		}
	}
}
