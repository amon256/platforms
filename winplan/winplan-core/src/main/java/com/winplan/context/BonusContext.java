package com.winplan.context;


public class BonusContext {
	
	/**
	 * 推荐奖
	 * @return
	 */
	public static int getRecommendBonus(){
		return 120;
	}
	
	/**
	 * 注册奖，分给登录注册人员
	 * @return
	 */
	public static float getRegisterBonus(){
		return 18f;
	}
	
	/**
	 * 层奖
	 * @param level
	 * @param newLevel
	 * @return
	 */
	public static float getCenBonus(int level,int newLevel){
		return 120f;
	}
	
	public static float getTaxRate(){
		return 0.9f;
	}
}
