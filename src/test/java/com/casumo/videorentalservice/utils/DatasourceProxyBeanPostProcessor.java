package com.casumo.videorentalservice.utils;

import java.lang.reflect.Method;
import javax.sql.DataSource;
import net.ttddyy.dsproxy.listener.QueryExecutionListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
@Profile("integration")
public class DatasourceProxyBeanPostProcessor implements BeanPostProcessor {

  @Autowired
  private QueryExecutionListener queryExecutionListener;

  @Override
  public Object postProcessBeforeInitialization(final Object bean, final String beanName)
      throws BeansException {
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(final Object bean, final String beanName)
      throws BeansException {
    if (bean instanceof DataSource) {
      final ProxyFactory factory = new ProxyFactory(bean);
      factory.setProxyTargetClass(true);
      factory.addAdvice(new ProxyDataSourceInterceptor((DataSource) bean, queryExecutionListener));
      return factory.getProxy();
    }

    return bean;
  }

  private static class ProxyDataSourceInterceptor implements MethodInterceptor {

    private final DataSource dataSource;

    protected ProxyDataSourceInterceptor(
        final DataSource dataSource,
        final QueryExecutionListener queryExecutionListener) {
      this.dataSource = ProxyDataSourceBuilder.create(dataSource)
          .name("Batch-Insert-Logger")
          .asJson()
          .listener(queryExecutionListener)
          .countQuery()
          .build();
    }

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
      final Method proxyMethod = ReflectionUtils.findMethod(dataSource.getClass(),
          invocation.getMethod().getName());
      if (proxyMethod != null) {
        return proxyMethod.invoke(dataSource, invocation.getArguments());
      }
      return invocation.proceed();
    }
  }
}
