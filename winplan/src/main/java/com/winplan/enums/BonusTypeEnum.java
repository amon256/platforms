/**
 * BonusTypeEnum.java.java
 * @author FengMy
 * @since 2015年7月10日
 */
package com.winplan.enums;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月10日
 */
public enum BonusTypeEnum {
	BONUS_KOU,//奖金扣除
	BONUS_TJ,//推荐奖
	BONUS_CEN,//层奖
	TRANSFER_IN,//转入
	TRANSFER_OUT;//转出
	
	public static BonusTypeEnum[] getAddBonusTypeEnums(){
		return new BonusTypeEnum[]{BONUS_TJ,BONUS_CEN,TRANSFER_IN};
	}
}
