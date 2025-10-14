import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;

public class GameModesMP implements ActionListener,KeyListener {
    JFrame frame = new JFrame("Buzz Quiz");
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
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
    Menu menu = new Menu();
    int streak1 = 0;
    int streak2 = 0;
    int thermoTimes = 0;

    Timer time;
    int timerPoints;
    int random;
    char keyboard_answer = ' ';
    private double keyboard_score = 0;
    private boolean keyboardAnswered = false;

    char mouse_answer = ' ';
    private double mouse_score = 0;
    private boolean mouseAnswered = false;

    Questions q = new Questions();
    Random rand = new Random();

    String[] questions = q.getQuestions();
    String[][] options = q.getAnswers();
    char[] answers = q.getCorrectAnswer();
    int[] questAppeared = new int[93];
    int rounds = 0;

    RoundType rt = new RoundType();
    String type = rt.TypeOfRounds();

    public GameModesMP(){
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.setSize(800,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(25,25,25));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField.setBounds(0,0,800,40);
        textField.setBackground(new Color(0,0,0));
        textField.setForeground(new Color(0,9,255));
        textField.setFont(new Font("Ink free",Font.BOLD,27));
        textField.setBorder(BorderFactory.createBevelBorder(2));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        textField.setFocusable(false);

        timeArea.setBounds(710, 520, 75, 45);
        timeArea.setEditable(false);
        timeArea.setBackground(new Color(0, 0, 0));
        timeArea.setFont(new Font("Ink free", Font.BOLD, 25));
        timeArea.setForeground(new Color(255, 0, 0));
        timeArea.setFocusable(false);
        timeArea.setVisible(false);
        timeArea.setText(Integer.toString(timerPoints));

        textArea.setBackground(new Color(25, 25, 25));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setFont(new Font("Serif", Font.PLAIN, 22));
        textArea.setBounds(0, 40, 800, 55);
        textArea.setFocusable(false);
        textArea.setForeground(new Color(255, 0, 127));

        answerA.setBounds(100,80,300,80);
        answerA.setBackground(new Color(50,50,50));
        answerA.setForeground(new Color(0,255,0));
        answerA.setFont(new Font("Serif",Font.PLAIN,25));
        answerA.setVisible(false);

        answerB.setBounds(100,210,300,80);
        answerB.setBackground(new Color(50,50,50));
        answerB.setForeground(new Color(0,255,0));
        answerB.setFont(new Font("Serif",Font.PLAIN,25));
        answerB.setVisible(false);

        answerC.setBounds(100,340,300,80);
        answerC.setBackground(new Color(50,50,50));
        answerC.setForeground(new Color(0,255,0));
        answerC.setFont(new Font("Serif",Font.PLAIN,25));
        answerC.setVisible(false);

        answerD.setBounds(100,470,300,80);
        answerD.setBackground(new Color(50,50,50));
        answerD.setForeground(new Color(0,255,0));
        answerD.setFont(new Font("Serif",Font.PLAIN,25));
        answerD.setVisible(false);

        answerButtonA.setBounds(0,100,50,50);
        answerButtonA.setFont(new Font("Serif",Font.BOLD,20));
        answerButtonA.setFocusable(false);
        answerButtonA.setText("A");
        answerButtonA.addActionListener(this);
        answerButtonA.setVisible(false);

        answerButtonB.setBounds(0,230,50,50);
        answerButtonB.setFont(new Font("Serif",Font.BOLD,20));
        answerButtonB.setFocusable(false);
        answerButtonB.setText("B");
        answerButtonB.addActionListener(this);
        answerButtonB.setVisible(false);

        answerButtonC.setBounds(0,360,50,50);
        answerButtonC.setFont(new Font("Serif",Font.BOLD,20));
        answerButtonC.setFocusable(false);
        answerButtonC.setText("C");
        answerButtonC.addActionListener(this);
        answerButtonC.setVisible(false);

        answerButtonD.setBounds(0,490,50,50);
        answerButtonD.setFont(new Font("Serif",Font.BOLD,20));
        answerButtonD.setFocusable(false);
        answerButtonD.setText("D");
        answerButtonD.addActionListener(this);
        answerButtonD.setVisible(false);

        frame.add(timeArea);
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
        frame.add(newGame);
        typeOfRound();
    }
    public void typeOfRound(){

        do{
            type = rt.TypeOfRounds();
        }while (type.equals("Quick Answer") || type.equals("Betting"));

        if(type.equals("Correct Answer")){
            CorrectAnswer();
        }
        if(type.equals("Stop The Timer")){
            StopTheTimer();
        }
        if(type.equals("Thermometer")){
            Thermometer();
        }

    }

    public void Thermometer(){
        timeArea.setVisible(false);
        thermoTimes++;
        textField.setText("Thermometer Question "+(rounds+1));
        if(streak1 ==3){
            keyboard_score += 5000;
            streak1 = 0;
        }
        if(streak2 ==3){
            mouse_score += 5000;
            streak2 = 0;
        }
        Questions();
    }

    public void StopTheTimer(){
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
    public void CorrectAnswer(){
        timeArea.setVisible(false);
        textField.setText("Correct Answer Question "+(rounds+1));
        Questions();
    }
    public void Questions(){
        answerA.setVisible(true);
        answerB.setVisible(true);
        answerC.setVisible(true);
        answerD.setVisible(true);
        answerButtonA.setVisible(true);
        answerButtonB.setVisible(true);
        answerButtonC.setVisible(true);
        answerButtonD.setVisible(true);
        textArea.setVisible(true);

        if(rounds >= 15){
            showResults();
        }
        else{
            do{
                random = rand.nextInt(93);

            }while(questAppeared[random] == 1);
            questAppeared[random] = 1;

            textArea.setText(questions[random]);
            answerA.setText(options[random][0]);
            answerB.setText(options[random][1]);
            answerC.setText(options[random][2]);
            answerD.setText(options[random][3]);
        }
    }
    public void displayAnswers(){
        answerButtonA.setEnabled(false);
        answerButtonB.setEnabled(false);
        answerButtonC.setEnabled(false);
        answerButtonD.setEnabled(false);

        if(answers[random]!='A'){
            answerA.setForeground(new Color(255,0,0));
        }
        if(answers[random]!='B'){
            answerB.setForeground(new Color(255,0,0));
        }
        if(answers[random]!='C'){
            answerC.setForeground(new Color(255,0,0));
        }
        if(answers[random]!='D'){
            answerD.setForeground(new Color(255,0,0));
        }
        Timer pause = new Timer(2000, e -> {

            answerA.setForeground(new Color(0,255,25));
            answerB.setForeground(new Color(0,255,25));
            answerC.setForeground(new Color(0,255,25));
            answerD.setForeground(new Color(0,255,25));
            rounds++;
            answerButtonA.setEnabled(true);
            answerButtonB.setEnabled(true);
            answerButtonC.setEnabled(true);
            answerButtonD.setEnabled(true);
            mouse_answer = ' ';
            keyboard_answer = ' ';
            keyboardAnswered = false;
            mouseAnswered = false;
            frame.addKeyListener(this);

            if(type.equals("Thermometer")){
                if(thermoTimes <3){
                    Thermometer();
                }
                else{
                    thermoTimes = 0;
                    typeOfRound();
                }
            }
            else{
                typeOfRound();
            }

        });
        pause.setRepeats(false);
        pause.start();
    }
    public void showResults() {
        for (JButton jButton : Arrays.asList(answerButtonA, answerButtonB, answerButtonC, answerButtonD)) {
            jButton.setVisible(false);
        }
        for (JLabel jLabel : Arrays.asList(answerA, answerB, answerC, answerD)) {
            jLabel.setVisible(false);
        }
        timeArea.setVisible(false);
        textField.setText("GAME FINISHED");
        textArea.setText("Keyboard Player scored: "+(int)keyboard_score+" \tMouse player scored: "+(int)mouse_score);

        newGame.setBounds(240, 170, 265, 75);
        newGame.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        newGame.setForeground(new Color(255, 0, 0));

        newGame.setFocusable(false);
        newGame.addActionListener(e -> {
            frame.dispose();
            menu.start();
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyChar();
        switch (type) {
            case "Correct Answer":
                switch (key) {
                    case 113:
                        keyboard_answer = 'A';
                        frame.removeKeyListener(this);
                        keyboardAnswered = true;
                        if (keyboard_answer == answers[random]) {
                            keyboard_score += 1000;
                        }
                        if (mouseAnswered) {
                            displayAnswers();
                        }
                        break;
                    case 119:
                        keyboard_answer = 'B';
                        frame.removeKeyListener(this);
                        keyboardAnswered = true;
                        if (keyboard_answer == answers[random]) {
                            keyboard_score += 1000;
                        }
                        if (mouseAnswered) {
                            displayAnswers();
                        }
                        break;
                    case 101:
                        keyboard_answer = 'C';
                        frame.removeKeyListener(this);
                        keyboardAnswered = true;
                        if (keyboard_answer == answers[random]) {
                            keyboard_score += 1000;
                        }
                        if (mouseAnswered) {
                            displayAnswers();
                        }
                        break;
                    case 114:
                        keyboard_answer = 'D';
                        frame.removeKeyListener(this);
                        keyboardAnswered = true;
                        if (keyboard_answer == answers[random]) {
                            keyboard_score += 1000;
                        }
                        if (mouseAnswered) {
                            displayAnswers();
                        }
                        break;
                }
                break;
            case "Thermometer":
                switch (key) {
                    case 113:
                        keyboard_answer = 'A';
                        frame.removeKeyListener(this);
                        keyboardAnswered = true;
                        if (keyboard_answer == answers[random]) {
                            streak1++;
                        } else {
                            streak1 = 0;
                        }
                        if (mouseAnswered) {
                            displayAnswers();
                        }
                        break;
                    case 119:
                        keyboard_answer = 'B';
                        frame.removeKeyListener(this);
                        keyboardAnswered = true;
                        if (keyboard_answer == answers[random]) {
                            streak1++;
                        } else {
                            streak1 = 0;
                        }
                        if (mouseAnswered) {
                            displayAnswers();
                        }
                        break;
                    case 101:
                        keyboard_answer = 'C';
                        frame.removeKeyListener(this);
                        keyboardAnswered = true;
                        if (keyboard_answer == answers[random]) {
                            streak1++;
                        } else {
                            streak1 = 0;
                        }
                        if (mouseAnswered) {
                            displayAnswers();
                        }
                        break;
                    case 114:
                        keyboard_answer = 'D';
                        frame.removeKeyListener(this);
                        keyboardAnswered = true;
                        if (keyboard_answer == answers[random]) {
                            streak1++;
                        } else {
                            streak1 = 0;
                        }
                        if (mouseAnswered) {
                            displayAnswers();
                        }
                        break;
                }
                break;
            case "Stop The Timer":
                switch (key) {
                    case 113:
                        keyboard_answer = 'A';
                        frame.removeKeyListener(this);
                        keyboardAnswered = true;
                        if (keyboard_answer == answers[random]) {
                            keyboard_score += timerPoints * 0.2;
                        }
                        if (mouseAnswered) {
                            time.stop();
                            displayAnswers();
                        }
                        break;
                    case 119:
                        keyboard_answer = 'B';
                        frame.removeKeyListener(this);
                        keyboardAnswered = true;
                        if (keyboard_answer == answers[random]) {
                            keyboard_score += timerPoints * 0.2;
                        }
                        if (mouseAnswered) {
                            time.stop();
                            displayAnswers();
                        }
                        break;
                    case 101:
                        keyboard_answer = 'C';
                        frame.removeKeyListener(this);
                        keyboardAnswered = true;
                        if (keyboard_answer == answers[random]) {
                            keyboard_score += timerPoints * 0.2;
                        }
                        if (mouseAnswered) {
                            time.stop();
                            displayAnswers();
                        }
                        break;
                    case 114:
                        keyboard_answer = 'D';
                        frame.removeKeyListener(this);
                        keyboardAnswered = true;
                        if (keyboard_answer == answers[random]) {
                            keyboard_score += timerPoints * 0.2;
                        }
                        if (mouseAnswered) {
                            time.stop();
                            displayAnswers();
                        }
                        break;
                }
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(type.equals("Correct Answer")){
            if(e.getSource()==answerButtonA){
                mouse_answer = 'A';
                mouseAnswered = true;
                if(mouse_answer == answers[random]){
                    mouse_score += 1000;
                }
                if(keyboardAnswered){
                    displayAnswers();
                }
            }
            if(e.getSource()==answerButtonB){
                mouse_answer = 'B';
                mouseAnswered = true;
                if(mouse_answer == answers[random]){
                    mouse_score += 1000;
                }
                if(keyboardAnswered){
                    displayAnswers();
                }
            }
            if(e.getSource()==answerButtonC){
                mouse_answer = 'C';
                mouseAnswered = true;
                if(mouse_answer == answers[random]){
                    mouse_score += 1000;
                }
                if(keyboardAnswered){
                    displayAnswers();
                }
            }
            if(e.getSource()==answerButtonD){
                mouse_answer = 'D';
                mouseAnswered = true;
                if(mouse_answer == answers[random]){
                    mouse_score += 1000;
                }
                if(keyboardAnswered){
                    displayAnswers();
                }
            }
        }
        if(type.equals("Stop The Timer")){
            if(e.getSource()==answerButtonA){
                mouse_answer = 'A';
                mouseAnswered = true;
                if(mouse_answer == answers[random]){
                    mouse_score += timerPoints*0.2;
                }
                if(keyboardAnswered){
                    time.stop();
                    displayAnswers();
                }
            }
            if(e.getSource()==answerButtonB){
                mouse_answer = 'B';
                mouseAnswered = true;
                if(mouse_answer == answers[random]){
                    mouse_score += timerPoints*0.2;
                }
                if(keyboardAnswered){
                    time.stop();
                    displayAnswers();
                }
            }
            if(e.getSource()==answerButtonC){
                mouse_answer = 'C';
                mouseAnswered = true;
                if(mouse_answer == answers[random]){
                    mouse_score += timerPoints*0.2;
                }
                if(keyboardAnswered){
                    time.stop();
                    displayAnswers();
                }
            }
            if(e.getSource()==answerButtonD){
                mouse_answer = 'D';
                mouseAnswered = true;
                if(mouse_answer == answers[random]){
                    mouse_score += timerPoints*0.2;
                }
                if(keyboardAnswered){
                    time.stop();
                    displayAnswers();
                }
            }
        }
        if(type.equals("Thermometer")){
            if(e.getSource()==answerButtonA){
                mouse_answer = 'A';
                mouseAnswered = true;
                if(mouse_answer == answers[random]){
                    streak2++;
                }
                else{
                    streak2 = 0;
                }
                if(keyboardAnswered){
                    displayAnswers();
                }
            }
            if(e.getSource()==answerButtonB){
                mouse_answer = 'B';
                mouseAnswered = true;
                if(mouse_answer == answers[random]){
                    streak2++;
                }
                else{
                    streak2 = 0;
                }
                if(keyboardAnswered){
                    displayAnswers();
                }
            }
            if(e.getSource()==answerButtonC){
                mouse_answer = 'C';
                mouseAnswered = true;
                if(mouse_answer == answers[random]){
                    streak2++;
                }
                else{
                    streak2 = 0;
                }
                if(keyboardAnswered){
                    displayAnswers();
                }
            }
            if(e.getSource()==answerButtonD){
                mouse_answer = 'D';
                mouseAnswered = true;
                if(mouse_answer == answers[random]){
                    streak2++;
                }
                else{
                    streak2 = 0;
                }
                if(keyboardAnswered){
                    displayAnswers();
                }
            }
        }
    }
}