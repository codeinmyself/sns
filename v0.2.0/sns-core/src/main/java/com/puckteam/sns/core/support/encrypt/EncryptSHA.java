package com.puckteam.sns.core.support.encrypt;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by PoemWhite on 2017/3/31.
 */
public class EncryptSHA implements Encrypt{


    private static EncryptSHA encryptSha = null;

    private EncryptSHA(){

    }

    public static EncryptSHA getInstances()
    {
        if(null == encryptSha){
            encryptSha = new EncryptSHA();
        }

        return encryptSha;
    }

    public String encrypt(String encrypt){

        String result = DigestUtils.sha512Hex(encrypt);

        return result;
    }

    public Boolean checkPassword(String password, String encryptPassword){

        if (password.equals(DigestUtils.sha512Hex(encryptPassword))) {
            return true;
        }
        return false;
    }
}
