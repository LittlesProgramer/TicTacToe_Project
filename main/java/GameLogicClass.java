import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Map;

public class GameLogicClass {
    private static GameFild.TicTacToeButtons moveUser = null;

    public static void UserMove(){
        for(Map.Entry<GameFild.TicTacToeButtons,Integer> el : GameFild.getButtonMap().entrySet()){
            el.getKey().addActionListener((action)->{
                System.out.println("id = "+el.getValue());
                moveUser = el.getKey();
                moveUser.setEnabled(false);
                moveUser.setOpaque(false);
                moveUser.rysuj("rect");
            });
        }
    }
}
