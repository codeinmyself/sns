package com.puckteam.sns.core.support.encrypt;

/**
 * Created by PoemWhite on 2017/3/31.
 */
public interface Encrypt {

    public String encrypt(String encrypt);

    public Boolean checkPassword(String password, String encryptPassword);
}
