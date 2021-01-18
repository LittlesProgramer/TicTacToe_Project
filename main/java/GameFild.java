import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameFild extends JFrame {
    //private static Map<Integer,String> pozitionMarkMap = new HashMap<>();
    private static Map<JButton,Integer> buttonMap = new HashMap<>();

    public GameFild(){
        addTicTacToeFild();
        GameLogicClass.UserMove();
    }

    public void addTicTacToeFild(){
        this.setLayout(new GridLayout(3,3));
        JButton fild = null;
        for(int x = 1 ; x <= 9 ; x++){
            fild = new JButton();
            this.add(fild);
            buttonMap.put(fild,x);
        }
    }

    public static Map<JButton,Integer> getButtonMap() {
        return buttonMap;
    }
}
