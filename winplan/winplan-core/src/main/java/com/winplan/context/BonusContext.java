package com.winplan.context;


public class BonusContext {
	
	/**
	 * 推荐奖
	 * @return
	 */
	public static int getRecommendBonus(){
		return 50;
	}
	
	/**
	 * 层奖
	 * @param level
	 * @param newLevel
	 * @return
	 */
	public static float getCenBonus(int level,int newLevel){
		return ((newLevel - level > 1) ? 500 : 300);
	}
	
	public static float getTaxRate(){
		return 0.9f;
	}
}
