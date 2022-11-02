import java.util.Scanner;

import javax.xml.transform.Templates;

import java.lang.*;
import java.security.PublicKey;


public class App {

    public static class player{

        String choice = "no choice yet";
        String name = "no name yet";

        public void nameSet(String name){
            this.name = name;
        }
        public void choiceSet(String choice){
            this.choice = choice;
        }
        
        public String nameGet(){
            return this.name;
        }
        public String choiceGet(){
            return this.choice;
        }

    }

    public static class board{

        int board[][] = new int[3][3];
        String actualboard[][] = new String[3][3];
             
        public void boardFIller(){
            for(int i = 0; i < 3; i ++){
                for(int j = 0; j < 3; j++){
                    this.board[i][j] = 10;
                    this.actualboard[i][j] = " ";
                }
            } 
        }


        public void emptyBoardOut(){
            System.out.println("  1   2  3");
            System.out.println("A   |   |  ");
            System.out.println("  --+---+--");
            System.out.println("B   |   |  ");
            System.out.println("  --+---+--");
            System.out.println("C   |   |  ");           
            
            
        }

        public int move(String tempx, String tempy, int cap){
            int x = 0, y = 0;
            if(tempx.contains("A")){
                x = 1;   
            }
            else if(tempx.contains("B")){
                x = 2;
            }
            else x = 3;
            y = Integer.parseInt(tempy);
            if(this.board[x-1][y-1] == 10){
                if(cap % 2 == 0){
                    this.board[x-1][y-1] = 2;// p1 = 2
                    cap++;
                }
                else{ 
                    this.board[x-1][y-1] = 3;// p2 = 3
                    cap++;
                }
            }
            else{
                System.out.println("position taken");
            }
            return cap;
        }

        public void boardPrint(String p1choice, String p2choice, String p1name, String p2name, int cap, String winner){

            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(this.board[i][j] == 2){
                        actualboard[i][j] = p1choice;
                    }
                    else if(this.board[i][j] == 3){
                        actualboard[i][j] = p2choice;
                    }
                }
            }

            

            System.out.println("  1   2   3");
            System.out.println("A "+actualboard[0][0]+" | "+actualboard[0][1]+" | "+actualboard[0][2]);
            System.out.println("  --+---+--");
            System.out.println("B "+actualboard[1][0]+" | "+actualboard[1][1]+" | "+actualboard[1][2]);
            System.out.println("  --+---+--");
            System.out.println("C "+actualboard[2][0]+" | "+actualboard[2][1]+" | "+actualboard[2][2]); 
            
            if(cap < 9){
                if(winner.contains("noname")){
                if(cap % 2 == 0){
                    System.out.println(p1name + "'s turn:");
                }
                else System.out.println(p2name + "'s turn:");
                }
            }
            
        }

        public String winnercheck(int[][] curboard, String p1name, String p2name){
            String winner = "noname";
            int temp = 0;

            //rows

            for(int i = 0; i < 3; i++){
                temp = temp + curboard[i][0];
            }
            winner = helper(temp, p1name, p2name);
            temp = 0;
            if(winner.contains("noname")){
                for(int i = 0; i < 3; i++){
                    temp = temp + curboard[i][1];
                }
                winner = helper(temp, p1name, p2name);
                temp = 0;
            }

            if(winner.contains("noname")){
                for(int i = 0; i < 3; i++){
                    temp = temp + curboard[i][2];
                }
                winner = helper(temp, p1name, p2name);
                temp = 0;
            }
            //columns
            if(winner.contains("noname")){
                for(int i = 0; i < 3; i++){
                    temp = temp + curboard[0][i];
                }
                winner = helper(temp, p1name, p2name);
                temp = 0;
            }

            if(winner.contains("noname")){
                for(int i = 0; i < 3; i++){
                    temp = temp + curboard[1][i];
                }
                winner = helper(temp, p1name, p2name);
                temp = 0;
            }

            if(winner.contains("noname")){
                for(int i = 0; i < 3; i++){
                    temp = temp + curboard[2][i];
                }
                winner = helper(temp, p1name, p2name);
            }

            //diagonals
            if(winner.contains("noname")){
                temp = 0;
                temp = curboard[0][0] + curboard[1][1] + curboard[2][2];
                winner = helper(temp, p1name, p2name);
                temp = 0;
            }

            if(winner.contains("noname")){
                temp = curboard[0][2] + curboard[1][1] + curboard[2][0];
                winner = helper(temp, p1name, p2name);
            }
            return winner;
        }

        public String helper(int temp, String p1name, String p2name ){
            String winner = "noname";
            if(temp == 6){
                winner = p1name;
            }
            else if(temp == 9){
                winner = p2name;
            }
            return winner;
        }





    }

    public static void main(String[] args) throws Exception {

        String choice = "rndm";
        Scanner scan = new Scanner(System.in);
        player p1 = new player();
        player p2 = new player();
        board board = new board();

        System.out.println("");
        System.out.println("****************************************************");
        System.out.println("            Welcome to tic_tac_toe!");
        System.out.println("****************************************************");
        System.out.println("");
        System.out.println("First player must chose \"O\" or \"X\". Type \"O\" or \"X\".");
        while(!(choice.contains("O") || choice.contains("X"))){
            choice = scan.nextLine();
            if(!(choice.contains("O") || choice.contains("X"))){
                System.out.println("please type \"O\" or \"X\" ");
            }
        }

        System.out.println("Player with \"" + choice + "\" is player 1. Type your name:");
        p1.nameSet(scan.nextLine());
        p1.choiceSet(choice);

        if(choice.contains("O")){
            choice = "X";
        }
        else choice = "O";

        System.out.println("Player with \"" + choice + "\" is player 2. Type your name:");
        p2.nameSet(scan.nextLine());
        p2.choiceSet(choice);
        System.out.println("");
        System.out.println("**************************************************************************");
        System.out.println("-This is the board with the coordinates:");
        board.emptyBoardOut();
        System.out.println("-Coordinates must be typed in capital with the letters first ex.\"A3\".");
        System.out.println("-If you want to stop the game instead of coordinates input the space button ");
        System.out.println("***************************************************************************");
        System.out.println("");
        System.out.println("The game begins!!! " + p1.nameGet() + " type your coordinates:");

        board.boardFIller();

        char temp;
        int cap = 0;
        String coord = scan.nextLine(), tempx, tempy, winner = "noname";
        Boolean helper = false;
        //apo kato tsekarisma gia na mpoun oi sostes syntetagmenes
        //se mia polyyy megalh grammh
        while(!coord.contains("A1") && !coord.contains("A2") && !coord.contains("A3") && !coord.contains("B1") && !coord.contains("B2") && !coord.contains("B3") && !coord.contains("C1") && !coord.contains("C2") && !coord.contains("C3") && !coord.contains(" ")){
            System.out.println("wrong coordinates please type again");
            coord = scan.nextLine();
        }
        while(!coord.contains(" ") && cap < 9 && helper == false){
            
            temp = coord.charAt(0);
            tempx = Character.toString(temp); 
            temp = coord.charAt(1);
            tempy = Character.toString(temp);
            cap = board.move(tempx, tempy, cap);

            winner = board.winnercheck(board.board, p1.nameGet(), p2.nameGet());
            board.boardPrint(p1.choiceGet(), p2.choiceGet(), p1.nameGet(), p2.nameGet(), cap, winner);
    
            if(!winner.contains("noname")){
                helper = true;
            }
            else{
                if(cap<9){
                    coord = scan.nextLine();
                    while(!coord.contains("A1") && !coord.contains("A2") && !coord.contains("A3") && !coord.contains("B1") && !coord.contains("B2") && !coord.contains("B3") && !coord.contains("C1") && !coord.contains("C2") && !coord.contains("C3") && !coord.contains(" ")){
                        System.out.println("wrong coordinates please type again");
                        coord = scan.nextLine();
                    }
                }
            }

        }  
        if(winner.contains("noname") && !coord.contains(" ")){
            System.out.println("lousate den nikhse kaneis");
        }
        else if(coord.contains(" ")){
            System.out.println("Game stopped. No winner.");
        }
        else System.out.println("and the winner iiiiiIIIIIIIIs ....." + winner + "!! LETSFUCKINGOO");
        
    }
}    

 