package com.jeecms.common.util;

import java.util.ArrayList;
import java.util.List;

public class PicUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "<p style=\"margin-top: 27px; margin-bottom: 10px; line-height: 1.8em; text-indent: 32px; color: rgb(51, 51, 51); font-family: &quot;Microsoft YaHei&quot;, 微软雅黑; white-space: normal; background-color: rgb(255, 255, 255);\"><img src=\"/cmsV8/u/cms/www/201706/20153246rdx6.jpg\" alt=\"15\"/><span style=\"color: rgb(0, 51, 102);\"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 6月19日下午，“中国凉都2017年水城彝族火把节”新闻发布会在北京人民大会堂新闻发布厅召开。千龙网记者 纪敬摄</span></p><p style=\"margin-top: 27px; margin-bottom: 10px; line-height: 1.8em; text-indent: 32px; color: rgb(51, 51, 51); font-family: &quot;Microsoft YaHei&quot;, 微软雅黑; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"border-bottom: 0px dotted rgb(0, 0, 153);\"><a href=\"http://culture.qianlong.com/2017/0620/1784273.shtml#&quot;\" target=\"_blank\" style=\"color: rgb(0, 0, 153); text-decoration-line: none; border-bottom: 1px dotted rgb(7, 129, 199);\">水城</a></span>坐落在中国凉都六盘水的腹地，这里是一年一度火把节举行的福地。彝族在这盛大的节日里，仍保留着火石点火的原始方式，凝聚着古老民族深厚的历史积淀。</p><p style=\"margin-top: 27px; margin-bottom: 10px; line-height: 1.8em; text-indent: 32px; color: rgb(51, 51, 51); font-family: &quot;Microsoft YaHei&quot;, 微软雅黑; white-space: normal; background-color: rgb(255, 255, 255);\">8月12日至16日，<span style=\"border-bottom: 0px dotted rgb(0, 0, 153);\"><a href=\"http://culture.qianlong.com/2017/0620/1784273.shtml#&quot;\" target=\"_blank\" style=\"color: rgb(0, 0, 153); text-decoration-line: none; border-bottom: 1px dotted rgb(7, 129, 199);\">火把节</a></span>期间将有“中国乌蒙历史博物馆开馆仪式”、“世界彝人海坪峰会”、“彝族始祖祭祖大典”、“火把节狂欢夜”。彝族始祖希慕遮祭祀大典将带领人们领略庄严厚重的彝族历史。</p><p style=\"margin-top: 27px; margin-bottom: 10px; line-height: 1.8em; text-indent: 32px; color: rgb(51, 51, 51); font-family: &quot;Microsoft YaHei&quot;, 微软雅黑; white-space: normal; background-color: rgb(255, 255, 255);\">今年的火把节将推出“一剧一片”的创意文化项目。“一剧”是首部以彝族神话英雄为主题的大型歌舞剧《支格阿鲁》，将浓墨重彩地展示彝族文化，为观众带来“意料之外”的艺术体验。</p><p style=\"margin-top: 27px; margin-bottom: 10px; line-height: 1.8em; text-indent: 32px; color: rgb(51, 51, 51); font-family: &quot;Microsoft YaHei&quot;, 微软雅黑; white-space: normal; background-color: rgb(255, 255, 255);\">“一片”即电影《天空之恋》，将以彝族文化为背景，讲述一段美丽动人的爱情故事，展现彝乡之恋的纯净，邀请当红人气演员加盟。</p><p style=\"margin-top: 27px; margin-bottom: 10px; line-height: 1.8em; text-indent: 32px; color: rgb(51, 51, 51); font-family: &quot;Microsoft YaHei&quot;, 微软雅黑; white-space: normal; background-color: rgb(255, 255, 255);\">火把节中的“千人彝宴”绝对是令吃货欲罢不能的一项活动。在这场巨型彝族美食文化盛宴上，吃货们可以大饱口福，一品正宗的彝族美食。在发布会上同时启动了“千人彝宴”网友线上征名活动，征名结束后，将选出幸运网友免费亲临现场，亲品彝家风味，共享火把节狂欢夜。</p><p><br/></p>";
		List aa = PicUtil.getPicUrlFromTxt(a);
		System.out.println(aa.get(0));
	}
	
	public static List<String> getPicUrlFromTxt(String str){
		List<String> list = new ArrayList<>();
		for (;;) {
			int s1 = str.indexOf("src=\"");
			int s2 = str.indexOf("g\"", s1);
			if (s1 > 0 && s2 > 0) {
				String path = str.substring(s1 + 5, s2 + 1);
				list.add(path);
				str = str.substring(s2 + 1, str.length());
			}else{
				break;
			}
		}
		return list;
	}

}
