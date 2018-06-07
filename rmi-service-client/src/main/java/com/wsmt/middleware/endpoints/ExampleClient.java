package com.wsmt.middleware.endpoints;

import com.wsmt.middleware.service.IFileService;
import org.springframework.context.annotation.Configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Properties;

@Configuration
public class ExampleClient {

    public ExampleClient()
            throws Exception {
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", "com.sun.jndi.cosnaming.CNCtxFactory");
        props.setProperty("java.naming.provider.url", "iiop://localhost:3000");
        Context ctx = new InitialContext(props);
        Object ref = ctx.lookup("fileService");
        IFileService proxy = (IFileService) PortableRemoteObject.narrow(ref, IFileService.class);
        System.out.println(proxy.retrieveFileInformation());
    }

    public static void main(String[] args) throws Exception {
        new ExampleClient();
    }

//    @Bean
//    public FileServiceBean fileServiceBean() {
//        return new FileServiceBean();
//    }
//
//    @Bean
//    public RmiProxyFactoryBean exporter() throws UnknownHostException {
//        RmiProxyFactoryBean rpfb = new RmiProxyFactoryBean();
//        rpfb.setServiceInterface(IFileService.class);
//        String hostAddress = Inet4Address.getLocalHost()
//                .getHostAddress();
//        rpfb.setServiceUrl(String.format("rmi://%s:2099/IFileService", hostAddress));
//        return rpfb;
//    }
//
//    public static void main(String[] args) throws Exception {
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(ExampleClient.class);
//        FileServiceBean bean = context.getBean(FileServiceBean.class);
//        System.out.println("hello client");
//        bean.indexFilesUnderPath("D:/github/wsmt-middleware/root");
//        List<FileInformation> result = bean.retrieveFileInformation();
//        System.out.println(result);
//        System.out.println(bean.filterFileInfo("file1", "", "", ""));
//    }
//
//    public static void runConsole() {
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(ExampleClient.class);
//        FileServiceBean bean = context.getBean(FileServiceBean.class);
//        System.out.println("Middleware console");
//        System.out.println("Options: 1. Index files 2. Filter files");
//
//        Scanner sc = new Scanner(System.in);
//        int i = sc.nextInt();
//        switch (i) {
//            case 1:
//                System.out.println("Insert context root:");
//                String contextRootInput = sc.next();
//                try {
//                    bean.indexFilesUnderPath(contextRootInput);
//                } catch (Exception e) {
//
//                }
//                break;
//            case 2:
//
//                break;
//
//            default:
//                runConsole();
//        }
//    }
}