import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class GameFild extends JFrame {
    private JComboBox<String> crossOrCircle = new JComboBox<String>(new String[]{"Cross","Circle"}); //choose user
    private JButton startGame = new JButton("Start Game"); //start game button

    private JPanel panelCrossCircle = new JPanel(); //pannel including user option choosing circle or cross
    private JPanel panelButtons = new JPanel(); //panel containing 9 TicTacToe palying filds
    private JPanel whoseMoveIsNow = new JPanel(); //this panel contains JLabel whoseMoveIsNowLabel
    private JLabel whoseMoveIsNowLabel = new JLabel("Who move is now: "); //this label show whose move is now
    private TicTacToeButtons buttons = null;

    private static Map<TicTacToeButtons,Integer> buttonMap = new HashMap<>(); //this map contains pairs - button and his location on the panelButtons
    //private boolean doCircleOrCross = false; //this variable discribe cross or circle choice from user

    public GameFild(){
        addTicTacToeFild();
        UserMoveClass.UserMove(whoseMoveIsNowLabel);
    }

    //this method add buttons,chooser panel cross or circle and show whose move is now
    public void addTicTacToeFild(){ //this method add button,label itd....
        this.setLayout(new BorderLayout());

        this.add(panelCrossCircle,BorderLayout.NORTH);
        panelCrossCircle.add(crossOrCircle);
        panelCrossCircle.add(startGame);

        this.add(panelButtons,BorderLayout.CENTER);
        panelButtons.setLayout(new GridLayout(3,3));

        //TicTacToeButtons buttons = null;

        //add 9 buttons to panelButtons
        for(int x = 1 ; x <= 9 ; x++){
            buttons = new TicTacToeButtons();
            buttons.setOpaque(false);
            panelButtons.add(buttons);
            buttonMap.put(buttons,x);
        }

        //action for this button is choose circle or cross for user
        startGame.addActionListener((action)->{

            if(crossOrCircle.getItemAt(crossOrCircle.getSelectedIndex()).equals("Cross")){
                UserMoveClass.setDoSelectedCrossOrCircle();
                UserMoveClass.whatFigureIsSelected("cross");
            }else{
                UserMoveClass.setDoSelectedCrossOrCircle();
                UserMoveClass.whatFigureIsSelected("circle");
            }

            crossOrCircle.setEnabled(false);
            startGame.setEnabled(false);

            whoseMoveIsNowLabel.setText("Who move is now: "+"your move");
        });

        //add label who show whose move is now
        this.add(whoseMoveIsNow,BorderLayout.SOUTH);
        whoseMoveIsNow.setLayout(new FlowLayout(FlowLayout.LEFT));
        whoseMoveIsNow.add(whoseMoveIsNowLabel);

    }

    //this static method return buttonMap
    public static Map<TicTacToeButtons,Integer> getButtonMap() {
        return buttonMap;
    }

    //this is class TicTacToe which draw circle or cross according to the user's choice using the drawingCircleOrCross method
    class TicTacToeButtons extends JButton{//this is Button field class
        private int wid;
        private int hei;

        private Rectangle2D rect2D = null;
        private Ellipse2D elli2D = null;

        private Line2D lineHorizontal = null;
        private Line2D lineVertical = null;
        private Line2D leftLine = null;
        private Line2D rightLine = null;

        private BasicStroke stroke = new BasicStroke(3);

        private String figure = null;

        public void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(stroke);

            if(figure != null) {

                wid = getXY().width;
                hei = getXY().height;

                rect2D = new Rectangle2D.Double(35,35,wid-70,hei-70);
                elli2D = new Ellipse2D.Double(35,35,wid-70,hei-70);

                double x = this.getInsets().left;
                double y = this.getInsets().top;
                double width = this.getWidth();
                double height = this.getHeight();
                double middleX = width/2;
                double middleY = height/2;

                lineVertical = new Line2D.Double(middleX,y,middleX,height);
                lineHorizontal = new Line2D.Double(this.getY(),middleY,width,middleY);
                leftLine = new Line2D.Double(0,0,width,height);
                rightLine = new Line2D.Double(0,height,width,0);

                String lineVinMoves = LogicAndComputerMoveClass.getLineWinMoves();

                if (figure.equals("cross")) {
                    g2.setColor(Color.RED);
                    g2.draw(rect2D);
                }else if(figure.equals("circle")){
                    g2.setColor(Color.GREEN);
                    g2.draw(elli2D);
                }else if(figure.equals("line+cross")){

                    if(figure.equals("line+cross") && (lineVinMoves.equals("147") || lineVinMoves.equals("258") || lineVinMoves.equals("369"))){
                        g2.setColor(Color.YELLOW);
                        g2.draw(lineVertical);
                        g2.setColor(Color.GREEN);
                        g2.draw(new Ellipse2D.Double(35,35,wid-70,hei-70));
                    }else if(figure.equals("line+cross") && (lineVinMoves.equals("123") || lineVinMoves.equals("456") || lineVinMoves.equals("789"))){
                        g2.setColor(Color.YELLOW);
                        g2.draw(lineHorizontal);
                        g2.setColor(Color.GREEN);
                        g2.draw(new Ellipse2D.Double(35,35,wid-70,hei-70));
                    }else if(figure.equals("line+cross") && lineVinMoves.equals("159")){
                        g2.setColor(Color.YELLOW);
                        g2.draw(leftLine);
                        g2.setColor(Color.GREEN);
                        g2.draw(new Ellipse2D.Double(35,35,wid-70,hei-70));
                    }else if(figure.equals("line+cross") && lineVinMoves.equals("357")){
                        g2.setColor(Color.YELLOW);
                        g2.draw(rightLine);
                        g2.setColor(Color.GREEN);
                        g2.draw(new Ellipse2D.Double(35,35,wid-70,hei-70));
                    }

                }else if(figure.equals("line+circle")){

                    if(figure.equals("line+circle") && (lineVinMoves.equals("147") || lineVinMoves.equals("258") || lineVinMoves.equals("369"))){
                        g2.setColor(Color.YELLOW);
                        g2.draw(lineVertical);
                        g2.setColor(Color.RED);
                        g2.draw(new Rectangle2D.Double(35,35,wid-70,hei-70));
                    }else if(figure.equals("line+circle") && (lineVinMoves.equals("123") || lineVinMoves.equals("456") || lineVinMoves.equals("789"))){
                        g2.setColor(Color.YELLOW);
                        g2.draw(lineHorizontal);
                        g2.setColor(Color.RED);
                        g2.draw(new Rectangle2D.Double(35,35,wid-70,hei-70));
                    }else if(figure.equals("line+circle") && lineVinMoves.equals("159")){
                        g2.setColor(Color.YELLOW);
                        g2.draw(leftLine);
                        g2.setColor(Color.RED);
                        g2.draw(new Rectangle2D.Double(35,35,wid-70,hei-70));
                    }else if(figure.equals("line+circle") && lineVinMoves.equals("357")){
                        g2.setColor(Color.YELLOW);
                        g2.draw(rightLine);
                        g2.setColor(Color.RED);
                        g2.draw(new Rectangle2D.Double(35,35,wid-70,hei-70));
                    }
                }

            }else{
                return;
            }
        }

        //according to this method choice is drawing circle or cross
        public void drawingCircleOrCross(String s){
            figure = s;
            repaint();
        }

        public int getWidthButtonTicTacToe(){
            return this.getSize().width;
        }
        public int getHeightButtonTicTacToe(){
            return this.getSize().height;
        }
        public Dimension getXY(){
            Dimension dim = new Dimension();
            dim.setSize(getWidthButtonTicTacToe(),getHeightButtonTicTacToe());
            return dim;
        }
    }
}
