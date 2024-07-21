package com.mustafatoker.motta;

import com.mustafatoker.motta.common.config.properties.CouchbaseProperties;
import com.mustafatoker.motta.common.config.properties.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtProperties.class, CouchbaseProperties.class})
public class MottaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MottaApplication.class, args);
    }

}
