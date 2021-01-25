import javax.swing.*;
import java.util.*;

public class LogicAndComputerMoveClass {
    private static String tableWinMoves[] = new String[]{"123","456","789","147","258","369","159","357"};
    private static Set<Integer> yoursMovesMap = new HashSet<Integer>();
    private static Set<Integer> computerMovesMap = new HashSet<Integer>();
    private static boolean EndGame = false; //this variable describe that this game is end
    private static JLabel whoMoveIsNow = null;

    public static void addYourMoveInMap(int yoursMove){ yoursMovesMap.add(yoursMove); } //this method added yours move into the yoursMovesMap map
    public static void addCompInMap(int compMove){ computerMovesMap.add(compMove); }

    public static void isThisYourMove(JLabel whoseMoveIsNowLabel) { //this method checking that you win
        whoMoveIsNow = whoseMoveIsNowLabel;
        if(checkingYoursWinsed(yoursMovesMap)){ //if true your win if false time on the computer's move

            for(Map.Entry<GameFild.TicTacToeButtons,Integer> button : GameFild.getButtonMap().entrySet()){
                    button.getKey().setEnabled(false);
                    whoseMoveIsNowLabel.setText("YOU ARE WINNERS - CONGRATULATIONS !!!");
                    EndGame = true;
            }

        }else{
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    whoMoveIsNow.setText("Who move is now: "+"computer move");
                    try {
                        Thread.sleep(500);
                        isComputerMove(whoseMoveIsNowLabel);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    whoMoveIsNow.setText("Who move is now: "+"your move");
                }
            });
            t.start();
        }
    }

    public static void isComputerMove(JLabel whoseMoveIsNowLabel) {

        //computer strategy
        //1.if first move choose the strongest field - firstComputerMove()
        //2.if this is your second move check that your oponent can win if yes block him
        //3.if this is your third,fourth,fifth ... move
        //3a. check your win
        //3b. if not 3a repeat point 2
        //3c. if not 3b repeat point 1

        if(computerMovesMap.size() == 0){
            int strongestMove = getTheStrongestField();
            drawingAllComputerMoves(strongestMove);
            addCompInMap(strongestMove);
        }else if(computerMovesMap.size() == 1){
            int blockedMove = checkingPlayerWinAndBlockedHisMove();
            int strongest = getTheStrongestField();
            if(blockedMove != 0){
                drawingAllComputerMoves(blockedMove);
                addCompInMap(blockedMove);
            }else{
                drawingAllComputerMoves(strongest);
                addCompInMap(strongest);
            }
        }else{
            int compWinMove = computer_sWiningMove();
            int blockedMove = checkingPlayerWinAndBlockedHisMove();
            int strongest = getTheStrongestField();

            System.out.println("STRONG = "+strongest+" BLOCKED = "+blockedMove+" COMPWINMOVE = "+compWinMove);

            if(compWinMove != 0){
                System.out.println("IF WIN");
                addCompInMap(compWinMove);
                if(checkingYoursWinsed(computerMovesMap)){
                    System.out.println("WYGRANA !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    drawingAllComputerMoves(compWinMove);

                    for(Map.Entry<GameFild.TicTacToeButtons,Integer> button : GameFild.getButtonMap().entrySet()){
                        button.getKey().setEnabled(false);
                        EndGame = true;
                    }

                }else{ JOptionPane.showMessageDialog(null,"cos nie tak");}
            }else{
                if(blockedMove != 0){
                    System.out.println("IF BLOCKED");
                    drawingAllComputerMoves(blockedMove);
                    addCompInMap(blockedMove);
                }else{
                    System.out.println("IF STRONGEST");
                    drawingAllComputerMoves(strongest);
                    addCompInMap(strongest);
                }
            }
        }
    }

    public static void drawingAllComputerMoves(int drawMove) {
        GameFild.TicTacToeButtons moveUser = null;

        if(UserMoveClass.getSelectedFigure().equals("cross")){
            for(Map.Entry<GameFild.TicTacToeButtons,Integer> el : GameFild.getButtonMap().entrySet()){
                if(el.getValue() == drawMove){
                    moveUser = el.getKey();
                    moveUser.setEnabled(false);
                    moveUser.setOpaque(false);
                    moveUser.drawingCircleOrCross("circle");
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
                    return;
                }
            }
        }
    }

    public static Integer computer_sWiningMove(){
        //String tableWinMoves[] = new String[]{"123","456","789","147","258","369","159","357"};

        List<Integer> listAvilableMoves = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));
        listAvilableMoves.removeAll(yoursMovesMap);
        listAvilableMoves.removeAll(computerMovesMap);

        String convertComputerMoveMapOnTheString = "";
        for(Integer i: computerMovesMap){
            convertComputerMoveMapOnTheString = convertComputerMoveMapOnTheString+i;
        }

        int computer_sWinedMove = 0;

        for(String winMoves: tableWinMoves) {
            for (Integer availableMove : listAvilableMoves) {
                String converPlusAvailable = (convertComputerMoveMapOnTheString + availableMove);

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
                    System.out.println("rozpatrywane Wygranej = "+converPlusAvailable+" winMoves = "+winMoves);
                    computer_sWinedMove = availableMove;

                    int x = 0;
                    for(String el: tableWinMoves){
                        if(el.equals(winMoves)){
                            break;
                        }
                        x++;
                    }

                    tableWinMoves = removeWinMoveFromTableWin(tableWinMoves,x);
                }
                converPlusAvailable = convertComputerMoveMapOnTheString;
            }
        }

        return computer_sWinedMove;
    }

    public static Integer checkingPlayerWinAndBlockedHisMove(){
        System.out.println("metoda blokowania 1");

        List<Integer> listAvilableMoves = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));
        listAvilableMoves.removeAll(yoursMovesMap);
        listAvilableMoves.removeAll(computerMovesMap);
        System.out.println("metoda blokowania 2");
        String convertYourMoveMapOnTheString = "";
        for(Integer i: yoursMovesMap){
            convertYourMoveMapOnTheString = convertYourMoveMapOnTheString+i;
        }
        System.out.println("metoda blokowania 3");
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
                System.out.println("winMoves = "+winMoves+" convertPlusAvailable = "+converPlusAvailable+" availableMove = "+availableMove);

                int x = 0;
                if (converPlusAvailable.contains(winMoves)){
                    System.out.println("rozpatrywane blokowanie = "+converPlusAvailable+" winMoves = "+winMoves);
                    computerBlockYour_sWinMove = availableMove;

                    for(String el: tableWinMoves){
                        if(el.equals(winMoves)){
                            break;
                        }
                        x++;
                    }

                    tableWinMoves = removeWinMoveFromTableWin(tableWinMoves,x);
                }
                converPlusAvailable = convertYourMoveMapOnTheString;

            }
        }

        return computerBlockYour_sWinMove;
    }

    public static boolean checkingYoursWinsed(Set<Integer> listChoice){
        String moves = "";
        for(Integer el : listChoice){
            moves = moves+el;
            //discribe vins moves
            /*123,456,789 horizontal
            147,258,369 vertical
            159,357 cross  */
            boolean horizontal1 = moves.contains("1")&&moves.contains("2")&&moves.contains("3");
            boolean horizontal2 = moves.contains("4")&&moves.contains("5")&&moves.contains("6");
            boolean horizontal3 = moves.contains("7")&&moves.contains("8")&&moves.contains("9");
            boolean vertical1 = moves.contains("1")&&moves.contains("4")&&moves.contains("7");
            boolean vertical2 = moves.contains("2")&&moves.contains("5")&&moves.contains("8");
            boolean vertical3 = moves.contains("3")&&moves.contains("6")&&moves.contains("9");
            boolean cross1 = moves.contains("1")&&moves.contains("5")&&moves.contains("9");
            boolean cross2 = moves.contains("3")&&moves.contains("5")&&moves.contains("7");

            if(horizontal1 || horizontal2 || horizontal3 || vertical1 || vertical2 || vertical3 || cross1 || cross2){
                EndGame = true;
            }else{
                EndGame = false;
            }
        }
        return EndGame;
    }

    public static int getTheStrongestField(){
        List<Integer> listAvilableMoves = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));
        listAvilableMoves.removeAll(yoursMovesMap);
        listAvilableMoves.removeAll(computerMovesMap);

        for(Integer five: listAvilableMoves){
            if(five == 5){
                return 5;
            }
        }
        for(Integer one: listAvilableMoves){
            if(one == 1){
                return 1;
            }
        }
        for(Integer three: listAvilableMoves){
            if(three == 3){
                return 3;
            }
        }
        for(Integer seven: listAvilableMoves){
            if(seven == 7){
                return 7;
            }
        }
        for(Integer nine: listAvilableMoves){
            if(nine == 9){
                return 9;
            }
        }
        for(Integer two: listAvilableMoves){
            if(two == 2){
                return 2;
            }
        }
        for(Integer four: listAvilableMoves){
            if(four == 4){
                return 4;
            }
        }
        for(Integer six: listAvilableMoves){
            if(six == 6){
                return 6;
            }
        }
        for(Integer eight: listAvilableMoves){
            if(eight == 8){
                return 8;
            }
        }

        return 0;
    }

    public static String[] removeWinMoveFromTableWin(String tab[],int zz){
        String nowa[] = new String[tab.length-1];

        int z = zz;
        for(int x = 0 ; x < (tab.length-1); x++){
            if(x >= z) {
                String temp = tab[x + 1];
                nowa[x] = temp;
            }else{
                nowa[x] = tab[x];
            }
        }
        tab = nowa;
        return tab;
    }

    public static boolean getEndGameVariable(){ return EndGame; }//this method return variable discribe Game is Over

}
