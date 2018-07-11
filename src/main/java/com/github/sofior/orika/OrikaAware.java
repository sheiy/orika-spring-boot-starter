package com.github.sofior.orika;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class OrikaAware implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(OrikaAware.class);

    @Bean
    public MapperFacade mapperFacade() {
        return mapperFactory().getMapperFacade();
    }

    @Bean
    @ConditionalOnMissingBean(MapperFactory.class)
    public MapperFactory mapperFactory() {
        logger.debug("custom mapper factory not found use default");
        return new DefaultMapperFactory.Builder().build();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, OrikaMapper> beansOfType = applicationContext.getBeansOfType(OrikaMapper.class);
        final MapperFactory mapperFactory = mapperFactory();
        beansOfType.forEach((s, mapper) -> mapper.register(mapperFactory));
    }
}
