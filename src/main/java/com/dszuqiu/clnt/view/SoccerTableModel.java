package com.dszuqiu.clnt.view;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.ServerMatchBean;
import com.dszuqiu.common.utils.DebugLogger;

public class SoccerTableModel extends AbstractTableModel {
 
	private static final long serialVersionUID = -8530251035284065642L;
	private static final Logger logger = DebugLogger.getLogger(SoccerTableModel.class);
	//match内存池
	private Map<String, Match> matches = new HashMap<String, Match>();

	private Object[][] content = new Object[0][0];
	
    private String[] HEADER = {"ID", "赛事", "时间", "主队（初：让球）", "比分" ,
    		"（初：大小球）客队","比赛动态","主队","让球","客队",
    		"角球","高于","大小球","低于","投资建议","模拟下注"};
 
    public void updateMatches(List<ServerMatchBean> match_invests) {
    	for(ServerMatchBean match_invest : match_invests){
    		Match match = (Match)match_invest.getMatch();
    		logger.info("updateMatches:"+ match);
    		matches.put(match.getTrId(), match);
    	}
    	content = getData(match_invests);
    }
    
    private Object[][] getData(List<ServerMatchBean> match_invests){
    	Object[][] row = new Object[match_invests.size()][16];
		for(int i=0;i<match_invests.size();i++){
			Match match = (Match)match_invests.get(i).getMatch();
			row[i][0] =  match.getTrId();
			row[i][1] =  match.getType();
			row[i][2] =  match.getTime();
			row[i][3] =  match.getHostAndConcede();
			row[i][4] =  match.getCurrentScore();
			row[i][5] =  match.getBigSmallAndGuest();
			row[i][6] =  match.getDynamicInfo();
			row[i][7] =  String.valueOf(match.getHostOdds());
			row[i][8] =  match.getCurrentConcede();
			row[i][9] =  String.valueOf(match.getGuestOdds());
			row[i][10] =  match.getCorner();
			row[i][11] =  String.valueOf(match.getBigOdds());
			row[i][12] =  match.getCurrentBigSmall();
			row[i][13] =  String.valueOf(match.getSmallOdds());
			if(match_invests.get(i).getSuggestion()!=null){
				row[i][14] =  String.valueOf(match_invests.get(i).getSuggestion().getRuleType());;
			}
//			row[i][15] =  new BetPanel();
		}
		return row;
	}
 
    public String getColumnName(int col) {
        return HEADER[col];
    }
 
    public int getColumnCount() {
        return HEADER.length;
    }
 
    public int getRowCount() {
        return content.length;
    }

	@Override
	public Object getValueAt(int row, int col) {
        return content[row][col];
    }
	
	@Override
	public boolean isCellEditable(int row, int column) {
		// 带有按钮列的功能这里必须要返回true不然按钮点击时不会触发编辑效果，也就不会触发事件。
		if (column == 7 ||column == 9 ||column == 11 ||column == 13 ||column == 15) {
			return true;
		} else {
			return false;
		}
	}
}
