package com.wsmt.middleware.endpoints;

import com.wsmt.middleware.endpoints.file.IFileService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

@Configuration
public class ExampleClient {


    @Bean
    public FileServiceBean fileServiceBean() {
        return new FileServiceBean();
    }

    @Bean
    public RmiProxyFactoryBean exporter() throws UnknownHostException {
        RmiProxyFactoryBean rpfb = new RmiProxyFactoryBean();
        rpfb.setServiceInterface(IFileService.class);
        String hostAddress = Inet4Address.getLocalHost()
                .getHostAddress();
        rpfb.setServiceUrl(String.format("rmi://%s:2099/IFileService", hostAddress));
        return rpfb;
    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ExampleClient.class);
        FileServiceBean bean = context.getBean(FileServiceBean.class);
        System.out.println("hello client");
        bean.indexFilesUnderPath("D:/github/wsmt-middleware/root");
        List<FileInformation> result = bean.retrieveFileInformation();
        System.out.println(result);
        System.out.println(bean.filterFileInfo("file1", "", "", ""));
    }

    public static void runConsole() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ExampleClient.class);
        FileServiceBean bean = context.getBean(FileServiceBean.class);
        System.out.println("Middleware console");
        System.out.println("Options: 1. Index files 2. Filter files");

        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        switch (i) {
            case 1:
                System.out.println("Insert context root:");
                String contextRootInput = sc.next();
                try {
                    bean.indexFilesUnderPath(contextRootInput);
                } catch (Exception e) {

                }
                break;
            case 2:

                break;

            default:
                runConsole();
        }
    }
}