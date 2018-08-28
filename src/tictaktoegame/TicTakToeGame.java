/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictaktoegame;

import java.util.Scanner;

/**
 *
 * @author Oksana"_Miller
 */
public class TicTakToeGame {

    /**
     * @param nRows
     * @param nColumns
     * @return 
     */
    public static String[][] createEmptyField(int nRows,int nColumns)
    {
        nRows += 1; //creating extra row for horizontal labels
        nColumns +=1; //creating extra row for vertical labels
        
        String[][] strArray = new String[nRows][nColumns];
        
        // creating horizontal cell labels
        for(int nIndex=0, nInnerIndex=1; nInnerIndex < nColumns; nInnerIndex++)
        {
            strArray[nIndex][nInnerIndex] = "  " + nInnerIndex + " "; 
        }
        // creating vertical labels
        for(int nIndex=1, nIndexZero=0; nIndex < nRows; nIndex++)
        {
            strArray[nIndex][nIndexZero] = "" + nIndex;
        }
        //creating the empty field 
        for(int nIndex=1; nIndex < nRows; nIndex++) //Populate an array
        {
            for(int nIndex1=1; nIndex1 < nColumns; nIndex1++)
            { 
                strArray[nIndex][nIndex1] = "[ ] ";
            }
        }
        return strArray;
    }
    
    public static void printTheField(String strArray[][])
    {
         // Printing the array representing the field, excluding element at index [0][0]
        for(int nIndex=0; nIndex < strArray.length; nIndex++) 
        { 
            if(nIndex==0)
            {
                for(int nIndex1=1; nIndex1 < strArray.length; nIndex1++)
                {
                    System.out.print(strArray[nIndex][nIndex1]);
                }
            }
            else
            {
                for(int nIndex1=0; nIndex1 < strArray[0].length; nIndex1++)
                {
                    System.out.print(strArray[nIndex][nIndex1]);
                }
            }
            System.out.println(" ");
        }
    }
    
    public static int[] promptPlayerToMove(int nRow, int nColumn, String strName)
    { 
        int[] nArrayInput = new int [2]; // Creating an array to store player's input;
        boolean bCorrectInput = false;  // flag to determine if the input is coorect.
        String strRow = "";
        String strColumn = "";
        
        while(!bCorrectInput)
        {
            //Getting the user's input
            Scanner obScan = new Scanner(System.in);
            System.out.println("\n" + strName + ",to make a move enter a cell number in the format 'Row/Column' : ");
            String strInput = obScan.nextLine();
        
            // Converting char to String 
            try
            {
                strRow = "" + strInput.charAt(0);
                strColumn = "" + strInput.charAt(2);
            }
            catch (StringIndexOutOfBoundsException e)
            {
                System.out.println
                ("WRONG FORMAT!Use a separator to enter a cell number, like 'Row/Column', where / is a separator");
                continue;
            }
            // Checking for NumberFormatExcepition
            try
            {
                nArrayInput[0] = Integer.parseInt(strRow);// Row#
                nArrayInput[1] = Integer.parseInt(strColumn) ;// Column #
                bCorrectInput = true;
            }
            catch (NumberFormatException e)
            {
                System.out.println
                ("WRONG FORMAT!Enter a cell number in the format 'Row/Column', where Row is # from 1-"+
                 nRow + " and Column is # from 1-" + nColumn);
                bCorrectInput = false; 
                continue;
            }
            // Making sure, the user's input is within the field size
            if(nArrayInput[0]< 1 || nArrayInput[0] > nRow || nArrayInput[1] < 1 || nArrayInput[1] > nColumn)
            {
                 System.out.println
                ("You are out of the field size" + 
                 "\nEnter a cell number in the format 'Row/Column', where Row is # from 1-"+
                 nRow + " and Column is # from 1-" + nColumn);
                 bCorrectInput = false; 
            }
        }
        return nArrayInput;
    }
    public static String collectName(String strPlayer)
    {
       Scanner obScan = new Scanner(System.in);
       System.out.println("Enter a name of the " + strPlayer+ ":");
       String strInput = obScan.nextLine();
       return strInput; 
    }
    public static boolean processInput(int[] nPositionArray, String[][] strField, int nCounter)
    {
        boolean bIsOccupied = false;
        if("[X] ".equals(strField[nPositionArray[0]][nPositionArray[1]]) || "[O] ".equals(strField[nPositionArray[0]][nPositionArray[1]]) )
        {
            System.out.println("This cell is already occupied. Try Again");
        }
        else
        {
            bIsOccupied = true;
            if(nCounter%2 == 0)
            {
                strField[nPositionArray[0]][nPositionArray[1]] = "[O] ";
            }
            else
            {
                strField[nPositionArray[0]][nPositionArray[1]] = "[X] ";
            }
        }
        return bIsOccupied;
    }
   
    public static int findTheWinner(String[][] strArray, int nHorizontalIndex, int nVerticalIndex)
    {
        boolean bEquals = false; // bEquals tells if there is a match
        int nWhoWins = 0;       // 0 - no winner, 1 - "X" wins, 2 - "O" wins.
        
        //Declaring Index variables
        int nInnerIndex;
        int nOuterIndex;
        int nIndex1, nIndex2;
        
        //Cheking for horizontal match 
        for(nInnerIndex=1; nInnerIndex < strArray[nHorizontalIndex].length-1; nInnerIndex++)
        {
            bEquals = ((strArray[nHorizontalIndex][nInnerIndex+1].equals(strArray[nHorizontalIndex][nInnerIndex]))
                                                                &&
           ((strArray[nHorizontalIndex][nInnerIndex].equals("[X] "))||(strArray[nHorizontalIndex][nInnerIndex].equals("[O] "))));
            if(!bEquals)
            {
                break;
            }
        }
        
        if(bEquals) // horizontal match is found
        {
            if(strArray[nHorizontalIndex][nInnerIndex].equals("[X] "))
            {
               nWhoWins = 1;  
            }
            else if(strArray[nHorizontalIndex][nInnerIndex].equals("[O] "))
            {
                nWhoWins = 2;
            }
        }
        else
        {    
            //Cheking for vertical match
            for(nOuterIndex=1; nOuterIndex < strArray.length-1; nOuterIndex++)
            {
                bEquals = ((strArray[nOuterIndex+1][nVerticalIndex].equals(strArray[nOuterIndex][nVerticalIndex]))
                                                              &&
            ((strArray[nOuterIndex][nVerticalIndex].equals("[X] "))||((strArray[nOuterIndex][nVerticalIndex].equals("[O] ")))));
                if(!bEquals)
                {
                    break;
                }
            }
            if(bEquals) // vertical match is found
            {
                if(strArray[nOuterIndex][nVerticalIndex].equals("[X] "))
                {
                    nWhoWins = 1;  
                }
                if(strArray[nOuterIndex][nVerticalIndex].equals("[O] "))
                {
                    nWhoWins = 2;
                }
            }
            else
            {
                //Cheking for left-to-right diagonal match
                for(nIndex1=1,nIndex2=1; nIndex1 < strArray.length-1; nIndex1++, nIndex2++)
                {
                    bEquals = (strArray[nIndex1][nIndex2].equals(strArray[nIndex1+1][nIndex2+1]))
                                                &&
                    ((strArray[nIndex1][nIndex2].equals("[X] ")) || (strArray[nIndex1][nIndex2].equals("[O] ")));
                    if(!bEquals)
                    {
                        break;
                    }
                }
                if(bEquals)// left-to-right diagonal match is found
                {
                    if(strArray[nIndex1][nIndex2].equals("[X] "))
                    {
                        nWhoWins = 1;  
                    }
                    if(strArray[nIndex2][nIndex2].equals("[O] "))
                    {
                        nWhoWins = 2;
                    }   
                }
                else
                {
                    //Cheking for right-to-left diagonal match
                     for(nIndex1=1,nIndex2=strArray.length-1; nIndex1 < strArray.length-1; nIndex1++, nIndex2--)
                    {
                        bEquals = (strArray[nIndex1][nIndex2].equals(strArray[nIndex1+1][nIndex2-1]))
                                                            &&
                        ((strArray[nIndex1][nIndex2].equals("[X] ")) || (strArray[nIndex1][nIndex2].equals("[O] ")));
                        if(!bEquals)
                        {
                            break;
                        }
                    }
                    if(bEquals)// right-to-left diagonal match is found
                    {
                         if(strArray[nIndex1][nIndex2].equals("[X] "))
                        {
                            nWhoWins = 1;  
                        }
                        if(strArray[nIndex1][nIndex2].equals("[O] "))
                        {
                            nWhoWins = 2;
                        }  
                    }
                }
            }
        }
        return nWhoWins;
    }
    
    public static boolean wouldYouLikeToPlayAgain()
    {
        boolean bPlayAgain = true;
        
        Scanner obScanner = new Scanner(System.in);
        System.out.println("Would you like to play again? Enter A to continue or 0 to quit: ");
        String strInput = obScanner.nextLine();
        
        // Validate the input. If after 4 attempts, input is wrong, quit the game
        int nCount;
        for(nCount=0; (nCount < 3) && !(strInput.equals("0")|| strInput.equalsIgnoreCase("A")); nCount++)
        {
            System.out.println("Wrong Input. Enter A to continue or 0(zero) to quit: ");
            strInput = obScanner.nextLine();
        }
        if(strInput.equals("0") || nCount == 3)
        {
            bPlayAgain = false;
        }
        return bPlayAgain;
    }
    
    public static void printStatistics(int nX, int nO, int nTies, String strName1, String strName2)
    {
        System.out.println("\n Thanks for the game! Here is the statistics table:");
        System.out.println("Names:\t" + strName1 + "\t" + strName2);
        System.out.println("Wins:\t" + nX + "\t" + nO);
        System.out.println("Losses:\t" + nO + "\t" + nX);
        System.out.println("Ties: \t" + nTies +"\t" + nTies);
    }
   
    public static void main(String[] args) 
    {
        System.out.println("Welcome to Tik-Tak-Toe Game!");
        // Declaring variablies responsible for the field size
        final int nRows = 3;
        final int nColumns = 3;
        
        // Declaring flag variables
        boolean bPlayAgain = true;// bPlayAgain determines if a user wants to play again
        boolean bBusy;           //  bBusy makes sure the chosen cell is empty.
        int nWinnerFound;       //   nWinnerFound tells who is the winner. 0 - no winner, 1 - "X" wins, 2 - "O" wins.
        
        // Declaring counter variables
        int nStepCount;      // nStepCount is responsible for switching a turn between the players.
                            //  nStepCount also controls that the # of moves is <= # of cells. 
        int nX_Wins = 0;
        int nO_Wins = 0;
        int nTies = 0;

        // Array presents the position entered,  where first element is Row# and the second one is Column# 
        int[] nArrRowColumn = {0,0};
        
        // Collecting players names
        String strPlayer1 = "First Player";
        strPlayer1 = collectName(strPlayer1);
        String strPlayer2 = "Second Player";
        strPlayer2 = collectName(strPlayer2);
        
        // Create and print the initial Field with labels
        System.out.println("\nHi " + strPlayer1+ " and " + strPlayer2 + ". Here is the field you will play in"); 
        String[][] strFields = createEmptyField(nRows,nColumns);
        printTheField(strFields); 
     
        //Game body:
        while(bPlayAgain)
        {
            nWinnerFound = 0;
            nStepCount = 1;
            while(nStepCount <= (nRows*nColumns)&& nWinnerFound == 0)
            {
                bBusy = false; 
            
                while(!bBusy)              
                {
                    if(nStepCount%2 == 0) // If nStepCount is even, O  moves
                    { 
                        nArrRowColumn = promptPlayerToMove(nRows, nColumns, strPlayer2);
                        bBusy = processInput(nArrRowColumn, strFields, nStepCount);
                    } 
                    else                 //  If nStepCount is odd, X moves
                    {
                        nArrRowColumn = promptPlayerToMove(nRows, nColumns, strPlayer1);// arguments are the maximum available values
                        bBusy = processInput(nArrRowColumn, strFields, nStepCount);
                    }
                }
                printTheField(strFields);
                //Check if there is a winner.
                // Arguments help to check ONLY the row and column where there was the last input
                nWinnerFound = findTheWinner(strFields,nArrRowColumn[0],nArrRowColumn[1]);
                nStepCount++;
            }
            // Print the result of the game. 
            switch(nWinnerFound)
            {
                case 1: System.out.println(strPlayer1 + ", Congratulations! You are the winner!");
                nX_Wins += 1;
                break;
            
                case 2: System.out.println(strPlayer2 + ", Congratulations! You are the winner!");
                nO_Wins += 1;
                break;
            
                case 0: System.out.println(strPlayer2 +" and " + strPlayer1 + ", You draw a tie...");
                nTies +=1;
                break;
            
                default: System.out.println("Ooops, smth went wrong...");
                break;
            }
            strFields = createEmptyField(nRows,nColumns); // Clear the field after each round
            bPlayAgain = wouldYouLikeToPlayAgain();
        }
        printStatistics(nX_Wins, nO_Wins, nTies, strPlayer1, strPlayer2);
    }    
}
