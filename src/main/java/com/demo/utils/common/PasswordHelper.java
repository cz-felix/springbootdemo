package com.demo.utils.common;

import com.demo.model.sys.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by chenzhi on 2017/11/12 0012.
 */
public class PasswordHelper {

    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void encryptPassword(User user) {
        String newPassword = new SimpleHash(algorithmName, user.getPwd(),  ByteSource.Util.bytes(user.getUsername()), hashIterations).toHex();
        user.setPwd(newPassword);

    }

    public String encryptPassword(User user,String password){
        password = new SimpleHash(algorithmName, password,  ByteSource.Util.bytes(user.getUsername()), hashIterations).toHex();
        return password;
    }

    public static void main(String[] args) {
        PasswordHelper passwordHelper = new PasswordHelper();
        User user = new User();
        user.setUsername("admin");
        user.setPwd("admin");
        passwordHelper.encryptPassword(user);
        System.out.println(user);
    }
}
