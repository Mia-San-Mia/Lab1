import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
/*change three*/
public class ShowGraph extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PutGraphPanel putGraph;
	
	public ShowGraph(MyGraph mygraph, String fileName) {		
		final int WIDTH = 900;
		final int HEIGHT = 600;
		setBackground(Color.WHITE);
		setTitle("Show Graph");
		setSize(WIDTH, HEIGHT);
		// 鑿滃崟
		JMenuBar meunBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem showGraph = new JMenuItem("Show Graph");
		JMenuItem updateGraph = new JMenuItem("Uptate Graph");
		menu.add(showGraph);
		menu.add(updateGraph);
		showGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Graph.createDirectedGraph(mygraph.word, mygraph.arr, fileName);
				}
		});
		updateGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Graph.updateDirectedGraph(mygraph.word, mygraph.arr, fileName.replace(".txt", "shortest") + ".txt", mygraph.shortestPath);
				}
		});
		meunBar.add(menu);
		// 娣诲姞鑿滃崟鍒颁富绋嬪簭
		setJMenuBar(meunBar);
		
		PutGraphPanel putGraph = new PutGraphPanel();
		JScrollPane sp = new JScrollPane(putGraph);
		sp.validate();
		add(sp);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}


class PutGraphPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JLabel graphLabel;
	public static ImageIcon graph;
	public PutGraphPanel(){
		graphLabel = new JLabel();
		setBackground(Color.WHITE);
		add(graphLabel);
	}
	
	public static void setgraph(String pngPath) {
		try {
			graph = new ImageIcon(ImageIO.read(new File(pngPath)));
			graphLabel.setIcon(graph);
			graphLabel.repaint();
		} catch (IOException e) {
			// TODO 鑷姩鐢熸垚鐨� catch 鍧�
			System.out.println("Show Graph Error!");
		}
	}
}