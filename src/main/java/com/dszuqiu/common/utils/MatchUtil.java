package com.dszuqiu.common.utils; 

import java.util.regex.Pattern;

import com.dszuqiu.common.entity.Match;

public class MatchUtil 
{ 
    public static float getCurrentBigSmall(Match newMatch) 
    { 
        String currentBigSmall = newMatch.getCurrentBigSmall(); 
        int index = currentBigSmall.indexOf(" "); 
        String bigSmall = currentBigSmall.substring(0, index); 
        return Float.parseFloat(bigSmall); 
    } 
    
    public static float getCurrentConcede(Match newMatch) 
    { 
        String currentConcede = newMatch.getCurrentConcede(); 
        int index = currentConcede.indexOf(" "); 
        String concede = currentConcede.substring(0, index); 
        return Float.parseFloat(concede); 
    } 

    public static int getKeScore(Match newMatch) 
    { 
        String score = newMatch.getCurrentScore(); 
        int index = score.indexOf(":"); 
        String newScore = score.substring(index+2, index + 3); 
        //3 : 2  
        int keScore = Integer.parseInt(newScore); 
        return keScore; 
    } 

    public static int getZhuScore(Match newMatch) 
    { 
        String score = newMatch.getCurrentScore(); 
        String newScore = score.substring(0, 1); 
        int zhuScore = Integer.parseInt(newScore); 
        return zhuScore; 
    } 

    public static int getShiJian(Match newMatch) 
    { 
        String time = newMatch.getTime(); 
        int index1 = time.indexOf("\'");
        if(index1 == -1){
        	if(time.indexOf("��") != -1){//�볡
        		return Integer.MIN_VALUE;
        	}else{//δ��ʼ
        		return Integer.MAX_VALUE;
        	}
        }
        int shiJian = Integer.parseInt(time.substring(0, index1));
        return shiJian;
    } 

    public static float getChuPan(Match newMatch){ 
        String hostAndConcede = newMatch.getHostAndConcede(); 
        int index1 = hostAndConcede.indexOf("(") + 1; 
        int index2 = hostAndConcede.indexOf(")"); 
        float chuPan = Float.parseFloat(hostAndConcede.substring(index1, index2)); 
        return chuPan; 
    } 
    
    public static float getChuPanBigSmall(Match newMatch){ 
        String hostAndConcede = newMatch.getBigSmallAndGuest(); 
        int index1 = hostAndConcede.indexOf("(") + 1; 
        int index2 = hostAndConcede.indexOf(")"); 
        float chuPan = Float.parseFloat(hostAndConcede.substring(index1, index2)); 
        return chuPan; 
    } 
    
    public static boolean isBigBallLeague(Match newMatch){ 
        String type = newMatch.getType(); 
        return isBigBallLeague(type); 
    } 
    
    /**
     * �����
     * @param newMatch
     * @return
     */
    public static boolean isUpwindBall(Match newMatch){ 
    	double chuPan = MatchUtil.getChuPan(newMatch); 
        int zhuScore = MatchUtil.getZhuScore(newMatch); 
        int keScore = MatchUtil.getKeScore(newMatch); 
        if((chuPan < 0 && zhuScore-keScore < 0) || (chuPan > 0 && zhuScore-keScore > 0)){
        	return true;
        }
        return false; 
    } 
    
    /**
     * ��ǰ��1���������̣���С��������3.25������
     * @param newMatch
     * @return
     */
    public static boolean isDeepDisk(Match newMatch){ 
    	double chuPan = MatchUtil.getChuPan(newMatch); 
    	double chuPanBigSmall = MatchUtil.getChuPanBigSmall(newMatch); 
        if(Math.abs(chuPan)>1 && chuPanBigSmall>3.25){
        	return true;
        }
        return false; 
    } 
    
    /**
     * ��С���̿�
     * @param newMatch
     * @return
     */
    public static float getBigSmallPankou(Match match){ 
    	int zhuScore = MatchUtil.getZhuScore(match); 
        int keScore = MatchUtil.getKeScore(match); 
        float bigSmall = MatchUtil.getCurrentBigSmall(match); 
        int totalScore = zhuScore + keScore; 
        
        float panKou = bigSmall - totalScore;
        return panKou;
    } 
    
    /**
     * ��ǰ�ܽ�����
     * @param str
     * @return
     */
    public static int getTotalScore(Match match){
    	int zhuScore = MatchUtil.getZhuScore(match); 
        int keScore = MatchUtil.getKeScore(match); 
        int totalScore = zhuScore + keScore; 
        return totalScore;
    }
    
    /**
     * ���ص�ǰ�ȷ֣����� 3 : 2
     * @param str
     * @return
     */
    public static String getCurrentScore(Match match){
    	int zhuScore = MatchUtil.getZhuScore(match); 
        int keScore = MatchUtil.getKeScore(match); 
        return zhuScore + " : " + keScore;
    }
    
    public static boolean isNumeric(String str){  
        Pattern pattern = Pattern.compile("[0-9]*");  
        return pattern.matcher(str).matches();     
    }  
    
    public static boolean isBigBallLeague(String type){ 
        if(type.indexOf("��") != -1 ||
        		type.indexOf("����") != -1 ||
        		type.indexOf("��") != -1 ||
        		type.indexOf("Ų") != -1 ||
        		type.indexOf("��") != -1 ||
        		type.indexOf("��") != -1 ||
        		type.indexOf("��") != -1 ||
        		type.indexOf("��") != -1 ||
        		type.indexOf("��") != -1 ||
        		type.indexOf("ӡ��") != -1 ||
        		type.indexOf("Խ") != -1 ||
        		type.indexOf("̩") != -1 
        		){
        	return true;
        }
        return false; 
    } 
     
    public static void main(String[] args) 
    { 
        Match newMatch = new Match(); 
        newMatch.setHostAndConcede("1 1[5] ��Ħ���� (-0.25)"); 
        newMatch.setTime("45'"); 
        newMatch.setBigSmallAndGuest("(2.5) AEL�������� [7]"); 
        newMatch.setCurrentScore("3 : 2 �볡�ȷ� 1 : 1 "); 
        newMatch.setCurrentBigSmall("5.5 2.250 �� 5.5 1.625 79'"); 
         
        System.out.println(getChuPan(newMatch)); 
        System.out.println(getShiJian(newMatch)); 
         
        System.out.println(getZhuScore(newMatch)); 
        System.out.println(getKeScore(newMatch)); 
         
        System.out.println(getCurrentBigSmall(newMatch)); 
        System.out.println(getChuPanBigSmall(newMatch)); 
        System.out.println(MatchUtil.isNumeric("2")); 
    } 
} 