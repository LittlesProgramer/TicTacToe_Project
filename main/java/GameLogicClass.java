import javax.swing.*;
import java.util.Map;

public class GameLogicClass {
    public static void UserMove(){
        for(Map.Entry<JButton,Integer> el : GameFild.getButtonMap().entrySet()){
            el.getKey().addActionListener((action)->{
                System.out.println("id = "+el.getValue());
            });
        }
    }
}
