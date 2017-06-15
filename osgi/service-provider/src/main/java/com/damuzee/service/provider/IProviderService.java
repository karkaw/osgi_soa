package com.damuzee.service.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by karkaw on 2016/8/9.
 */
public interface IProviderService extends Remote {
    /**
     * 得到一个远程服务的名称 .
     * @return .
     * @throws RemoteException .
     */
    Object  invoke(String serverName)  throws RemoteException;



}
