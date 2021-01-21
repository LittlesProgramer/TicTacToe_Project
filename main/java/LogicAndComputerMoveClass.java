import javax.swing.*;
import java.util.HashSet;
import java.util.Map;

public class LogicAndComputerMoveClass {
    private static HashSet<Integer> yoursMovesMap = new HashSet<Integer>();
    private static HashSet<Integer> computerMovesMap = new HashSet<Integer>();
    private static boolean EndGame = false;

    public static void addYourMoveInMap(int yoursMove){ yoursMovesMap.add(yoursMove); } //this method added yours move into the yoursMovesMap map

    public static void isWin(JLabel whoseMoveIsNowLabel){ //this method checking that you win
        String moves = "";
        whoseMoveIsNowLabel.setText("KOKOKO");
        for(Integer el : yoursMovesMap){
            System.out.println("el : "+el);
            moves = moves+el;
            System.out.println("moves = "+moves);
            //discribe vins moves
            /*123,456,789 horizontal
            147,258,369 vertical
            159,357 cross  */
            boolean horizontal1 = moves.contains("1")&&moves.contains("5")&&moves.contains("9");
            boolean horizontal2 = moves.contains("4")&&moves.contains("5")&&moves.contains("6");
            boolean horizontal3 = moves.contains("7")&&moves.contains("8")&&moves.contains("9");
            boolean vertical1 = moves.contains("1")&&moves.contains("4")&&moves.contains("7");
            boolean vertical2 = moves.contains("2")&&moves.contains("5")&&moves.contains("8");
            boolean vertical3 = moves.contains("3")&&moves.contains("6")&&moves.contains("9");
            boolean cross1 = moves.contains("1")&&moves.contains("5")&&moves.contains("9");
            boolean cross2 = moves.contains("3")&&moves.contains("5")&&moves.contains("7");

            if(horizontal1 || horizontal2 || horizontal3 || vertical1 || vertical2 || vertical3 || cross1 || cross2){
                System.out.println("true");
                for(Map.Entry<GameFild.TicTacToeButtons,Integer> button : GameFild.getButtonMap().entrySet()){
                    button.getKey().setEnabled(false);
                    whoseMoveIsNowLabel.setText("YOU ARE WINNERS - CONGRATULATIONS !!!");
                    EndGame = true;
                }
            }else{
                System.out.println("false");
            }
        }
    }

    public static boolean getEndGameVariable(){ return EndGame; }//this method return variable discribe Game is Over

}
