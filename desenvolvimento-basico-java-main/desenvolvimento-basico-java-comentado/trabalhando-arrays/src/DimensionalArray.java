public class DimensionalArray {
  public static void main(String[] args) {
    int[][] myArrayMulti = {{1, 2, 3, 4}, {5, 6, 7}};
    myArrayMulti = new int[][]{{1, 2, 3, 4}, {53, 63, 73}};

    /* Forma extensa */
    /*
      for (int i = 0; i < myArrayMulti.length; i++) {
        for (int j = 0; j < myArrayMulti[i].length; j++) {
          System.out.println(myArrayMulti[i][j]);
        }
      }
    */

    /* Forma reduzida */
    for (int[] ints : myArrayMulti) {
      for (int anInt : ints) {
        System.out.println(anInt);
      }
    }
  }
}
