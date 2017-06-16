package com.damuzee;

import com.damuzee.provider.IProviderService;
import com.damuzee.provider.impl.DefaultProviderService;
import org.osgi.framework.*;

import java.net.InetAddress;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

/**
 * Created by karka.w on 2016/8/8.
 *
 * @TODO  这里有个坑，import-package的包中包括和本类相同的目录，可能会报一个找不到改类的错误
 */
public class ProviderActivator implements BundleActivator ,ServiceListener,BundleListener{

    Logger logger = Logger.getLogger(ProviderActivator.class.getName());

    private static final Integer RMI_PORT = 1099;

    private Registry rmiRegistry ;
    private BundleContext context;
    private ServiceRegistration providerRegistration ;

    @Override
    public void start(BundleContext context) throws RemoteException, InvalidSyntaxException {

        this.context = context ;

        logger.info("启动  ProviderActivator .....................");
        IProviderService providerService = new DefaultProviderService(context);
        providerRegistration = context.registerService(IProviderService.class.getName(),providerService,null);
        //ProviderServiceFactory providerServiceFactory= new ProviderServiceFactory();
       // serviceRegistration  = context.registerService(IProviderService.class.getName(), providerServiceFactory,null);

        //startRmiServer((IProviderService) providerServiceFactory.getService( context.getBundle(),serviceRegistration));
        startRmiServer(providerService);
        getProviderInfo();
    }

    /**
     *
     * @param bundleEvent
     * 获取Provider信息网络地址
     *
     */
    public void getProviderInfo() throws InvalidSyntaxException {

        // TODO 获取所有的服务
        ServiceReference[] allServiceReferences = context.getServiceReferences(null, null);
        for (ServiceReference service: allServiceReferences){
            System.out.println("----" +service);
        }

        // TODO 获取本地IP地址
        InetAddress ia=null;
        try {
            ia = ia.getLocalHost();

            String localname = ia.getHostName();
            String localip = ia.getHostAddress();
            System.out.println("本机名称是：" + localname);
            System.out.println("本机的ip是 ：" + localip);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void bundleChanged(BundleEvent bundleEvent) {
        logger.info("IProviderService BundleListener  bundleChanged .....................");
    }

    @Override
    public void serviceChanged(ServiceEvent serviceEvent) {
        logger.info("IProviderService ServiceListener  serviceEvent .....................");
    }

    /**
     * 启动 rmi server .
     * @param service the service.
     * @throws RemoteException re.
     */
    private void startRmiServer(IProviderService service) throws RemoteException {
        rmiRegistry = createRegistry();

        logger.info("发布远程服务  IProviderService .....................");

        IProviderService theService = (IProviderService) UnicastRemoteObject.exportObject(service,0);
        // 注册 appService 远程服务
        rmiRegistry.rebind("appService", theService);
    }

    /**
     * 创建一个Registry对象.
     * LocateRegistry用于获取名字服务或创建名字服务.
     * 调用LocateRegistry.createRegistry(int port)方法可以在某一特定端口创建名字服务,从而用户无需再手工启动rmiregistry
     * @return 返回一个Registry对象
     */
    private static Registry createRegistry() {
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(RMI_PORT); //如果该端口未被注册，则抛异常
            registry.list(); //拿到该端口注册的rmi对象
        } catch (final Exception e) {
            try {
                registry = LocateRegistry.createRegistry(RMI_PORT);//捕获异常，端口注册
            } catch (final Exception ee) {
                ee.printStackTrace();
            }
        }
        return registry;
    }

    @Override
    public void stop(BundleContext context) {
        try {
            providerRegistration.unregister();
            rmiRegistry.unbind("appService");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        logger.info("停止 ProviderActivator .....................");
    }

}
