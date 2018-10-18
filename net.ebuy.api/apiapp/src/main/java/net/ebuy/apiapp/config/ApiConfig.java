package net.ebuy.apiapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.ebuy.apiapp.handle.BasicInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "net.ebuy.apiapp" })
public class ApiConfig extends WebMvcConfigurerAdapter {

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(new BasicInterceptor()).addPathPatterns("/api/**");
   }
}