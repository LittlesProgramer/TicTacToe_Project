import javax.swing.*;
import java.util.Map;

public class UserMoveClass {
    private static GameFild.TicTacToeButtons moveUser = null;
    private static boolean doSelectedCrossOrCircle = false;
    private static String selectedFigure = "";

    public static void UserMove(JLabel whoseMoveIsNowLabel){

        for(Map.Entry<GameFild.TicTacToeButtons,Integer> el : GameFild.getButtonMap().entrySet()){
            el.getKey().addActionListener((action)->{
                if(!doSelectedCrossOrCircle){
                    JOptionPane.showMessageDialog(null,"Proszê wybraæ kó³ko lub krzy¿yk");
                }else {
                    System.out.println("id = " + el.getValue());
                    moveUser = el.getKey();
                    moveUser.setEnabled(false);
                    moveUser.setOpaque(false);
                    moveUser.drawingCircleOrCross(selectedFigure);
                    LogicAndComputerMoveClass.addYourMoveInMap(el.getValue()); //add your move to table
                    //class LogicClass checking on this moment you wined
                    LogicAndComputerMoveClass.isThisYourMove(whoseMoveIsNowLabel);
                    if(!LogicAndComputerMoveClass.getEndGameVariable()){
                        whoseMoveIsNowLabel.setText("Who move is now: "+"computer move");
                    }
                }
            });
        }
    }

    public static void setDoSelectedCrossOrCircle(){
        doSelectedCrossOrCircle = true;
    }
    public static void whatFigureIsSelected(String figure){ UserMoveClass.selectedFigure = figure; }
    public static String getSelectedFigure(){ return UserMoveClass.selectedFigure; }
}
