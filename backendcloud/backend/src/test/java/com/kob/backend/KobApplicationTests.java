package com.kob.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class KobApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder ps = new BCryptPasswordEncoder();
        System.out.println(ps.encode("tx"));
        System.out.println(ps.encode("syj"));
        System.out.println(ps.encode("czd"));

    }

}
