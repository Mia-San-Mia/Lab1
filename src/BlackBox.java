import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;

import org.junit.Test;

public class BlackBox {
	@Test
	public void test() {
		MyGraph mg = new MyGraph("Lab1.txt");
		String s1 = "aa";
		String s2 = "bb";
		String[] a = null;
		assertArrayEquals(a, mg.calcShortestPath(s1, s2));
	}
	
	@Test
	public void test1() {
		MyGraph mg = new MyGraph("Lab1.txt");
		String s1 = "mine";
		String s2 = "apple";
		String[] a = null;
		assertArrayEquals(a, mg.calcShortestPath(s1, s2));
	}
	
	@Test
	public void test2() {
		MyGraph mg = new MyGraph("Lab1.txt");
		String s1 = "i";
		String s2 = "apple";
		String[] a = {"have","an"};
		for(String i:mg.calcShortestPath(s1, s2))
			System.out.println(i);
		assertArrayEquals(a, mg.calcShortestPath(s1, s2));
	}

}
