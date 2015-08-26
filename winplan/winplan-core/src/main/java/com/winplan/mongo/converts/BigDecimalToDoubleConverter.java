/**
 * BigDecimalToDoubleConverter.java.java
 * @author FengMy
 * @since 2015年8月25日
 */
package com.winplan.mongo.converts;

import java.math.BigDecimal;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月25日
 */
@Component
public class BigDecimalToDoubleConverter implements Converter<BigDecimal, Double> {

    @Override
    public Double convert(BigDecimal source) {
        return source.doubleValue();
    }
}
