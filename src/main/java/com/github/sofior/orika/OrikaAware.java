package com.github.sofior.orika;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class OrikaAware implements ApplicationContextAware {

    @Bean
    public MapperFacade mapperFacade() {
        return mapperFactory().getMapperFacade();
    }

    @Bean
    public MapperFactory mapperFactory() {
        return new DefaultMapperFactory.Builder().build();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, OrikaMapper> beansOfType = applicationContext.getBeansOfType(OrikaMapper.class);
        final MapperFactory mapperFactory = mapperFactory();
        beansOfType.forEach((s, mapper) -> mapper.register(mapperFactory));
    }
}
