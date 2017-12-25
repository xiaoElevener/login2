package com.xiao.login.utils;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;
import static org.junit.Assert.*;

public class PasswordUtilTest {
    @Test
    public void encryptBasedDes() throws Exception {
        String password="123456";
        String newPassword=PasswordUtil.encryptBasedDes(password);
        out.println(newPassword);
    }

    @Test
    public void decryptBasedDes() throws Exception {
        String password="x16YjoF1LNE=";
        String newPassword = PasswordUtil.decryptBasedDes(password);
        out.println(newPassword);
    }

}