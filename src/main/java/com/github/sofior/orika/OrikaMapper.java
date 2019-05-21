package com.github.sofior.orika;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;

public interface OrikaMapper {

    default void register(MapperFactory mapperFactory){}

    default void registerConverter(ConverterFactory converterFactory){}
}
