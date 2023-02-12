package com.guhe.net.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author njl
 * @date 2023/2/7
 */
public class RMIServerApp {
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		System.out.println("正在创建 WorldClock 服务");
		WorldClock worldClock = new WorldClockImpl();
		// 将这个远程对象暴露在指定的端口上，以便接收已将到来的调用
		Remote worldClockStub = UnicastRemoteObject.exportObject(worldClock, 2000);
		// 创建并暴露一个注册实例 接收本机的1099端口的请求
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.bind("worldClockStub", worldClock);
		System.out.println("已经将 WorldClock 服务暴露在网络中供其它客户端调用");
	}
}
