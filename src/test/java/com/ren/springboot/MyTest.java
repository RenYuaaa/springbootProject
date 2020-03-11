package com.ren.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Encoder;
import sun.security.provider.MD5;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {
    private static String  salt = "!Q@W#E$RRT%TT^YY";

    @Test
    public void testMd5() {

        String md5 = DigestUtils.md5DigestAsHex("123456".getBytes());
        BASE64Encoder base64Encoder =new BASE64Encoder();

        String encode = base64Encoder.encode((md5).getBytes());
        System.out.println(encode);
    }
}
