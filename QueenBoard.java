public class QueenBoard{
  public static void main(String[] args){
    QueenBoard testBoard = new QueenBoard(5);
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
          if (board[i][j] == 0){
            boardStr += "_";
          }
          else{
            boardStr += "Q";
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
      return false;
    }

    /**
    *@return the number of solutions found, and leaves the board filled with only 0's
    *@throws IllegalStateException when the board starts with any non-zero value
    */
    public int countSolutions(){
      return 0;
    }

  private boolean addQueen(int r, int c){
    return true;
  }
  private boolean removeQueen(int r, int c){
    return true;
  }

}
