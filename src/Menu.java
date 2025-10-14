import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener{
    JFrame frame = new JFrame("Buzz Quiz");
    JButton spButton = new JButton("SinglePlayer");
    JButton mpButton = new JButton("Multiplayer");
    JTextField textField = new JTextField();
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem spInstr = new JMenuItem("SinglePlayer");
    JMenuItem mpInstr = new JMenuItem("MultiPlayer");
    JTextArea textArea = new JTextArea();
    JButton answerButtonA = new JButton();
    JButton answerButtonB = new JButton();
    JButton answerButtonC = new JButton();
    JButton answerButtonD = new JButton();
    JLabel answerA = new JLabel();
    JLabel answerB = new JLabel();
    JLabel answerC = new JLabel();
    JLabel answerD = new JLabel();

    public void start(){
        frame.setVisible(true);
    }

    public Menu(){
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(64,64,255));
        frame.add(textField);
        frame.add(textArea);
        frame.add(mpButton);
        frame.add(spButton);
        frame.add(answerButtonA);
        frame.add(answerA);
        frame.add(answerB);
        frame.add(answerC);
        frame.add(answerD);
        frame.add(answerButtonB);
        frame.add(answerButtonC);
        frame.add(answerButtonD);
        frame.setJMenuBar(menuBar);

        menuBar.add(menu);
        menu.add(spInstr);
        menu.add(mpInstr);
        menu.add(exit);

        spInstr.addActionListener(e -> JOptionPane.showInternalMessageDialog(null,"Singleplayer plays only with mouse","SinglePlayer",JOptionPane.INFORMATION_MESSAGE));
        mpInstr.addActionListener(e -> JOptionPane.showInternalMessageDialog(null,"Multiplayer plays with Q,W,E,R and mouse for each player","MultiPlayer",JOptionPane.INFORMATION_MESSAGE));

        answerButtonA.setVisible(true);
        answerButtonB.setVisible(true);
        answerButtonC.setVisible(true);
        answerButtonD.setVisible(true);

        textField.setBounds(0,0,800,40);
        textField.setBackground(new Color(0,0,0));
        textField.setForeground(new Color(25,255,0));
        textField.setFont(new Font("Ink free",Font.PLAIN,27));
        textField.setBorder(BorderFactory.createBevelBorder(2));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        textField.setFocusable(false);
        textField.setText("Welcome to Buzz Quiz Game");

        textArea.setBounds(0,70,800,50);
        textArea.setBackground(new Color(64,64,255));
        textArea.setEditable(false);
        textArea.setFont(new Font("Serif",Font.PLAIN,35));
        textArea.setForeground(new Color(255,0,127));
        textArea.setFocusable(false);
        textArea.setText("\t        Main Menu");

        exit.addActionListener(e -> System.exit(0));

        spButton.setBounds(240,170,265,75);
        spButton.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,20));
        spButton.setForeground(new Color(255,0,0));
        spButton.setText("SINGLEPLAYER");
        spButton.setFocusable(false);
        spButton.addActionListener(this);

        mpButton.setBounds(240,300,265,75);
        mpButton.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,20));
        mpButton.setForeground(new Color(255,0,0));
        mpButton.setText("MULTIPLAYER");
        mpButton.setFocusable(false);
        mpButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==spButton){
            frame.dispose();
            new GameModesSP();
        }
        if(e.getSource()==mpButton){
            frame.dispose();
            new GameModesMP();
        }
    }
}