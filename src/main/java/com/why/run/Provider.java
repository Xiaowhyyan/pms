package com.why.run;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 *  启动系统
 */
public class Provider {
    private static final Logger log = Logger.getLogger(Provider.class);

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"provider.xml"});
        context.start();
        //System.out.println("Provider started.");
        log.info("项目日志 启动");
        System.in.read(); // press any key to exit
    }
}
