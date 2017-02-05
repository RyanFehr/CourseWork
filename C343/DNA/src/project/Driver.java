package project;
import java.io.*;
import java.util.Random;

enum Mutation { I, M, D };

class Result {
    Result(int s, Mutation d) { score = s; dir = d; }
    int score;
    Mutation dir;
}

/**
 * Created by mvc on 11/14/2015.
 */
public class Driver {
    public DNAFrame frame;
    public int size;
    public boolean enableGUI;
    public String str1;
    public String str2;
    public String aligned_s1;
    public String aligned_s2;
    public int bestScore;

    public static final String HINT = "\nUsage:\n\"gui\" to run the GUI mode.\n\"test\" to run the test mode.";

    public static void main(final String... args) {
        if(args.length > 0) {
            switch (args[0]) {
                case "gui":
                    javax.swing.SwingUtilities.invokeLater(() -> new Driver(true).resize(10));
                    break;
                case "gen_test":
                    Driver.gentest();
                    break;
                case "test":
                    Driver.test();
                    break;
                default:
                    System.out.println(Driver.HINT);
            }
        }else {
            javax.swing.SwingUtilities.invokeLater(() -> new Driver(true).resize(10));
        }
    }

    public Driver(final boolean _enableGUI) {
        this.enableGUI = _enableGUI;
    }

    private void feed(final String _str1, final String _str2) {
        this.size = _str1.length();
        this.str1 = _str1;
        this.str2 = _str2;
        if(this.enableGUI) {
            this.frame = new DNAFrame(this, this.str1, this.str2);
        }

        perform_alignment(this, this.str1, this.str2);
    }

    static void perform_alignment(final Driver driver, final String s1, final String s2) {
        //this.driver = _driver;
        //assume s1 and s2 are of the same length
        int tableSize = s1.length() + 1;
	//this.path = new boolean[tableSize][tableSize];
        System.out.println("feed in");
        System.out.println(s1);
        System.out.println(s2);
	
	
        StringBuilder s1_aligned = new StringBuilder();
        StringBuilder s2_aligned = new StringBuilder();
        Result[][] scores = AlignmentFunction.align(s1, s2, s1_aligned, s2_aligned);
        Driver.display(scores, driver);

        final String s1_new = s1_aligned.reverse().toString();
        final String s2_new = s2_aligned.reverse().toString();
        driver.aligned_s1 = s1_new;
        driver.aligned_s2 = s2_new;
        driver.bestScore = scores[tableSize-1][tableSize-1].score;
        System.out.println("Aligned strings:");
        System.out.println(s1_new);
        System.out.println(s2_new);
    }
    

    public void resize(final int _size) {
        this.size = _size;
        final String _str1 = Driver.rand_string(this.size);
        final String _str2 = Driver.rand_string(this.size);
        this.feed(_str1, _str2);
    }

    public static void gentest() {
        final Driver driver = new Driver(false);
        try {
            final File file = new File("tests.txt");
            final FileWriter writer = new FileWriter(file, false);
            for (int index = 0; index < Constants.TEST_SAMPLES; index += 1) {
                driver.resize(Constants.TEST_STR_SIZE);
                writer.write(driver.str1);
                writer.write(Constants.Splitter1);
                writer.write(driver.str2);
                writer.write(Constants.Splitter2);
                writer.write(Integer.toString(driver.bestScore));
                writer.write(Constants.Splitter3);
            }
            writer.close();
        }catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void test() {
        try {
            final FileReader reader = new FileReader("tests.txt");
            final BufferedReader bufferedReader = new BufferedReader(reader);
            final String in = bufferedReader.readLine();
            bufferedReader.close();
            final String[] samples = in.split(Constants.Splitter3);
            final Driver driver = new Driver(false);
            for(int index=0; index<samples.length; index+=1) {
                final String[] testNresult = samples[index].split(Constants.Splitter2);
                final String[] tests = testNresult[0].split(Constants.Splitter1);
                final String test0 = tests[0];
                final String test1 = tests[1];
                final int expectedScore = Integer.parseInt(testNresult[1]);
                System.out.println("testing:");
                System.out.println(test0);
                System.out.println(test1);
                driver.feed(test0, test1);
                int resultScore = 0;
                for(int i=0; i<Constants.TEST_STR_SIZE; i+=1) {
                    resultScore += match(driver.aligned_s1.charAt(i), driver.aligned_s2.charAt(i));
                }
                if(driver.bestScore == expectedScore &&
                        driver.aligned_s1.replaceAll("_","").equals(test0) &&
                        driver.aligned_s2.replaceAll("_","").equals(test1)) {
                    System.out.println("---test passed---");
                }else {
                    System.out.println(driver.aligned_s1.replaceAll("_",""));
                    System.out.println(driver.aligned_s2.replaceAll("_",""));
                    System.out.println("expecting:");
                    System.out.println(expectedScore);
                    System.out.println("actual result:");
                    System.out.println(resultScore);
                    System.out.println("!!!test failed!!!");
                    break;
                }
            }
        }catch (FileNotFoundException e) {
            System.out.println("The tests file, \"test.txt\", is not found.\nYou probably had deleted it by mistake.");
        }catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private static String rand_string(final int len) {
        final StringBuilder str = new StringBuilder();
        for(int index=0; index<len; index+=1) {
            final int i = Driver.rand_int();
            char c;
            switch (i) {
                case 0:
                    c = 'A';
                    break;
                case 1:
                    c = 'C';
                    break;
                case 2:
                    c = 'G';
                    break;
                default:
                    c = 'T';
                    break;
            }
            str.append(c);
        }
        return str.toString();
    }

    private static int rand_int() {
        final Random rand = new Random();
        final int num_of_colors = 4;
        return rand.nextInt(num_of_colors + 1);
    }

    static void display(Result[][] scores, Driver driver) {
        if (driver.enableGUI) {
            if(scores.length > Constants.TOO_LARGE) {
                driver.frame.displayPath(scores);
            }else {
                driver.frame.displayScoresNPath(scores);
            }
            try{
                Thread.sleep(100);
            }catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }
    
    static int match(final char c1, final char c2) {
        if(c1 == Constants.SPACE_CHAR || c2 == Constants.SPACE_CHAR) {
            return Constants.SPACE_PENALTY;
        }else if(c1 == c2) {
            return Constants.MATCH;
        }else {
            return Constants.MISMATCH;
        }
    }
}
