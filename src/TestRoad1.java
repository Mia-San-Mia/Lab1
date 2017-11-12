import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author wz124
 *
 */
public class TestRoad1 {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQueryBridgeWordsRoad1() {
		MyGraph mg = new MyGraph("Lab1.txt");
		String s1 = "yuyuyuyu";
		String s2 = "wswswswsws";
		List<String> expectedAnswer = new ArrayList<String>();
		assertEquals(expectedAnswer, mg.queryBridgeWords(s1, s2));
	}
	

}
