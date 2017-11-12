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
public class TestRoad6 {

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
	public void test() {
		MyGraph mg = new MyGraph("Lab1.txt");
		String s1 = "the";
		String s2 = "world";
		List<String> expectedAnswer = new ArrayList<String>();
		expectedAnswer.add("it");
		assertEquals(expectedAnswer, mg.queryBridgeWords(s1, s2));
	}

}
