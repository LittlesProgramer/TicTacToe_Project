import javax.swing.*;
import java.util.*;

public class LogicAndComputerMoveClass {
    private static String tableWinMoves[] = new String[]{"123","456","789","147","258","369","159","357"};
    private static Set<Integer> yoursMovesMap = new HashSet<Integer>();
    private static Set<Integer> computerMovesMap = new HashSet<Integer>();
    private static boolean EndGame = false; //this variable describe that this game is end
    private static JLabel whoMoveIsNow = null;
    private static String lineWinMoves = null;

    public static void addYourMoveInMap(int yoursMove){ yoursMovesMap.add(yoursMove); } //this method added yours move into the yoursMovesMap map
    public static void addCompInMap(int compMove){ computerMovesMap.add(compMove); }//this methid addes computer move into the computerMoveMap map

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
                        Thread.sleep(300);
                        if(isComputerMove(whoseMoveIsNowLabel)){
                            drawVictoryLine(getLineWinMoves());
                            Thread.currentThread().interrupt();
                            whoseMoveIsNowLabel.setText("COMPUTER ARE WINNERS - SORRY YOU LOUSE - next time will be better ;) ");
                        }else{

                            if(getDrawGameMatch()){
                                whoseMoveIsNowLabel.setText("REMIS !!!!");
                                return;
                            }
                            whoseMoveIsNowLabel.setText("Who move is now: "+"your move");
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
    }

    public static boolean isComputerMove(JLabel whoseMoveIsNowLabel) {//this is computer's strategy method

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
            if(GameFild.TicTacToeButtons.getVariableDifficultLevel().equals("easy")){
                int strongest = getTheStrongestField();
                drawingAllComputerMoves(strongest);
                addCompInMap(strongest);
            }else if(GameFild.TicTacToeButtons.getVariableDifficultLevel().equals("middle")){
                int strongest = getTheStrongestField();
                drawingAllComputerMoves(strongest);
                addCompInMap(strongest);
            }else {
                int blockedMove = checkingPlayerWinAndBlockedHisMove();
                int strongest = getTheStrongestField();
                if (blockedMove != 0) {
                    drawingAllComputerMoves(blockedMove);
                    addCompInMap(blockedMove);
                } else {
                    drawingAllComputerMoves(strongest);
                    addCompInMap(strongest);
                }
            }
        }else{
            //this three variable can use to set degree of difficulty example: if comWinMove = 0 or blockedMove = 0 or both = 0
            int compWinMove = computer_sWiningMove();
            int blockedMove = checkingPlayerWinAndBlockedHisMove();
            int strongest = getTheStrongestField();

            if(GameFild.TicTacToeButtons.getVariableDifficultLevel().equals("easy")){

                if(compWinMove != 0){
                    addCompInMap(compWinMove);
                    if (checkingYoursWinsed(computerMovesMap)) {

                        drawingAllComputerMoves(compWinMove);

                        for (Map.Entry<GameFild.TicTacToeButtons, Integer> button : GameFild.getButtonMap().entrySet()) {
                            button.getKey().setEnabled(false);
                            EndGame = true;
                        }
                        return getEndGameVariable();

                    }

                }else{
                    drawingAllComputerMoves(strongest);
                    addCompInMap(strongest);
                }

            }else if(GameFild.TicTacToeButtons.getVariableDifficultLevel().equals("middle")){

            }else { //only draw difficult level
                if (compWinMove != 0) {

                    addCompInMap(compWinMove);
                    if (checkingYoursWinsed(computerMovesMap)) {

                        drawingAllComputerMoves(compWinMove);

                        for (Map.Entry<GameFild.TicTacToeButtons, Integer> button : GameFild.getButtonMap().entrySet()) {
                            button.getKey().setEnabled(false);
                            EndGame = true;
                        }
                        return getEndGameVariable();

                    } else {
                        JOptionPane.showMessageDialog(null, "cos nie tak");
                    }
                } else {
                    if (blockedMove != 0) {

                        drawingAllComputerMoves(blockedMove);
                        addCompInMap(blockedMove);

                    } else {

                        drawingAllComputerMoves(strongest);
                        addCompInMap(strongest);
                    }
                }
            }
        }

        return getEndGameVariable();
    }

    public static void drawVictoryLine(String stringVictory){
        int tabVictoy_sMoves[] = new int[stringVictory.length()];
        for(int x = 0 ; x < stringVictory.length() ; x++){
            tabVictoy_sMoves[x] = Integer.valueOf(String.valueOf(stringVictory.charAt(x)));
        }

        GameFild.TicTacToeButtons moveUser = null;

        for(int victoryLineMoves: tabVictoy_sMoves) {
            if (UserMoveClass.getSelectedFigure().equals("cross")) {
                for (Map.Entry<GameFild.TicTacToeButtons, Integer> el : GameFild.getButtonMap().entrySet()) {
                    if (el.getValue() == victoryLineMoves) {
                        moveUser = el.getKey();
                        moveUser.setEnabled(false);
                        moveUser.setOpaque(false);
                        moveUser.drawingCircleOrCross("line+"+UserMoveClass.getSelectedFigure());

                    }
                }

            } else {

                for (Map.Entry<GameFild.TicTacToeButtons, Integer> el : GameFild.getButtonMap().entrySet()) {
                    if (el.getValue() == victoryLineMoves) {
                        moveUser = el.getKey();
                        moveUser.setEnabled(false);
                        moveUser.setOpaque(false);
                        moveUser.drawingCircleOrCross("line+"+UserMoveClass.getSelectedFigure());

                    }
                }
            }
        }
    }

    public static void drawingAllComputerMoves(int drawMove) {//this method draw all figure in game
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

    public static Integer computer_sWiningMove(){//this method check computer's wining
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
                    lineWinMoves = winMoves;

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

    public static Integer checkingPlayerWinAndBlockedHisMove(){//In this method computer check that you can win if yes blocking your wins move

        List<Integer> listAvilableMoves = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));
        listAvilableMoves.removeAll(yoursMovesMap);
        listAvilableMoves.removeAll(computerMovesMap);

        String convertYourMoveMapOnTheString = "";
        for(Integer i: yoursMovesMap){
            convertYourMoveMapOnTheString = convertYourMoveMapOnTheString+i;
        }


        for(int v = 0 ; v < convertYourMoveMapOnTheString.length() ; v++) {
            for (int y = 0; y < convertYourMoveMapOnTheString.length(); y++) {

                int computerBlockYour_sWinMove = 0;

                for (String winMoves : tableWinMoves) {
                    for (Integer availableMove : listAvilableMoves) {
                        String converPlusAvailable = (((char) convertYourMoveMapOnTheString.charAt(v)) + "" + ((char) convertYourMoveMapOnTheString.charAt(y)) + availableMove);

                        //this code sort convertPlusAvailable
                        String kod = converPlusAvailable;
                        char tab[] = new char[kod.length()];
                        for (int x = 0; x < tab.length; x++) {
                            char znak = kod.charAt(x);
                            tab[x] = znak;
                        }

                        Arrays.sort(tab);
                        converPlusAvailable = new String(tab);

                        int x = 0;
                        if (converPlusAvailable.contains(winMoves)) {

                            computerBlockYour_sWinMove = availableMove;

                            for (String el : tableWinMoves) {
                                if (el.equals(winMoves)) {
                                    break;
                                }
                                x++;
                            }

                            tableWinMoves = removeWinMoveFromTableWin(tableWinMoves, x);
                            return computerBlockYour_sWinMove;
                        }
                    }
                }
            }
        }

        return 0;
    }

    public static boolean checkingYoursWinsed(Set<Integer> listChoice){//this method check your wins
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

    public static int getTheStrongestField(){//this methid return the strongest field in current game
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
    public static boolean getDrawGameMatch(){//this method return true if draw in game
        if(yoursMovesMap.size()+computerMovesMap.size() == 10){
            return true;
        }else{ return false;}
    }
    public static String getLineWinMoves(){ return lineWinMoves; }//this method return all win fild moves

}
