package org.pvronlineService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication (scanBasePackages= {"org.pvronlineService"})
public class StartApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(StartApplication.class, args);
    }
}
