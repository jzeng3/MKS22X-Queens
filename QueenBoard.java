import java.util.*;
public class QueenBoard{
  public static void main(String[] args){
    for (int i = 0; i < 15; i++){
      QueenBoard testBoard = new QueenBoard(i);
      System.out.println("GENERATED " + i + " X " + i + " BOARD");
      System.out.println("SOLVEABLE: " + testBoard.solve());
      System.out.println(testBoard);
    }
    System.out.println("NUMBER OF SOLUTIONS FOR");
    for (int i = 0; i < 15; i++){
      QueenBoard testBoard = new QueenBoard(i);
      System.out.println(i + " X " + i + " BOARD: " + testBoard.countSolutions());
    }
  }


  private int[][] board;
  // Constructor makes a board of given size (N x N)
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

  // prints out the number values of each element of the board
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

  // helper method takes in the current column
  private boolean solveH(int colStart){
    if (board.length == 4){
    System.out.println(QueenBoard.go(20,1));
    System.out.println(this);
    QueenBoard.wait(500);
  }
    // If reached edge of board, return true (board is solved)
    if (colStart == board.length){
      return true;
    }
    else{
      // otherwise, loop through each row of the current column
      for (int row = 0; row < board.length; row++){
        // check if can add queen at current row, column and add
        if (addQueen(row, colStart)){
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
    // return false if board cannot be solved
    return false;
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

  // helper method takes in the current column
  private int countSolutionsH(int colStart){
    // If reached edge of board, solution counts as 1
    if (colStart == board.length){
      return 1;
    }
    // local variable to keep track of number of solutions
    int numSolutions = 0;
    // otherwise, loop through each row of the current column
    for (int row = 0; row < board.length; row++){
      // check if can add queen at current row, column and add
      if (addQueen(row, colStart)){
        // update the number of solutions with recursive call on next column
        numSolutions += countSolutionsH(colStart+1);

      }
      // remove current queen and
      // move down a row from the previous column
      removeQueen(row, colStart);
    }
    // return the total number of solutions
    return numSolutions;
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
      // return true if queen was successfully added, false otherwise
      return true;
    }
    return false;

  }
  // remove queen at specified position and her marked targets
  private boolean removeQueen(int r, int c){
    // check if there is a queen at this position
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
      // return true if queen was successfully removed, false otherwise
      return true;
    }
    return false;
  }
  // go and wait taken from Mr. K's website, for debugging
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
}
