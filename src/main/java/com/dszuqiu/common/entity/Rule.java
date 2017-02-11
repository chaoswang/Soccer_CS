package com.dszuqiu.common.entity;

import com.dszuqiu.common.GlobalParam;

public enum Rule {
	//HalfReverseInterceptor半场逆转系列规则
    RULE_1("主让球，客队先进球0:1，即时大小球降到3。", 1, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_2("客让球，主队先进球1:0，即时大小球降到3。", 2, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_3("主让球，有进球但让球没打出。", 3, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    RULE_4("客让球，有进球但让球没打出。 ", 4, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_5("已有进球,降到初盘。", 5, GlobalParam.RULE_TYPE_BIG_BALL) , 
    
    //PanziRollingBallInterceptor 盘子滚球法则系列规则
    RULE_6("65分钟，盘口大于等于1，水位不超过1，后面还有球。", 6, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_7("75分钟，盘口等于0.75，或盘口等于0.5且小球水位大于1，后面还有球。", 7, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_8("上半有进球，中场坚挺，下半有球。 ", 8, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_9("初盘让半球，中场仍让半球，下半有球。", 9, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_10("初盘主让球不走水法则。", 10, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    RULE_11("初盘客让球不走水法则。", 11, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    
    //334,757
    RULE_12("334法则。", 12, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_13("757法则。", 13, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    
    //TwentyMinRuleInterceptor滚球二十分钟法则
    RULE_14("前10分钟1进球，初盘[3.5,4]。", 14, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    RULE_15("前10分钟1进球，初盘[2.75,3]。", 15, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    RULE_16("前15分钟1进球，初盘<=3。 ", 16, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_17("前20分钟2进球，初盘<=4。 ", 17, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_18("前20分钟没进球，初盘[2.25,2.5]，即时盘降到1.75。", 18, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_19("前30分钟1进球，初盘[3.25,3.5]，即时盘降到3。", 19, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    RULE_20("前30分钟1进球，初盘>=4，即时盘降到4。 ", 20, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_21("前35分钟1进球，初盘>=3，即时盘降到3。 ", 21, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_22("前40分钟2进球，初盘>=4，即时盘降到4。 ", 22, GlobalParam.RULE_TYPE_BIG_BALL) , 
    
    //    UniqueInterceptor
    RULE_23("主让球，客队先进球0:1，即时大小球降到2.5。", 23, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    RULE_24("主让半球，70分钟无进球，即时大小球降到0.75。 ", 24, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_25("主让球，有进球但让球没打出。 ", 25, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_26("澳假，头10分钟无进球，追大。 ", 26, GlobalParam.RULE_TYPE_BIG_BALL) ;
    
    private String name;
    private int index;
    private int strategyType;
   
   
    
    private Rule(String name, int index, int strategyType) {
        this.name = name;
        this.index = index;
        this.strategyType = strategyType;
    }

    public static String getName(int index) {
        for (Rule c : Rule.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
    
    public int getStrategyType() {
        return strategyType;
    }
    
//    public Rule[] getAllRules(){
//    	
//    }
}
