package com.stein.net.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author njl
 * @date 2023/2/7
 */
public interface WorldClock extends Remote { // jls 规定 必须继承 Remote 接口，且方法必须抛出 RemoteException异常
    LocalDateTime getLocalDateTimeWithZone(ZoneId zoneId) throws RemoteException;
}
