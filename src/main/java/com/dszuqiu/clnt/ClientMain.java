package com.dszuqiu.clnt;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.apache.log4j.Logger;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.dszuqiu.clnt.view.SoccerUI;
import com.dszuqiu.common.utils.DebugLogger;

public class ClientMain {
	private static final Logger logger = DebugLogger.getLogger(ClientMain.class);
	
	private static void initGlobalFont(Font font) {   
        FontUIResource fontRes = new FontUIResource(font);   
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {   
            Object key = keys.nextElement();   
            Object value = UIManager.get(key);   
            if (value instanceof FontUIResource) {   
                UIManager.put(key, fontRes);   
            }   
        }   
    }  
	
	public static void main(String args[]) {
		logger.info("###赌神一路发 客户端###");
		initGlobalFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));   
        try {   
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();   
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;   
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();   
            UIManager.put("RootPane.setupButtonVisible", false);   
            BeautyEyeLNFHelper.translucencyAtFrameInactive = false;   
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
        } catch (Exception e) {   
        	logger.info("set skin fail!");   
        }   
		SoccerUI ui = new SoccerUI();
	}
}
