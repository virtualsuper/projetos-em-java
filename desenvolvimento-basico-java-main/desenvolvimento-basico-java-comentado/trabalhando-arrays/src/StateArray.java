import java.util.Arrays;

public class StateArray {
  public static void main(String[] args) {
    int[] meuArray = new int[7];
    int[] otherArray = {12, 32, 54, 6, 8, 89, 64};

    System.out.println(Arrays.toString(meuArray));

    System.out.println(Arrays.toString(otherArray));

    otherArray[0] = 50;

    System.out.println(Arrays.toString(otherArray));

  }
}
