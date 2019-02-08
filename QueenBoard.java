import java.util.*;
public class QueenBoard{
  public static void main(String[] args){
    QueenBoard testBoard = new QueenBoard(2);
    System.out.println(testBoard);
    testBoard.addQueen(0,0);
    System.out.println(testBoard);
    testBoard.removeQueen(0,0);
    System.out.println(testBoard);
    testBoard.solve();
    System.out.println(testBoard.solve());
    System.out.println(testBoard);
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


    /**
    *@return false when the board is not solveable and leaves the board filled with zeros;
    *        true when the board is solveable, and leaves the board in a solved state
    *@throws IllegalStateException when the board starts with any non-zero value
    */
    public boolean solve(){
      return solveH(0, 0, 0);
    }

    /**
    *@return the number of solutions found, and leaves the board filled with only 0's
    *@throws IllegalStateException when the board starts with any non-zero value
    */
    public int countSolutions(){
      return 0;
    }
  private boolean solveH(int row, int column, int numQueens){
    // return true if all queens fit on board
     if (row == board.length && numQueens == board.length){
       return true;
     }
     // else loop through each row
     else{
       for (int i = 0; i < board.length && column < board[0].length; i++){
         if (board[i][column] == 0){
           addQueen(row, column);
           solveH(row + 1, column + 1, numQueens + 1);
         }
         else{
           solveH(row, column + 1, numQueens);
         }
       }
     }
     return false;
  }

  private boolean addQueen(int r, int c){
    board[r][c] = -1;
    // when queen is added, mark the spots that it could target
    for (int i = 0; i < board.length; i++ ){
      for (int j = 0; j < board[0].length; j++){
        if (!(r == i && c == j) && (r == i || c == j || Math.abs(r-i) == Math.abs(c-j))){
          board[i][j] += 1;
        }
      //  System.out.print(board[i][j] + ", ");
      }
    }
    return true;
  }
  private boolean removeQueen(int r, int c){
    board[r][c] = 0;
    for (int i = 0; i < board.length; i++ ){
      for (int j = 0; j < board[0].length; j++){
        if (!(r == i && c == j) && (r == i || c == j || Math.abs(r-i) == Math.abs(c-j))){
          board[i][j] -= 1;
        }
      //  System.out.print(board[i][j] + ", ");
      }
    }
    return true;
  }

}
