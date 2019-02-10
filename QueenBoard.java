import java.util.*;
public class QueenBoard{
  public static void main(String[] args){
    QueenBoard testBoard = new QueenBoard(4);
  /*  System.out.println(testBoard);
    testBoard.addQueen(0,0);
    System.out.println(testBoard);
    testBoard.removeQueen(0,0);
    System.out.println(testBoard);
    testBoard.solve();*/
  /*  testBoard.addQueen(1,2);
    System.out.println(testBoard.toStringDebug());
    testBoard.removeQueen(1,2);
    System.out.println(testBoard.toStringDebug());*/
    System.out.println(testBoard.solve());
    System.out.println(testBoard);
    System.out.println(testBoard.toStringDebug());
  }

  private int[][] board;

  public QueenBoard(int size){
      board = new int[size][size];
  }
  /**
    *@return The output string formatted as follows:
    *All numbers that represent queens are replaced with 'Q'
    *all others are displayed as underscores '_'
    *There are spaces between each symbol:
    *"""_ _ Q _
    *Q _ _ _
    *_ _ _ Q
    *_ Q _ _"""
    *(pythonic string notation for clarity,
    *excludes the character up to the *)
    */
    public String toString(){
      String boardStr = "";
      for (int i = 0; i < board.length; i++){
        for (int j = 0; j < board[0].length; j++){
          if (board[i][j] >= 0){
            boardStr += "_ ";
          }
          else if (board[i][j] == -1){
            boardStr += "Q ";
          }
          if (j == board[0].length -1){
            boardStr += "\n";
          }
        }
      }
      return boardStr;
    }

    public String toStringDebug(){
      String boardStr = "";
      for (int i = 0; i < board.length; i++){
        for (int j = 0; j < board[0].length; j++){
            boardStr += board[i][j] + " ";
          if (j == board[0].length -1){
            boardStr += "\n";
          }
        }
      }
      return boardStr;
    }


    /**
    *@return false when the board is not solveable and leaves the board filled with zeros;
    *        true when the board is solveable, and leaves the board in a solved state
    *@throws IllegalStateException when the board starts with any non-zero value
    */
    public boolean solve(){
      return solveH(0, 0, board.length);
    }

    /**
    *@return the number of solutions found, and leaves the board filled with only 0's
    *@throws IllegalStateException when the board starts with any non-zero value
    */
    public int countSolutions(){
      return 0;
    }
  private boolean solveH(int rowStart, int colStart, int numQueensLeft){
    // return true if all queens fit on board
    if (colStart >= board.length){
      System.out.println("base case");
      return numQueensLeft == 0;
    }
     // else loop through each row
     else{
       if (addQueen(rowStart, colStart)){
         System.out.println("can add queen at "+ rowStart+", "+ colStart);
         System.out.println("solveH at "+ 0+", "+ colStart+"+1, numQueensLeft (do -1) " +numQueensLeft);
      return solveH(0, colStart + 1, numQueensLeft - 1);
       }
      else{
        if (rowStart < board.length - 1){
          removeQueen(rowStart, colStart);
        System.out.println("remove queen at "+ rowStart+", "+ colStart);

           System.out.println("solveH at "+ rowStart+" +1, "+ colStart+", numQueensLeft " +numQueensLeft);
           return solveH(rowStart + 1, colStart, numQueensLeft);
         }
        
      }
       }
       return false;
  }

  private boolean addQueen(int r, int c){
    // check if there is a queen or target, then add queen and targets
    if (board[r][c] == 0){
      // clear queen
      board[r][c] = -1;
      for (int i = 0; i < board[r].length; i++){
        // this row, excluding queen
        if (i != c){
          board[r][i] += 1;
        }
        // back a row, back a column
        if ((r - i >= 0 && c - i >= 0) && board[r - i][c - i] >= 0){
          board[r - i][c - i] += 1;
        }
        // back a row, forward a column
        if ((r - i >= 0 && c + i < board.length) && board[r - i][c + i] >= 0){
          board[r - i][c + i] += 1;
        }
        // forward a row, back a column
        if ((r + i < board.length && c - i >= 0) && board[r + i][c - i] >= 0){
          board[r + i][c - i] += 1;
        }
        // forward a row, forward a column
        if ((r + i < board.length && c + i < board.length) && board[r + i][c + i] >= 0){
          board[r + i][c + i] += 1;
        }
      }

    return true;
   }
   return false;

  }
  private boolean removeQueen(int r, int c){
    // check if there is a queen
    if (board[r][c] == -1){
      // clear queen
      board[r][c] = 0;
      for (int i = 0; i < board[r].length; i++){
        // this row, excluding queen
        if (i != c){
          board[r][i] -= 1;
        }
        // back a row, back a column
        if ((r - i >= 0 && c - i >= 0) && board[r - i][c - i] > 0){
          board[r - i][c - i] -= 1;
        }
        // back a row, forward a column
        if ((r - i >= 0 && c + i < board.length) && board[r - i][c + i] > 0){
          board[r - i][c + i] -= 1;
        }
        // forward a row, back a column
        if ((r + i < board.length && c - i >= 0) && board[r + i][c - i] > 0){
          board[r + i][c - i] -= 1;
        }
        // forward a row, forward a column
        if ((r + i < board.length && c + i < board.length) && board[r + i][c + i] > 0){
          board[r + i][c + i] -= 1;
        }
      }

    return true;
   }
   return false;
 }

}
