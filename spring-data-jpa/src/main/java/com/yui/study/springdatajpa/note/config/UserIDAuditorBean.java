package com.yui.study.springdatajpa.note.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author XuZhuohao
 * @date 2019/2/25
 */
public class UserIDAuditorBean  implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Long t1 = 1L;
        return Optional.of(t1);
    }
}
