import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Map;

public class GameLogicClass {
    private static GameFild.TicTacToeButtons moveUser = null;
    private static boolean doSelectedPlayer = false;

    public static void UserMove(boolean selectedPlayer){
        doSelectedPlayer = selectedPlayer;

        for(Map.Entry<GameFild.TicTacToeButtons,Integer> el : GameFild.getButtonMap().entrySet()){
            el.getKey().addActionListener((action)->{
                if(!doSelectedPlayer){
                    JOptionPane.showMessageDialog(null,"Proszê wybraæ kó³ko lub krzy¿yk");
                }else {
                    System.out.println("id = " + el.getValue());
                    moveUser = el.getKey();
                    moveUser.setEnabled(false);
                    moveUser.setOpaque(false);
                    moveUser.drawingCircleOrCross("rect");
                }
            });
        }
    }
}
