/**
 * ApplicationContextAware.java
 * @since 2014年6月13日
 */
package com.platforms.core.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @desc spring上下文
 * @author fengmengyue
 *
 * @since 2014年6月13日
 */
@Component
public class ApplicationContextAware implements org.springframework.context.ApplicationContextAware {

	private static ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
                    throws BeansException {
            ApplicationContextAware.ctx = applicationContext;
    }
    
    public static ApplicationContext getApplicationContext(){
            return ctx;
    }

}
