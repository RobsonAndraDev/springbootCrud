package com.robsonandradev.config;

import com.robsonandradev.hendlers.HendlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.robsonandradev" })
public class WebConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // Register guest interceptor with single path pattern
    registry.addInterceptor(new HendlerInterceptor()).addPathPatterns("/**");

    // Register admin interceptor with multiple path patterns
    registry.addInterceptor(new HendlerInterceptor())
        .addPathPatterns(new String[] { "/admin", "/admin/*" });
  }

  @Bean
  public InternalResourceViewResolver resolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/css/*");
    resolver.setPrefix("/css/*");
    resolver.setSuffix(".css");
    return resolver;
  }

}
