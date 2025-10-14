import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Arrays;
import java.util.Random;

public class GameModesSP implements ActionListener{
    JFrame frame = new JFrame("Buzz Quiz");
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton bet250 = new JButton("250");
    JButton bet500 = new JButton("500");
    JButton bet750 = new JButton("750");
    JButton bet1000 = new JButton("1000");
    JButton answerButtonA = new JButton();
    JButton answerButtonB = new JButton();
    JButton answerButtonC = new JButton();
    JButton answerButtonD = new JButton();
    JLabel answerA = new JLabel();
    JLabel answerB = new JLabel();
    JLabel answerC = new JLabel();
    JLabel answerD = new JLabel();
    JTextArea timeArea = new JTextArea();
    JButton newGame = new JButton("New Game");
    JButton exitButton = new JButton("Exit");
    Menu menu = new Menu();
    private Timer time;
    JButton mouse_bet250 = new JButton("250");
    JButton mouse_bet500 = new JButton("500");
    JButton mouse_bet750 = new JButton("750");
    JButton mouse_bet1000 = new JButton("1000");
    JButton keyboard_bet250 = new JButton("250");
    JButton keyboard_bet500 = new JButton("500");
    JButton keyboard_bet750 = new JButton("750");
    JButton keyboard_bet1000 = new JButton("1000");
    JButton endBet = new JButton("End Bet");

    Questions q = new Questions();
    Random rand = new Random();

    String[] questions = q.getQuestions();
    String[][] options = q.getAnswers();
    char[] answers = q.getCorrectAnswer();
    int[] questAppeared = new int[93];

    private int timerPoints = 5000;
    int rounds = 0;
    int random;
    double gamePoints = 0;
    char answer = ' ';
    int betPoints = 0;

    RoundType rt = new RoundType();
    String type = rt.TypeOfRounds();

    public GameModesSP() {
        for (int i : questAppeared) {
            questAppeared[i] = 0;
        }
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(25, 25, 25));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timeArea.setBounds(710, 520, 75, 45);
        timeArea.setEditable(false);
        timeArea.setBackground(new Color(0, 0, 0));
        timeArea.setFont(new Font("Ink free", Font.BOLD, 25));
        timeArea.setForeground(new Color(255, 0, 0));
        timeArea.setFocusable(false);
        timeArea.setVisible(false);
        timeArea.setText(Integer.toString(timerPoints));

        textField.setBounds(0, 0, 800, 40);
        textField.setBackground(new Color(0, 0, 0));
        textField.setForeground(new Color(0, 9, 255));
        textField.setFont(new Font("Ink free", Font.BOLD, 27));
        textField.setBorder(BorderFactory.createBevelBorder(2));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        textField.setFocusable(false);

        answerA.setBounds(100, 80, 300, 80);
        answerA.setBackground(new Color(50, 50, 50));
        answerA.setForeground(new Color(0, 255, 0));
        answerA.setFont(new Font("Serif", Font.PLAIN, 25));
        answerA.setVisible(false);

        answerB.setBounds(100, 210, 300, 80);
        answerB.setBackground(new Color(50, 50, 50));
        answerB.setForeground(new Color(0, 255, 0));
        answerB.setFont(new Font("Serif", Font.PLAIN, 25));
        answerB.setVisible(false);

        answerC.setBounds(100, 340, 300, 80);
        answerC.setBackground(new Color(50, 50, 50));
        answerC.setForeground(new Color(0, 255, 0));
        answerC.setFont(new Font("Serif", Font.PLAIN, 25));
        answerC.setVisible(false);

        answerD.setBounds(100, 470, 300, 80);
        answerD.setBackground(new Color(50, 50, 50));
        answerD.setForeground(new Color(0, 255, 0));
        answerD.setFont(new Font("Serif", Font.PLAIN, 25));
        answerD.setVisible(false);

        bet250.setBounds(220, 200, 120, 50);
        bet250.setFont(new Font("Ink free", Font.BOLD, 25));
        bet250.setFocusable(false);
        bet250.addActionListener(this);
        bet250.setVisible(false);

        bet500.setBounds(430, 200, 120, 50);
        bet500.setFont(new Font("Ink free", Font.BOLD, 25));
        bet500.setFocusable(false);
        bet500.addActionListener(this);
        bet500.setVisible(false);

        bet750.setBounds(220, 350, 120, 50);
        bet750.setFont(new Font("Ink free", Font.BOLD, 25));
        bet750.setFocusable(false);
        bet750.addActionListener(this);
        bet750.setVisible(false);

        bet1000.setBounds(430, 350, 120, 50);
        bet1000.setFont(new Font("Ink free", Font.BOLD, 25));
        bet1000.addActionListener(this);
        bet1000.setFocusable(false);
        bet1000.setVisible(false);

        textArea.setBackground(new Color(25, 25, 25));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setFont(new Font("Serif", Font.PLAIN, 22));
        textArea.setBounds(0, 40, 800, 55);
        textArea.setFocusable(false);
        textArea.setForeground(new Color(255, 0, 127));


        answerButtonA.setBounds(0, 100, 50, 50);
        answerButtonA.setFont(new Font("Serif", Font.BOLD, 20));
        answerButtonA.setFocusable(false);
        answerButtonA.setText("A");
        answerButtonA.addActionListener(this);
        answerButtonA.setVisible(false);

        answerButtonB.setBounds(0, 230, 50, 50);
        answerButtonB.setFont(new Font("Serif", Font.BOLD, 20));
        answerButtonB.setFocusable(false);
        answerButtonB.setText("B");
        answerButtonB.addActionListener(this);
        answerButtonB.setVisible(false);

        answerButtonC.setBounds(0, 360, 50, 50);
        answerButtonC.setFont(new Font("Serif", Font.BOLD, 20));
        answerButtonC.setFocusable(false);
        answerButtonC.setText("C");
        answerButtonC.addActionListener(this);
        answerButtonC.setVisible(false);

        answerButtonD.setBounds(0, 490, 50, 50);
        answerButtonD.setFont(new Font("Serif", Font.BOLD, 20));
        answerButtonD.setFocusable(false);
        answerButtonD.setText("D");
        answerButtonD.addActionListener(this);
        answerButtonD.setVisible(false);

        frame.add(textField);
        frame.add(textArea);
        frame.add(answerButtonA);
        frame.add(answerButtonB);
        frame.add(answerButtonC);
        frame.add(answerButtonD);
        frame.add(answerA);
        frame.add(answerB);
        frame.add(answerC);
        frame.add(answerD);
        frame.add(bet250);
        frame.add(bet500);
        frame.add(bet750);
        frame.add(bet1000);
        frame.add(timeArea);
        frame.add(newGame);
        frame.add(exitButton);
        frame.add(mouse_bet250);
        frame.add(mouse_bet500);
        frame.add(mouse_bet750);
        frame.add(mouse_bet1000);
        frame.add(keyboard_bet250);
        frame.add(keyboard_bet500);
        frame.add(keyboard_bet750);
        frame.add(keyboard_bet1000);
        frame.add(endBet);
        typeOfRound();
    }

    public void typeOfRound() {
        do {
            type = rt.TypeOfRounds();
        } while (type.equals("Quick Answer") || type.equals("Thermometer"));
        if (type.equals("Betting")) {
            Bet();
        }
        if (type.equals("Correct Answer")) {
            CorrectAnswer();
        }
        if (type.equals("Stop The Timer")) {
            StopTheTimer();
        }
    }

    public void StopTheTimer() {
        textField.setText("Stop The Timer Question " + (rounds + 1));
        timerPoints = 5000;
        timeArea.setVisible(true);

        time = new Timer(100, e -> {
            if (timerPoints <= 0) {
                ((Timer) e.getSource()).stop();
                displayAnswers();
            } else {
                timerPoints -= 100;
                timeArea.setText(Integer.toString(timerPoints));
            }
        });
        time.start();
        Questions();
    }

    public void CorrectAnswer() {
        timeArea.setVisible(false);
        textField.setText("Correct Answer Question " + (rounds + 1));
        Questions();
    }

    public void Bet() {
        answerA.setVisible(false);
        timeArea.setVisible(false);
        answerB.setVisible(false);
        answerC.setVisible(false);
        answerD.setVisible(false);
        answerButtonA.setVisible(false);
        answerButtonB.setVisible(false);
        answerButtonC.setVisible(false);
        answerButtonD.setVisible(false);
        textArea.setVisible(false);

        for (JButton jButton : Arrays.asList(bet250, bet500, bet750, bet1000)) {
            jButton.setVisible(true);
        }
        textField.setText("Betting Question " + (rounds + 1));
        if (rounds >= 15) {
            Questions();
        }
    }

    public void Questions() {
        answerA.setVisible(true);
        answerB.setVisible(true);
        answerC.setVisible(true);
        answerD.setVisible(true);
        answerButtonA.setVisible(true);
        answerButtonB.setVisible(true);
        answerButtonC.setVisible(true);
        answerButtonD.setVisible(true);
        textArea.setVisible(true);

        if (rounds >= 15) {
            showResults();
        } else {
            do {
                random = rand.nextInt(93);

            } while (questAppeared[random] == 1);
            questAppeared[random] = 1;

            textArea.setText(questions[random]);
            answerA.setText(options[random][0]);
            answerB.setText(options[random][1]);
            answerC.setText(options[random][2]);
            answerD.setText(options[random][3]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (type) {
            case "Betting":
                if (e.getSource() == bet250) {
                    betPoints = 250;
                    for (JButton jButton : Arrays.asList(bet250, bet500, bet750, bet1000)) {
                        jButton.setVisible(false);
                    }
                    Questions();
                }
                if (e.getSource() == bet500) {
                    betPoints = 500;
                    for (JButton jButton : Arrays.asList(bet250, bet500, bet750, bet1000)) {
                        jButton.setVisible(false);
                    }
                    Questions();
                }
                if (e.getSource() == bet750) {
                    betPoints = 750;
                    for (JButton jButton : Arrays.asList(bet250, bet500, bet750, bet1000)) {
                        jButton.setVisible(false);
                    }
                    Questions();
                }
                if (e.getSource() == bet1000) {
                    betPoints = 1000;
                    for (JButton jButton : Arrays.asList(bet250, bet500, bet750, bet1000)) {
                        jButton.setVisible(false);
                    }
                    Questions();
                }
                if (e.getSource() == answerButtonA) {
                    answer = 'A';
                    if (answer == answers[random]) {
                        gamePoints += betPoints;
                    } else {
                        gamePoints -= betPoints;
                    }
                    displayAnswers();
                }
                if (e.getSource() == answerButtonB) {
                    answer = 'B';
                    if (answer == answers[random]) {
                        gamePoints += betPoints;
                    } else {
                        gamePoints -= betPoints;
                    }
                    displayAnswers();
                }
                if (e.getSource() == answerButtonC) {
                    answer = 'C';
                    if (answer == answers[random]) {
                        gamePoints += betPoints;
                    } else {
                        gamePoints -= betPoints;
                    }
                    displayAnswers();
                }
                if (e.getSource() == answerButtonD) {
                    answer = 'D';
                    if (answer == answers[random]) {
                        gamePoints += betPoints;
                    } else {
                        gamePoints -= betPoints;
                    }
                    displayAnswers();
                }
                break;
            case "Correct Answer":
                if (e.getSource() == answerButtonA) {
                    answer = 'A';
                    if (answer == answers[random]) {
                        gamePoints += 1000;
                    }
                }
                if (e.getSource() == answerButtonB) {
                    answer = 'B';
                    if (answer == answers[random]) {
                        gamePoints += 1000;
                    }
                }
                if (e.getSource() == answerButtonC) {
                    answer = 'C';
                    if (answer == answers[random]) {
                        gamePoints += 1000;
                    }
                }
                if (e.getSource() == answerButtonD) {
                    answer = 'D';
                    if (answer == answers[random]) {
                        gamePoints += 1000;
                    }
                }
                displayAnswers();
                break;
            case "Stop The Timer":
                if (e.getSource() == answerButtonA) {
                    time.stop();
                    answer = 'A';
                    if (answer == answers[random]) {
                        gamePoints += timerPoints * 0.2;
                    }
                }
                if (e.getSource() == answerButtonB) {
                    time.stop();
                    answer = 'B';
                    if (answer == answers[random]) {
                        gamePoints += timerPoints * 0.2;
                    }
                }
                if (e.getSource() == answerButtonC) {
                    time.stop();
                    answer = 'C';
                    if (answer == answers[random]) {
                        gamePoints += timerPoints * 0.2;
                    }
                }
                if (e.getSource() == answerButtonD) {
                    time.stop();
                    answer = 'D';
                    if (answer == answers[random]) {
                        gamePoints += timerPoints * 0.2;
                    }
                }
                displayAnswers();
                break;
        }
    }

    public void displayAnswers() {
        answerButtonA.setEnabled(false);
        answerButtonB.setEnabled(false);
        answerButtonC.setEnabled(false);
        answerButtonD.setEnabled(false);

        if (answers[random] != 'A') {
            answerA.setForeground(new Color(255, 0, 0));
        }
        if (answers[random] != 'B') {
            answerB.setForeground(new Color(255, 0, 0));
        }
        if (answers[random] != 'C') {
            answerC.setForeground(new Color(255, 0, 0));
        }
        if (answers[random] != 'D') {
            answerD.setForeground(new Color(255, 0, 0));
        }
        Timer pause = getTimer();
        pause.start();
    }

    private Timer getTimer() {
        Timer pause = new Timer(2000, e -> {
            answerA.setForeground(new Color(0, 255, 25));
            answerB.setForeground(new Color(0, 255, 25));
            answerC.setForeground(new Color(0, 255, 25));
            answerD.setForeground(new Color(0, 255, 25));
            answer = ' ';
            rounds++;
            answerButtonA.setEnabled(true);
            answerButtonB.setEnabled(true);
            answerButtonC.setEnabled(true);
            answerButtonD.setEnabled(true);
            typeOfRound();
        });
        pause.setRepeats(false);
        return pause;
    }

    public void showResults() {
        for (JButton jButton : Arrays.asList(answerButtonA, answerButtonB, answerButtonC, answerButtonD)) {
            jButton.setVisible(false);
        }
        for (JLabel jLabel : Arrays.asList(answerA, answerB, answerC, answerD)) {
            jLabel.setVisible(false);
        }
        for (JButton jButton : Arrays.asList(bet250, bet500, bet750, bet1000)) {
            jButton.setVisible(false);
        }
        timeArea.setVisible(false);
        textField.setText("GAME FINISHED");
        textArea.setText("\t\tYou scored " + (int)gamePoints + " points");

        newGame.setBounds(240, 170, 265, 75);
        newGame.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        newGame.setForeground(new Color(255, 0, 0));

        newGame.setFocusable(false);
        newGame.addActionListener(e -> {
            frame.dispose();
            menu.start();
        });
        exitButton.setBounds(240, 300, 265, 75);
        exitButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        exitButton.setForeground(new Color(255, 0, 0));
        exitButton.setFocusable(false);
        exitButton.addActionListener(e -> System.exit(0));
    }
}
