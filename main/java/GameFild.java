import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class GameFild extends JFrame {
    private JComboBox<String> crossOrCircle = new JComboBox<String>(new String[]{"Cross","Circle"});
    private JButton startGame = new JButton("Start Game");

    private JPanel panelCrossCircle = new JPanel();
    private JPanel panelButtons = new JPanel();

    private static Map<TicTacToeButtons,Integer> buttonMap = new HashMap<>();

    public GameFild(){
        addTicTacToeFild();
        GameLogicClass.UserMove();
    }

    public void addTicTacToeFild(){
        this.setLayout(new BorderLayout());

        this.add(panelCrossCircle,BorderLayout.NORTH);
        panelCrossCircle.add(crossOrCircle);
        panelCrossCircle.add(startGame);

        this.add(panelButtons,BorderLayout.CENTER);
        panelButtons.setLayout(new GridLayout(3,3));

        TicTacToeButtons buttons = null;

        for(int x = 1 ; x <= 9 ; x++){
            buttons = new TicTacToeButtons();
            buttons.setOpaque(false);
            panelButtons.add(buttons);
            buttonMap.put(buttons,x);
        }

        startGame.addActionListener((action)->{
            System.out.println("cr = "+crossOrCircle.getItemAt(crossOrCircle.getSelectedIndex()));
            crossOrCircle.setEnabled(false);
        });
    }

    public static Map<TicTacToeButtons,Integer> getButtonMap() {
        return buttonMap;
    }

    class TicTacToeButtons extends JButton{
        private Rectangle2D rect2D = new Rectangle2D.Double(10,10,10,10);
        private String figure = null;

        public void paintComponent(Graphics g){
            System.out.println("paint");
            if(figure != null) {
                if (figure.equals("rect")) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.draw(rect2D);
                }
            }else{
                return;
            }
        }

        public void rysuj(String s){
            figure = s;
        }
    }
}
