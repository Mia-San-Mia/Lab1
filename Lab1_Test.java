import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static java.lang.System.exit;

class MyGraph {
    MyGraph(String path) {
        read(path);
        getDistance();
    }
    
    private void resetShortestArray() {

    	for (int i = 0; i < word.length; i++) {
			for (int j = 0; j < word.length; j++) {
                shortestPath[i][j] = 0;
            }
		}
    }

    private void translate(List<String> list) {
        List<String> unilist = new ArrayList<String>();
        for (String s : list) {
            if (!unilist.contains(s))
                unilist.add(s);
        }
        v = unilist.size();
        word = new String[v];
        unilist.toArray(word);
        arr = new int[v][v];
        int pre, now = 0;
        for (int i = 1; i < list.size(); i++) {
            pre = now;
            now = unilist.indexOf(list.get(i));
            arr[pre][now]++;
        }
    }

    private void read(String path) {
        List<String> list = new ArrayList<String>();
        String pre;
        String[] cur;
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            try {
                while (true) {
                    pre = in.readLine();
                    if (pre == null) {
                        translate(list);
                        return;
                    }
                    cur = pre.toLowerCase().split("[^a-z]+");
                    for (String s : cur)
                        list.add(s);
                }
            } catch (Exception e1) {
                System.out.println("Reading error!");
            }
        } catch (Exception e2) {
            System.out.println("File error!");
            exit(-1);
        }
    }

    public void exist(String s1) {
        f1 = -1;
        for (int i = 0; i < v; i++) {
            if (word[i].equals(s1))
                f1 = i;
        }
    }

    public void exist(String s1, String s2) {
        f1 = f2 = -1;
        for (int i = 0; i < v; i++) {
            if (word[i].equals(s1))
                f1 = i;
            if (word[i].equals(s2))
                f2 = i;
        }
    }

    public List<String> queryBridgeWords(String s1, String s2) {
        List<String> bridgeWord = new ArrayList<String>();
        exist(s1, s2);
        if (f1 != -1 && f2 != -1) {
            for (int i = 0; i < v; i++) {
                if (arr[f1][i] != 0) {
                    if (arr[i][f2] != 0) {
                        bridgeWord.add(word[i]);
                    }
                }
            }
        }
        return bridgeWord;
    }


    public String generateNewText(String old) {
        String[] oldWord = old.split("[^a-z]+");
        List<String> newWord = new ArrayList<String>();
        int l = oldWord.length;
        if (l < 2) {
            for (String s : oldWord)
                newWord.add(s);
        } else {
            Random r = new Random();
            for (int i = 0; i < l - 1; i++) {
                newWord.add(oldWord[i]);
                List<String> cur = queryBridgeWords(oldWord[i], oldWord[i + 1]);
                if (cur.size() != 0)
                    newWord.add(cur.get(r.nextInt(cur.size())));
            }
            newWord.add(oldWord[l - 1]);
        }
        String newText = "";
        for (String s : newWord)
            newText += s + " ";
        return newText;
    }

    private void getDistance() {
        dist = new int[v][v];
        path = new int[v][v];
        shortestPath = new int[v][v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                path[i][j] = -1;
                if (arr[i][j] == 0)
                    dist[i][j] = MAX;
                else dist[i][j] = arr[i][j];
            }
        }
        for (int k = 0; k < v; k++) {
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }
    }

    public String[] calcShortestPath(String word1, String word2) {
        exist(word1, word2);
        resetShortestArray();
        int k, count, result[];
        result = new int[v];
        k = f2;
        count = 0;
        while (path[f1][k] != -1) {
            count++;
            result[count] = path[f1][k];
            k = path[f1][k];
        }
        if (dist[f1][k] == MAX)
            return null;
        String[] pathword = new String[count];
        shortestPath[f1][result[count]] = 1;
        for (int i = count; i >= 1; i--){
            pathword[count - i] = word[result[i]];
            shortestPath[result[i]][result[i-1]] = 1;
        }
        shortestPath[result[1]][result[0]] = 0;
        shortestPath[result[1]][f2] = 1;
        return pathword;
    }

    public String randomWalk() {
        if (fr == -1) {
            fr = new Random().nextInt(v);
            return word[fr];
        }
        List<Integer> ranFrom = new ArrayList<Integer>();
        for (int i = 0; i < v; i++) {
            if (arr[fr][i] != 0)
                ranFrom.add(i);
        }
        if (ranFrom.size() == 0)
            return null;
        fr = ranFrom.get(new Random().nextInt(ranFrom.size()));
        return word[fr];
    }

    public final static int MAX = (int) Double.POSITIVE_INFINITY / 2 - 1;
    int v, f1 = -1, f2 = -1, fr = -1;
    public String[] word;
    public int[][] arr, dist, path, shortestPath;
}

////////////////////////////////////////////////////////////////////////////////////
class query implements Runnable {
    public void run() {
        Scanner in = new Scanner(System.in);
        while (Lab1_Test.exitFlag) {
            if (in.nextLine() != null) {
                break;
            }
        }
        if (Lab1_Test.exitFlag) {
            Lab1_Test.exitFlag = false;
        }
    }
}

////////////////////////////////////////////////////////////////////////////////////
public class Lab1_Test implements Runnable {
    public static boolean exitFlag = true;
    MyGraph graph;

    static void showDirectedGraph(MyGraph g, String fileName) {
    	new ShowGraph(g, fileName);
    	
        System.out.print("\t\t\t");
        for (int i = 0; i < g.v; i++) {
            System.out.print(g.word[i]);
            for (int j = 0; j < 12 - g.word[i].length(); j++) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (int i = 0; i < g.v; i++) {
            System.out.print(g.word[i]);
            for (int j = 0; j < 12 - g.word[i].length(); j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < g.v; j++) {
                System.out.print(g.arr[i][j] + "\t\t\t");
            }
            System.out.println();
        }
    }

    static void BridgeWords(MyGraph g, String s1, String s2) {
        List<String> bridgeWord = g.queryBridgeWords(s1, s2);
        if (g.f1 == -1 && g.f2 == -1) {
            System.out.println("No \"" + s1 + "\" and \"" + s2 + "\" in the graph!");
            return;
        } else if (g.f1 == -1) {
            System.out.println("No \"" + s1 + "\" in the graph!");
            return;
        } else if (g.f2 == -1) {
            System.out.println("No \"" + s2 + "\" in the graph!");
            return;
        } else {
            int count = bridgeWord.size();
            if (count == 0)
                System.out.println("No bridge words from \"" + s1 + "\" to \"" + s2 + "\"!");
            else if (count == 1)
                System.out.println("The bridge words from \"" + s1 + "\" to \"" + s2 + "\" is: " + bridgeWord.get(0));
            else if (count == 2)
                System.out.println("The bridge words from \"" + s1 + "\" to \"" + s2 + "\" are: "
                        + bridgeWord.get(0) + " and " + bridgeWord.get(1));
            else {
                System.out.print("The bridge words from \"" + s1 + "\" to \"" + s2 + "\" are: ");
                for (int i = 0; i < count - 1; i++)
                    System.out.print(bridgeWord.get(i) + ",");
                System.out.println(bridgeWord.get(count - 1));
            }
        }
    }

    private static void printShortestPath(String[] pathword, MyGraph g) {
        if (pathword == null) {
            System.out.println("There is no path from " + g.word[g.f1] + " to " + g.word[g.f2] + ".");
            return;
        }
        System.out.print("The path from " + g.word[g.f1] + " to " + g.word[g.f2] + " is:" + g.word[g.f1] + "->");
        for (int i = 0; i < pathword.length; i++)
            System.out.print(pathword[i] + "->");
        System.out.println(g.word[g.f2]);
        System.out.println("The length of path is:" + g.dist[g.f1][g.f2]);
    }

    static void shortestPath(MyGraph g, String s1) {
        g.exist(s1);
        if (g.f1 == -1) {
            System.out.println("The word doesn't exist.");
            return;
        }
        for (int i = 0; i < g.v; i++) {
            if (i != g.f1) {
                printShortestPath(g.calcShortestPath(s1, g.word[i]), g);
            }
        }
    }

    static void shortestPath(MyGraph g, String s1, String s2) {
        g.exist(s1, s2);
        if (g.f1 == -1 || g.f2 == -1) {
            System.out.println("The two words don't both exist.");
            return;
        }
        if (g.f1 == g.f2) {
            System.out.println("The two words are same.");
            return;
        }
        printShortestPath(g.calcShortestPath(s1, s2), g);
        /*for (int i = 0; i < g.v; i++) {
            System.out.print(g.word[i]);
            for (int j = 0; j < 8 - g.word[i].length(); j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < g.v; j++) {
                System.out.print(g.shortestPath[i][j] + "\t\t");
            }
            System.out.println();
        }*/


    }

    public void run() {
        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter("result.txt"));
            List<String> ranword = new ArrayList<String>();
            String cur = graph.randomWalk();
            ranword.add(cur);
            boolean flag = cur != null;
            while (flag && Lab1_Test.exitFlag) {
                System.out.print(cur + " ");
                wr.write(cur + " ");
                wr.flush();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cur = graph.randomWalk();
                if (cur != null) {
                    for (int i = 1; i < ranword.size(); i++) {
                        if (ranword.get(i).equals(cur) && ranword.get(i - 1).equals(ranword.get(ranword.size() - 1))) {
                            flag = false;
                            System.out.println(cur);
                            wr.write(cur);
                            wr.flush();
                        }
                    }
                    ranword.add(cur);
                }
                if (flag)
                    flag = cur != null;
            }
            System.out.println();
            Lab1_Test.exitFlag = false;
            graph.fr = -1;
            if (!flag)
                System.out.println("The random walk has ended.Press enter to exit.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the path and filename.");
        String filename = in.nextLine();
        MyGraph g = new MyGraph(filename);
        System.out.println("The graph has been generated.");

        user_choose:
        while (true) {
            System.out.println("1.Show the graph.\n2.Search two words' bridgeword.\n" +
                    "3.Generate new text according to the old bridgeword.\n" +
                    "4.Show the path between words.\n5.Random ergodic and output to the file.\n0.exit.");

            String choice = in.nextLine();
            switch (choice) {
                case "1":
                    showDirectedGraph(g, filename);
                    //new ShowGraph(g, filename);
                    //new something
                    break;
                case "2":
                    System.out.println("Please enter two words separated by space.");
                    String[] brin = in.nextLine().split(" ");
                    if (brin.length == 2)
                        BridgeWords(g, brin[0], brin[1]);
                    else
                        System.out.println("Illegal input.");
                    break;
                case "3":
                    System.out.println("Please enter the new text.");
                    String oldtext = in.nextLine();
                    System.out.println(g.generateNewText(oldtext));
                    break;
                case "4":
                    System.out.println("Please enter two words separated by space or enter one word.");
                    String[] fromto = in.nextLine().split(" ");
                    if (fromto.length == 1)
                        shortestPath(g, fromto[0]);
                    else if (fromto.length == 2)
                        shortestPath(g, fromto[0], fromto[1]);
                    else
                        System.out.println("Illegal input.");
                    break;
                case "5":
                    System.out.println("Random walk begins.Press enter to exit.");
                    Lab1_Test.exitFlag = true;
                    Lab1_Test input = new Lab1_Test();
                    input.graph = g;
                    Thread t1 = new Thread(input);
                    Thread t2 = new Thread(new query());

                    t1.start();
                    t2.start();
                    try {
                        t1.join();
                        t2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case "0":
                    break user_choose;
                default:
                    System.out.println("Illegal input.");
            }
        }
        System.out.println("The program is closed.Thank you for using.");
    }
}
