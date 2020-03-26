package org.milkteaboy.simplefarm.netty.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Spring工具类
 */
@Component
@Lazy(false)
public class SpringUtil  implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 获取ApplicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }
}
