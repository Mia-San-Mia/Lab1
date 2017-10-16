import java.io.*;
/*change two*/
/*edit on branch B1*/
/*new change*/
public class Graph {
	/*edit on branch B2*/
	public static void createDirectedGraph(String[] word, int[][] arr, String fileName) {
		File dotFile = new File(fileName.replace("txt", "dot"));
		try {
			dotFile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(dotFile));
			out.write(String.format("digraph %s {\n", "test"));
			for(int i = 0;i < arr.length; i++) {
				for(int j = 0;j < arr.length; j++) {
					if(arr[i][j] != 0) {
						out.write(String.format("\t%s -> %s [label=\"%d\"];\n", word[i], word[j], arr[i][j]));
					}
				}
			}
			out.write("}");
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("IO Exception!");
		}
		
		//生成并展示图片
		Runtime run = Runtime.getRuntime();
		try {
			Process process = run.exec(String.format("dot -Tpng %s -o %s", fileName.replace("txt", "dot"), fileName.replace("txt", "png")));
			process.waitFor();
		} catch (Exception e) {
			System.out.println("Wrong when generating graph!");
		}
		
		try {
			PutGraphPanel.setgraph(fileName.replace("txt", "png"));
		} catch (Exception e) {
			System.out.println("Wrong when generating graph!");
		}
	}
	public static void updateDirectedGraph(String[] word, int[][] arr, String fileName, int[][] shortArr) {
		File dotFile = new File(fileName.replace("txt", "dot"));
		try {
			dotFile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(dotFile));
			out.write(String.format("digraph %s {\n", "test"));
			/*enum clr{
				red, blue, gold, yellow, cyan, orange
			}*/
			for(int i = 0;i < arr.length; i++) {
				for(int j = 0;j < arr.length; j++) {
					if(arr[i][j] != 0) {
						if (shortArr[i][j] != 0) {
							out.write(String.format("\t%s -> %s [label=\"%d\", color=\"red\"];\n", word[i], word[j], arr[i][j]));
						} else {
							out.write(String.format("\t%s -> %s [label=\"%d\"];\n", word[i], word[j], arr[i][j]));
						}
						//out.write(String.format("\t%s -> %s [label=\"%d\"];\n", word[i], word[j], arr[i][j]));
					}
				}
			}
			out.write("}");
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("IO Exception!");
		}
		
		//生成并展示图片
		Runtime run = Runtime.getRuntime();
		try {
			Process process = run.exec(String.format("dot -Tpng %s -o %s", fileName.replace("txt", "dot"), fileName.replace("txt", "png")));
			process.waitFor();
		} catch (Exception e) {
			System.out.println("Wrong when generating graph!");
		}
		
		try {
			PutGraphPanel.setgraph(fileName.replace("txt", "png"));
		} catch (Exception e) {
			System.out.println("Wrong when generating graph!");
		}
	}
}