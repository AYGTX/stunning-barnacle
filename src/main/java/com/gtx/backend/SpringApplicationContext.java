package com.gtx.backend;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
//THIS NEEDS TO BE ADDED TO BE AVAILABLE AS A BEAN
public class SpringApplicationContext implements ApplicationContextAware {
    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        CONTEXT = context;
    }
// GIVING BEAN NAME SO IT CAN RETURN THE LAREADY CRATED SPRING FRAMEWORK BEAN
    // WHEN USERSERVICEIMPLEMENTATION IS CREATED WE CAN ACCESS IT THROUGH APPICATIONCONTEXT
        //WE THEN CAN GET ACCESS TO USERSERVICEIMPLEMENTATION IF WE HAVE ACCESS TO SPRINGAPPCONTEXT
    public static Object getBean(String beanName) {
        return CONTEXT.getBean(beanName);
    }
}
