package com.guhe.net.rmi;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author njl
 * @date 2023/2/7
 */
public class WorldClockImpl implements WorldClock {
	@Override
	public LocalDateTime getLocalDateTimeWithZone(ZoneId zoneId) throws RemoteException {
		return LocalDateTime.now(zoneId);
	}
}
