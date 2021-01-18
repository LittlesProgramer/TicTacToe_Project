import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameFild extends JFrame {
    private Map<Integer,String> pozitionMarkMap = new HashMap<>();
    private Map<JButton,Map<Integer,String>> buttonMap = new HashMap<>();

    public GameFild(){
        addTicTacToeFild();
    }

    public void addTicTacToeFild(){
        this.setLayout(new GridLayout(3,3));
        JButton fild = null;
        for(int x = 0 ; x < 9 ; x++){
            fild = new JButton();
            this.add(fild);
            pozitionMarkMap.put(x,null);
            buttonMap.put(fild,pozitionMarkMap);
        }
    }

}
