package PTQ.Test;

import org.testng.annotations.Test;

import PTQ.util.TestUtil;
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;

public class ConnectTestlink {
	TestLinkAPI api;

	@Test
	public void connecTest() {
		api =TestUtil.connect();
		//得到当前testlink下所有的project，返回一个project数组对象
		System.out.println(api.getProjects()[0].getName());
		//我当前系统就一个project，所以我根据索引0来确定第一个project来查找testplan
		System.out.println(api.getProjectTestPlans(api.getProjects()[0].getId())[0].getName());
	}

}
