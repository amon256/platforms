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
	public static double getCenBonus(int level,int newLevel){
		return 0.9 * ((newLevel - level > 4) ? 500 : 250);
	}
}
