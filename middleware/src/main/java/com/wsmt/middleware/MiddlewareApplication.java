package com.wsmt.middleware;

import com.wsmt.middleware.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

@SpringBootApplication
public class MiddlewareApplication {

    @Autowired
    private FileService fileService;

    @Bean
    public Context context() throws Exception {
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", "com.sun.jndi.cosnaming.CNCtxFactory");
        props.setProperty("java.naming.provider.url", "iiop://localhost:3000");
        Context ctx = new InitialContext(props);
        ctx.rebind("fileService", fileService);
        System.out.println("Java RMI IIOP waiting: locahost:3000/fileService");
        return ctx;
    }
//    public static void main(String args[]) throws Exception {
//        if (args.length > 2)
//            new ExecRmiIiopServ(args[0], args[1], args[2]);
//        else
//            new ExecRmiIiopServ("localhost", "3000", "ExecIiop");
//    }

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
