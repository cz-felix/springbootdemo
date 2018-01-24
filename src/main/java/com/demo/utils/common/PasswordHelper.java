package com.demo.utils.common;

import com.demo.model.sys.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chenzhi on 2017/11/12 0012.
 */
public class PasswordHelper {

    private static String algorithmName = "md5";
    private static int hashIterations = 2;

    public static void encryptPassword(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String newPassword = new SimpleHash(algorithmName, user.getPassword(), null, hashIterations).toHex();
        user.setPassword(newPassword);

    }

    public static String encryptPassword(String password){
        password = new SimpleHash(algorithmName, password, null, hashIterations).toHex();
        return password;
    }

    @Test
    public void test() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("zhangsan");
        try {
            encryptPassword(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(user.getPassword());
        System.out.println(user);
    }
}
