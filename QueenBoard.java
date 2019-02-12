import java.util.*;
public class QueenBoard{
  public static void main(String[] args){
    //QueenBoard testBoard = new QueenBoard(4);
    // QueenBoard testBoard = new QueenBoard(5);
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
  //  System.out.println(testBoard.solve());
    System.out.println(testBoard.countSolutions());
    System.out.println(testBoard);
    System.out.println(testBoard.toStringDebug());
    try{System.out.println(testBoard.solve());
    }catch(IllegalStateException e){
      System.out.println(testBoard);
      System.out.println("Illegal state exception caught in main");
    }
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
      for (int i = 0; i < board.length; i++){
        if (board[0][i] != 0){
          throw new IllegalStateException();
        }
      }
      return solveH(0);
    }

    /**
    *@return the number of solutions found, and leaves the board filled with only 0's
    *@throws IllegalStateException when the board starts with any non-zero value
    */
    public int countSolutions(){
      for (int i = 0; i < board.length; i++){
        if (board[0][i] != 0){
          throw new IllegalStateException();
        }
      }
      return countSolutionsH(0);
    }
    // helper method takes in the current column and the number of queens left
  private boolean solveH(int colStart){
    // If reached edge of board, check if all queens fit
     if (colStart == board.length){
       return true;
     }
     else{
       // otherwise, loop through each row of the current column
        for (int row = 0; row < board.length; row++){
          // check if can add queen at current row, column and add
          if (addQueen(row, colStart)){
            addQueen(row, colStart);
            // check if can SOLVE the rest of the board
            if (solveH(colStart+1)){
              return true;
            }
            // remove current queen and
            // move down a row from the previous column
            removeQueen(row, colStart);
          }
        }
     }
     return false;
  }
  public static String go(int x,int y){
      return ("\033[" + x + ";" + y + "H");
  }
  //And don't forget you can easily delay the printing if needed:
   public static void wait(int millis){
       try {
           Thread.sleep(millis);
       }
       catch (InterruptedException e) {
       }
   }
  // helper method takes in the current column and the number of queens left
private int countSolutionsH(int colStart){
/*  System.out.println(QueenBoard.go(1,1));
  System.out.println(this);
  QueenBoard.wait(1000);*/
  // If reached edge of board, check if all queens fit
   if (colStart == board.length){
     return 1;
   }
     // otherwise, loop through each row of the current column
      for (int row = 0; row < board.length; row++){
        System.out.println("row "+row+ " colSTart " +colStart);
        // check if can add queen at current row, column and add
        if (addQueen(row, colStart)){
          System.out.println("added queen to "+ row + " " + colStart);
          // check if can SOLVE the rest of the board
          if (solveH(colStart+1)){
              System.out.println("solved for queen");
              // removeQueen(row, colStart);
             return 1 + countSolutionsH(colStart+1);

          }
          // remove current queen and
          // move down a row from the previous column
            removeQueen(row, colStart);
      }
   }
   return 0;
}

// add queen at specified position and mark her targets
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
  // remove queen at specified position and her marked targets
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
 // clear and return board to original state
 private void clear(){
   for (int i = 0; i < board.length; i++){
     for (int j = 0; j < board[0].length; j++){
       board[i][j] = 0;
     }
   }
 }

}
