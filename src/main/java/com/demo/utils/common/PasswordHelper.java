package com.demo.utils.common;

import com.demo.model.sys.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chenzhi on 2017/11/12 0012.
 */
public class PasswordHelper {

    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void encryptPassword(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String newPassword = new SimpleHash(algorithmName, user.getPassword(), null, hashIterations).toHex();
        user.setPassword(newPassword);

    }

    public String encryptPassword(User user,String password){
        password = new SimpleHash(algorithmName, password, null, hashIterations).toHex();
        return password;
    }

/*    public static void main(String[] args) {
        PasswordHelper passwordHelper = new PasswordHelper();
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("zhangsan");
        try {
            passwordHelper.encryptPassword(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(user.getPassword());
        System.out.println(user);
    }*/
}
