package com.yxc.common.utils;

        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.BeansException;
        import org.springframework.context.ApplicationContext;
        import org.springframework.context.ApplicationContextAware;
        import org.springframework.stereotype.Component;

@Component
@Slf4j
/**
 * 在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象
 * **/
public class SpringBeanUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringBeanUtil.applicationContext==null){
            SpringBeanUtil.applicationContext = applicationContext;
        }
        log.info("========ApplicationContext配置成功,applicationContext=:{}",SpringBeanUtil.applicationContext);
    }
    //获取applicationContext
    public static ApplicationContext getApplicationContext(){
         return applicationContext;
    }
    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }
    //通过class获取Bean.
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

}
