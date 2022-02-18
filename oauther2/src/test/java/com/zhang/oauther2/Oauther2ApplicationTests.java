package com.zhang.oauther2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@SpringBootTest
class Oauther2ApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        String encode = pe.encode("123");
        log.info("",encode);
    }

}
