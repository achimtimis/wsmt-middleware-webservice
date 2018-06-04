package com.wsmt.middleware;

import com.wsmt.middleware.service.FileService;
import com.wsmt.middleware.service.IFileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.UnknownHostException;

@SpringBootApplication
public class MiddlewareApplication {


    @Bean
    public IFileService orderService() {
        return new FileService();
    }

    @Bean
    public RmiServiceExporter exporter() throws UnknownHostException {
        RmiServiceExporter rse = new RmiServiceExporter();
        rse.setServiceName("IFileService");
        rse.setService(orderService());
        rse.setServiceInterface(IFileService.class);
        rse.setRegistryPort(2099);
        return rse;
    }

    //		public static void main(String[] args) {
//			new AnnotationConfigApplicationContext(MiddlewareApplication.class);
//		}
//
//	}
//
    public static void main(String[] args) {
//		System.setSecurityManager(new SecurityManager());
        SpringApplication.run(MiddlewareApplication.class, args);
    }
}
