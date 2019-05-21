package com.github.sofior.orika;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;

public interface OrikaMapper {

    void register(MapperFactory mapperFactory);

    void registerConverter(ConverterFactory converterFactory);
}
