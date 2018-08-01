package com.styzf.core.common.util;

import java.util.List;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Orika简单的转换工具类
 * @author styzf
 * @date 2018年7月27日 
 *
 */
public class OrikaUtil {
    private static MapperFacade mapper = null;
    
    static {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapper = mapperFactory.getMapperFacade();
    }
    
    public static <S, D> D map(S source, Class<D> destinationClass) {
        return (D)mapper.map(source, destinationClass);
    }
    
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<D> destinationClass) {
        return mapper.mapAsList(sourceList, destinationClass);
    }
    
    public static MapperFacade getMapperFacade() {
        return mapper;
    }
}
