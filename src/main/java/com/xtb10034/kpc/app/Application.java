package com.xtb10034.kpc.app;


import com.xtb10034.kpc.service.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication(scanBasePackages = {"com.xtb10034.kpc"})
@EnableCaching
@EnableScheduling
@EnableJms
public class Application {
    private static Log logger = LogFactory.getLog(Application.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context =  SpringApplication.run(Application.class, args);
        Service service = context.getBean(Service.class);
        try{
            service.startProcess();
        }catch(Exception e){
            logger.error("Clean Pod with error " + e.getMessage());
        }
    }
}