import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
public class Window extends JComponent {
    static boolean clicked;
    static boolean isAdjective;
    static JFrame frameWindow;
    static JButton generate;
    static JButton pref;
    static Generator gen;
    static String name = "";
    static JComboBox numNounSyl;
    static JComboBox numAdjSyl;
    static final String[] sylOptions = {"1-4", "1", "2", "3", "4"};
    public Window() throws IOException {
        clicked = false;
        isAdjective = true;
        gen = new Generator(isAdjective);
    }

    public static void main(String[] args) throws IOException {
        frameWindow = new JFrame("Indie Band Name Generator");
        //frameWindow.setLayout(new BoxLayout(frameWindow.getContentPane(), BoxLayout.Y_AXIS));
        frameWindow.setLocationRelativeTo(null);
        frameWindow.setSize(new Dimension(500,310));
        frameWindow.setResizable(false);
        Window mainWindow = new Window();
        frameWindow.add(mainWindow);

        generate = new JButton("Generate");
        generate.setFont(new Font("Helvetica", Font.BOLD, 27));
        generate.setVisible(true);
        generate.setBounds(162,170,175,50);
        mainWindow.add(generate);

        numAdjSyl = new JComboBox<String>(sylOptions);
        numNounSyl = new JComboBox<String>(sylOptions);
        numAdjSyl.setEditable(true);
        numNounSyl.setEditable(true);
        JPanel wrapper = new JPanel();
        JPanel wrapper2 = new JPanel();
        wrapper.add(numAdjSyl);
        wrapper2.add(numNounSyl);
        wrapper.setBounds(50, 210, 60,30);
        wrapper2.setBounds(400, 210, 60, 30);
        mainWindow.add(wrapper);
        mainWindow.add(wrapper2);

        JRadioButton adjectives = new JRadioButton("Adjectives");
        JRadioButton verbs = new JRadioButton("Verbs");
        ButtonGroup group = new ButtonGroup();
        group.add(adjectives);
        group.add(verbs);
        adjectives.setBounds(180, 220, 100, 30);
        verbs.setBounds(180, 245, 70, 30);
        mainWindow.add(adjectives);
        mainWindow.add(verbs);

        adjectives.setSelected(true);
        frameWindow.validate();
        frameWindow.setVisible(true);
        frameWindow.repaint();

        generate.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {clicked = true;
                    name = gen.generate().toUpperCase();
                }
            });

        adjectives.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    if (adjectives.isSelected()) {
                        isAdjective = true;
                    } else {
                        isAdjective = false;
                    }
                    try {
                        gen = new Generator(isAdjective);
                    } catch (Exception exception){}
                    frameWindow.repaint();
                    changeSyl();
                }
            });

        verbs.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    if (verbs.isSelected()) {
                        isAdjective = false;
                    } else {
                        isAdjective = true;
                    }
                    try {
                        gen = new Generator(isAdjective);
                    } catch (Exception exception){}

                    frameWindow.repaint();
                    changeSyl();
                }
            });

        numAdjSyl.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    changeSyl();
                }
            });

        numNounSyl.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    changeSyl();
                }
            });
    }

    public static void changeSyl()
    {
        String adjChoice = (String)numAdjSyl.getEditor().getItem();
        String nounChoice =(String)numNounSyl.getEditor().getItem();
        if (adjChoice.equals("1-4") && nounChoice.equals("1-4")) {
        } else if (nounChoice.equals("1-4")) {
            int numSyl = Integer.parseInt(adjChoice);
            try{
                gen = new Generator(numSyl,0,isAdjective);
            }
            catch(Exception ex){}
        } else if(adjChoice.equals("1-4")) {
            int numSyl = Integer.parseInt(nounChoice);
            try{
                gen = new Generator(0,numSyl,isAdjective);
            }
            catch(Exception ex){}
        } else {
            int numSyl = Integer.parseInt(adjChoice);
            int numSyl2 = Integer.parseInt(nounChoice);
            try {
                gen = new Generator(numSyl,numSyl2,isAdjective);
            } catch(Exception ex){}
        }
    }

    public void resetClicked() {
        //System.out.println("Before notClicked " + clicked);
        clicked = false;
        frameWindow.repaint();
    }

    public boolean isClicked() {
		return clicked;
	}

    public void paintComponent(Graphics g) {
        g.setColor(new Color(51,153,200));
        g.fillRect(0,0,1000,1000);
        g.setColor(Color.WHITE);
        //g.drawRect(10,60,70,70);
        g.fillRect(20,70,460,70);
        g.setColor(Color.BLACK);

        FontMetrics metrics = g.getFontMetrics(new Font("Helvetica", Font.BOLD, 25));
        int x = 20 + (460 - metrics.stringWidth(name)) / 2;
        int y = 70 + ((70 - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(new Font("Helvetica", Font.BOLD, 25));
        g.drawString(name, x,y);

        g.setFont(new Font("Helvetica", Font.BOLD, 15));
        if (isAdjective == true) {
            g.drawString("Adjective Syllables", 15, 195);
        } else {
            g.drawString("Verb Syllables", 28, 195);
        }
        g.drawString("Noun Syllables", 375, 195);
        g.setFont(new Font("Helvetica", Font.BOLD, 30));
        g.setColor(new Color(7, 49, 117));
        g.drawString("Indie Band Name Generator", 55, 45);
        resetClicked();
    }
}

