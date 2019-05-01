package com.robsonandradev.config;

import com.robsonandradev.handlers.HandlerInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.robsonandradev" })
@ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
public class WebConfig extends WebMvcConfigurerAdapter {

  private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
      "classpath:/META-INF/resources/", "classpath:/resources/",
      "classpath:/static/", "classpath:/public/", "classpath:/favicon.ico" };

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // Register guest interceptor with single path pattern
    registry.addInterceptor(new HandlerInterceptor()).addPathPatterns("/**");

    // Register admin interceptor with multiple path patterns
    registry.addInterceptor(new HandlerInterceptor())
        .addPathPatterns(new String[] { "/admin", "/admin/*" });
  }

  @Bean
  public InternalResourceViewResolver resolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/css");
    resolver.setPrefix("/css/**");
    resolver.setPrefix("/static/**");
    resolver.setSuffix(".css");
    return resolver;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!registry.hasMappingForPattern("/webjars/**")) {
      registry.addResourceHandler("/webjars/**").addResourceLocations(
          "classpath:/META-INF/resources/webjars/");
    }
    if (!registry.hasMappingForPattern("/**")) {
      registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
  }

}
