package com.dszuqiu.common.entity;

import com.dszuqiu.common.GlobalParam;

public enum Rule {
	//HalfReverseInterceptor�볡��תϵ�й���
    RULE_1("�����򣬿Ͷ��Ƚ���0:1����ʱ��С�򽵵�3��", 1, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_2("�����������Ƚ���1:0����ʱ��С�򽵵�3��", 2, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_3("�������н�������û�����", 3, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    RULE_4("�������н�������û����� ", 4, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_5("���н���,�������̡�", 5, GlobalParam.RULE_TYPE_BIG_BALL) , 
    
    //PanziRollingBallInterceptor ���ӹ�����ϵ�й���
    RULE_6("65���ӣ��̿ڴ��ڵ���1��ˮλ������1�����滹����", 6, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_7("75���ӣ��̿ڵ���0.75�����̿ڵ���0.5��С��ˮλ����1�����滹����", 7, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_8("�ϰ��н����г���ͦ���°����� ", 8, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_9("�����ð����г����ð����°�����", 9, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_10("������������ˮ����", 10, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    RULE_11("���̿�������ˮ����", 11, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    
    //334,757
    RULE_12("334����", 12, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_13("757����", 13, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    
    //TwentyMinRuleInterceptor�����ʮ���ӷ���
    RULE_14("ǰ10����1���򣬳���[3.5,4]��", 14, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    RULE_15("ǰ10����1���򣬳���[2.75,3]��", 15, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    RULE_16("ǰ15����1���򣬳���<=3�� ", 16, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_17("ǰ20����2���򣬳���<=4�� ", 17, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_18("ǰ20����û���򣬳���[2.25,2.5]����ʱ�̽���1.75��", 18, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_19("ǰ30����1���򣬳���[3.25,3.5]����ʱ�̽���3��", 19, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    RULE_20("ǰ30����1���򣬳���>=4����ʱ�̽���4�� ", 20, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_21("ǰ35����1���򣬳���>=3����ʱ�̽���3�� ", 21, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_22("ǰ40����2���򣬳���>=4����ʱ�̽���4�� ", 22, GlobalParam.RULE_TYPE_BIG_BALL) , 
    
    //    UniqueInterceptor
    RULE_23("�����򣬿Ͷ��Ƚ���0:1����ʱ��С�򽵵�2.5��", 23, GlobalParam.RULE_TYPE_BIG_BALL) ,  
    RULE_24("���ð���70�����޽��򣬼�ʱ��С�򽵵�0.75�� ", 24, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_25("�������н�������û����� ", 25, GlobalParam.RULE_TYPE_BIG_BALL) , 
    RULE_26("�ļ٣�ͷ10�����޽���׷�� ", 26, GlobalParam.RULE_TYPE_BIG_BALL) ;
    
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
