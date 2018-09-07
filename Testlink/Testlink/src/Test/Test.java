package Test;

import java.net.MalformedURLException;

import PTQ.util.TestUtil;
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;

public class Test {
	static TestLinkAPI api;
	
	public static void main(String[] args) throws MalformedURLException {
		api =TestUtil.connect();
		//得到当前testlink系统的所有project，返回类型是数组，所有需要用TestProject[]存储
		TestProject[] projects = api.getProjects();
				
		//遍历下数组
		for (int i = 0; i < projects.length; i++) {
			System.out.println(projects[i].getName()+ ":" + projects[i].getId());
		}

	}
}
