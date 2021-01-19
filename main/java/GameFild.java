import javax.swing.*;
import java.awt.*;
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

    private static Map<TicTacToeButtons,Integer> buttonMap = new HashMap<>(); //this map contains pairs - button and his location on the panelButtons

    public GameFild(){
        addTicTacToeFild();
        GameLogicClass.UserMove(false);
    }

    //this method add buttons,chooser panel cross or circle and show whose move is now
    public void addTicTacToeFild(){
        this.setLayout(new BorderLayout());

        this.add(panelCrossCircle,BorderLayout.NORTH);
        panelCrossCircle.add(crossOrCircle);
        panelCrossCircle.add(startGame);

        this.add(panelButtons,BorderLayout.CENTER);
        panelButtons.setLayout(new GridLayout(3,3));

        TicTacToeButtons buttons = null;

        //add 9 buttons to panelButtons
        for(int x = 1 ; x <= 9 ; x++){
            buttons = new TicTacToeButtons();
            buttons.setOpaque(false);
            panelButtons.add(buttons);
            buttonMap.put(buttons,x);
        }

        //action for this button is choose circle or cross for user
        startGame.addActionListener((action)->{
            System.out.println("cr = "+crossOrCircle.getItemAt(crossOrCircle.getSelectedIndex()));
            crossOrCircle.setEnabled(false);
            startGame.setEnabled(false);
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
    class TicTacToeButtons extends JButton{
        private Rectangle2D rect2D = new Rectangle2D.Double(10,10,10,10);
        private String figure = null;

        public void paintComponent(Graphics g){
            //System.out.println("paint");
            if(figure != null) {
                if (figure.equals("rect")) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.draw(rect2D);
                }
            }else{
                return;
            }
        }

        //according to this method choice is drawing circle or cross
        public void drawingCircleOrCross(String s){
            figure = s;
        }
    }
}
