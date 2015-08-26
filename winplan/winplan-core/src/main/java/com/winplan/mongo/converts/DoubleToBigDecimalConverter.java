/**
 * DoubleToBigDecimalConverter.java.java
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
public class DoubleToBigDecimalConverter implements Converter<Double, BigDecimal> {

	@Override
	public BigDecimal convert(Double source) {
		return new BigDecimal(source);
	}

}
