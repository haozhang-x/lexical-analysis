package cn.cloudx.namevoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author zhang
 */
@SpringBootApplication
@EnableCaching
public class NameVoiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NameVoiceApplication.class, args);
    }
}
