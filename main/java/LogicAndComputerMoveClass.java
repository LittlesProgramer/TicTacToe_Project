import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LogicAndComputerMoveClass {
    private static HashSet<Integer> yoursMovesMap = new HashSet<Integer>();
    private static HashSet<Integer> computerMovesMap = new HashSet<Integer>();
    private static boolean EndGame = false; //this variable describe that this game is end

    public static void addYourMoveInMap(int yoursMove){ yoursMovesMap.add(yoursMove); } //this method added yours move into the yoursMovesMap map

    public static void isThisYourMove(JLabel whoseMoveIsNowLabel){ //this method checking that you win
        if(checkingYoursWinsed()){ //if true your win if false time on the computer's move

            for(Map.Entry<GameFild.TicTacToeButtons,Integer> button : GameFild.getButtonMap().entrySet()){
                    button.getKey().setEnabled(false);
                    whoseMoveIsNowLabel.setText("YOU ARE WINNERS - CONGRATULATIONS !!!");
                    EndGame = true;
            }

        }else{
            isComputerMove(whoseMoveIsNowLabel);
        }
    }

    public static void isComputerMove(JLabel whoseMoveIsNowLabel){

        //computer strategy
        //1.if first move choose the strongest field - firstComputerMove()
        //2.if this is your second move check that your oponent can win if yes block him
        //3.if this is your third,fourth,fifth ... move
        //3a. check your win
        //3b. if not 3a repeat point 2
        //3c. if not 3b repeat point 1

        if(computerMovesMap.size() == 0){
            drawingAllComputerMoves(getTheStrongestField());
            return;
        }else if(computerMovesMap.size() == 1){
            System.out.println("secoundo: "+checkingPlayerWinAndBlockedHisMove());
        }else{

        }
    }

    public static void drawingAllComputerMoves(int drawMove){
        GameFild.TicTacToeButtons moveUser = null;

        if(UserMoveClass.getSelectedFigure().equals("cross")){
            for(Map.Entry<GameFild.TicTacToeButtons,Integer> el : GameFild.getButtonMap().entrySet()){
                if(el.getValue() == drawMove){
                    moveUser = el.getKey();
                    moveUser.setEnabled(false);
                    moveUser.setOpaque(false);
                    moveUser.drawingCircleOrCross("circle");
                    computerMovesMap.add(el.getValue());
                    return;
                }
            }

        }else{

            for(Map.Entry<GameFild.TicTacToeButtons,Integer> el : GameFild.getButtonMap().entrySet()){
                if(el.getValue() == drawMove){
                    moveUser = el.getKey();
                    moveUser.setEnabled(false);
                    moveUser.setOpaque(false);
                    moveUser.drawingCircleOrCross("cross");
                    computerMovesMap.add(el.getValue());
                    return;
                }
            }
        }
    }

    public static Integer checkingPlayerWinAndBlockedHisMove(){
        String tableWinMoves[] = new String[]{"123","456","789","147","258","369","159","357"};

        List<Integer> listAvilableMoves = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));
        listAvilableMoves.removeAll(yoursMovesMap);
        listAvilableMoves.removeAll(computerMovesMap);

        String convertYourMoveMapOnTheString = "";
        for(Integer i: yoursMovesMap){
            convertYourMoveMapOnTheString = convertYourMoveMapOnTheString+i;
        }

        int computerBlockYour_sWinMove = 0;

        for(String winMoves: tableWinMoves) {
            for (Integer availableMove : listAvilableMoves) {
                String converPlusAvailable = (convertYourMoveMapOnTheString + availableMove);

                //this code sort convertPlusAvailable
                String kod = converPlusAvailable;
                char tab[] = new char[kod.length()];
                for(int x = 0 ; x < tab.length ; x++){
                    char znak = kod.charAt(x);
                    tab[x] = znak;
                }

                Arrays.sort(tab);
                converPlusAvailable = new String(tab);

                if (converPlusAvailable.contains(winMoves)){
                    computerBlockYour_sWinMove = availableMove;
                }
                converPlusAvailable = convertYourMoveMapOnTheString;
            }
        }

        return computerBlockYour_sWinMove;
    }

    public static boolean checkingYoursWinsed(){
        String moves = "";
        for(Integer el : yoursMovesMap){
            System.out.println("el : "+el);
            moves = moves+el;
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
                EndGame = true;
            }else{
                System.out.println("false");
                EndGame = false;
            }
        }
        return EndGame;
    }

    public static int getTheStrongestField(){
        Set<Integer> theStrongestField = new HashSet<>();
        theStrongestField.addAll(computerMovesMap);
        theStrongestField.addAll(yoursMovesMap);
        for(Integer theStrongest: theStrongestField){
            if(theStrongest != 5){
                return 5;
            }
            if(theStrongest != 1){
                return 1;
            }
            if(theStrongest != 3){
                return 3;
            }
            if(theStrongest != 7){
                return 7;
            }
            if(theStrongest != 9){
                return 9;
            }
            if(theStrongest != 2){
                return 2;
            }
            if(theStrongest != 4){
                return 4;
            }
            if(theStrongest != 6){
                return 6;
            }
            if(theStrongest != 8){
                return 8;
            }
        }

        return 0;
    }

    public static boolean getEndGameVariable(){ return EndGame; }//this method return variable discribe Game is Over

}
