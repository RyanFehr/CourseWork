package project;
import javax.swing.*;
import java.awt.*;

/**
 * Created by wma22_000 on 11/17/2015.
 */
public class DNAFrame extends JFrame{
    public Driver driver;
    public String str1;
    public String str2;
    public int panelSize;

    private JPanel panel;
    private JButton[][] buttons;

    public DNAFrame(final Driver _driver, final String _str1, final String _str2) {
        this.driver = _driver;
        this.str1 = _str1;
        this.str2 = _str2;
        this.panelSize = this.str1.length() + 2;
        this.panel = new JPanel(new GridLayout(panelSize, panelSize));

        this.initButtons();

        this.setContentPane(this.panel);
        //this.pack();
        this.setSize(640, 640);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initButtons() {
        this.buttons = new JButton[this.panelSize][this.panelSize];
        for(int x=0; x<panelSize; x+=1) {
            for(int y=0; y<panelSize; y+=1) {
                final JButton newButton = new JButton();
                newButton.setEnabled(false);
                newButton.setBackground(Color.WHITE);
                newButton.setOpaque(true);
                buttons[x][y] = newButton;
                if(x == 0) {
                    if(y > 1) {
                        final int charLocation = y - 2;
                        newButton.setText(this.str1.substring(charLocation, charLocation+1));
                    }
                }
                else if(y == 0) {
                    if(x > 1) {
                        final int charLocation = x - 2;
                        newButton.setText(this.str2.substring(charLocation,charLocation+1));
                    }
                }
                this.panel.add(newButton);
            }
        }
    }

    static public boolean[][] find_path(Result[][] scores) {
	boolean[][] path = new boolean[scores.length][scores.length];
        int j = scores.length - 2;
        int i = j;
	path[i+1][j+1] = true;
        while(j > -1 || i > -1) {
	    if (j>-1 && i>-1 && (scores[i + 1][j + 1].dir == Mutation.M)) {
                j -= 1;
                i -= 1;
            } else if (j > -1 && (scores[i + 1][j + 1].dir == Mutation.D)) {
                j -= 1;
            } else if (i > -1 && (scores[i + 1][j + 1].dir == Mutation.I)) {
                i -= 1;
            } else {
                System.out.println(j);
                System.out.println(i);
                break;
            }
            path[i+1][j+1] = true;
        }
	return path;
    }

    public void displayPath(final Result[][] scores) {
	boolean[][] path = find_path(scores);
        for(int x=1; x<this.panelSize; x+=1) {
            final int pathX = x - 1;
            for(int y=1; y<panelSize; y+=1) {
                final int pathY = y-1;
                if(path[pathX][pathY]) {
                    buttons[x][y].setBackground(Color.CYAN);
                }
            }
        }
    }

    public void displayScoresNPath(final Result[][] scores) {
	boolean[][] path = find_path(scores);
        for(int x=1; x<this.panelSize; x+=1) {
            final int scoresX = x - 1;
            for(int y=1; y<this.panelSize; y+=1) {
                final int scoresY = y - 1;
                final JButton jbt = this.buttons[x][y];
                jbt.setText(Integer.toString(scores[scoresX][scoresY].score));
                if(path[scoresX][scoresY]) {
                    jbt.setBackground(Color.CYAN);
                }
            }
        }
    }
}
