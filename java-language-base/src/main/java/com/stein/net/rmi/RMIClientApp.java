package com.stein.net.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author njl
 * @date 2023/2/7
 */
public class RMIClientApp {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        // 获得本地1099端口的远程对象注册中心,默认端口1099
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        // 查找名称为 worldClockStub 的服务接口的实现 并将其强制转换为 worldClock 接口引用
        // 客户端只有接口，并没有实现类 客户端和远程 RMI 服务端 只通过接口进行通信
        WorldClock worldClock = (WorldClock) registry.lookup("worldClockStub");
        // 调用 worldClock 服务
        LocalDateTime now = worldClock.getLocalDateTimeWithZone(ZoneId.systemDefault());
        System.out.println("now = " + now);
    }
}
