import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameFild extends JFrame {
    private JComboBox<String> crossOrCircle = new JComboBox<String>(new String[]{"Cross","Circle"});
    private JButton startGame = new JButton("Start Game");

    private JPanel panelCrossCircle = new JPanel();
    private JPanel panelButtons = new JPanel();

    private static Map<JButton,Integer> buttonMap = new HashMap<>();

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

        JButton fild = null;

        for(int x = 1 ; x <= 9 ; x++){
            fild = new JButton();
            panelButtons.add(fild);
            buttonMap.put(fild,x);
        }

        startGame.addActionListener((action)->{
            System.out.println("cr = "+crossOrCircle.getItemAt(crossOrCircle.getSelectedIndex()));
            crossOrCircle.setEnabled(false);
        });
    }

    public static Map<JButton,Integer> getButtonMap() {
        return buttonMap;
    }

    public void userChooseCrossOrCircle(){

    }
}
