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
        	if(time.indexOf("半") != -1){//半场
        		return Integer.MIN_VALUE;
        	}else{//未开始
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
     * 逆风球
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
     * 赛前让1球以上深盘，大小球设置在3.25球以上
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
     * 大小球盘口
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
     * 当前总进球数
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
     * 返回当前比分，比如 3 : 2
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
        if(type.indexOf("澳") != -1 ||
        		type.indexOf("新西") != -1 ||
        		type.indexOf("荷") != -1 ||
        		type.indexOf("挪") != -1 ||
        		type.indexOf("德") != -1 ||
        		type.indexOf("丹") != -1 ||
        		type.indexOf("芬") != -1 ||
        		type.indexOf("匈") != -1 ||
        		type.indexOf("爱") != -1 ||
        		type.indexOf("印尼") != -1 ||
        		type.indexOf("越") != -1 ||
        		type.indexOf("泰") != -1 
        		){
        	return true;
        }
        return false; 
    } 
     
    public static void main(String[] args) 
    { 
        Match newMatch = new Match(); 
        newMatch.setHostAndConcede("1 1[5] 奥摩尼亚 (-0.25)"); 
        newMatch.setTime("45'"); 
        newMatch.setBigSmallAndGuest("(2.5) AEL利马索尔 [7]"); 
        newMatch.setCurrentScore("3 : 2 半场比分 1 : 1 "); 
        newMatch.setCurrentBigSmall("5.5 2.250 ↓ 5.5 1.625 79'"); 
         
        System.out.println(getChuPan(newMatch)); 
        System.out.println(getShiJian(newMatch)); 
         
        System.out.println(getZhuScore(newMatch)); 
        System.out.println(getKeScore(newMatch)); 
         
        System.out.println(getCurrentBigSmall(newMatch)); 
        System.out.println(getChuPanBigSmall(newMatch)); 
        System.out.println(MatchUtil.isNumeric("2")); 
    } 
} 