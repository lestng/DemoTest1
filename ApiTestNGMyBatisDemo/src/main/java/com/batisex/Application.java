package com.batisex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
public class Application {
 //   private  static ConfigurableApplicationContext context;

    public static void main(String[] args){
    //   Application.context= SpringApplication.run(Application.class,args);
     SpringApplication.run(Application.class,args);
    }

/*    @PreDestroy
    public void close(){
        Application.context.close();
    }*/

}
