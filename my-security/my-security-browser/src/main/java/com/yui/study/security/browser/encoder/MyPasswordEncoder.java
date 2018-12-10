package com.yui.study.security.browser.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * PasswordEncoder
 *
 * @author XuZhuohao
 * @date 2018/12/10
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }
}
