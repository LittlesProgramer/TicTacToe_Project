import javax.swing.*;
import java.util.Map;

public class GameLogicClass {
    private static GameFild.TicTacToeButtons moveUser = null;
    private static boolean doSelectedCrossOrCircle = false;
    private static String selectedFigure = "";

    public static void UserMove(){

        for(Map.Entry<GameFild.TicTacToeButtons,Integer> el : GameFild.getButtonMap().entrySet()){
            el.getKey().addActionListener((action)->{
                if(!doSelectedCrossOrCircle){
                    JOptionPane.showMessageDialog(null,"Prosz� wybra� k�ko lub krzy�yk");
                }else {
                    System.out.println("id = " + el.getValue());
                    moveUser = el.getKey();
                    moveUser.setEnabled(false);
                    moveUser.setOpaque(false);
                    moveUser.drawingCircleOrCross(selectedFigure);
                }
            });
        }
    }

    public static void setDoSelectedCrossOrCircle(){
        doSelectedCrossOrCircle = true;
    }
    public static void whatFigureIsSelected(String figure){
        GameLogicClass.selectedFigure = figure;
    }
}
