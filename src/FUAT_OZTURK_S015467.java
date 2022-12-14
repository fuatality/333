
// Java program for the above approach
import java.util.Scanner;
import java.util.Arrays;
class FUAT_OZTURK_S015467 {

    //did not want to use Math.max()
    public static int max(int a, int b)
    {
        return (a > b)? a : b;
    }

    //the method to find and print the uncrossed wires and how many of them
    public static void uncrossedWires(String[] a, String[] b, int aLength, int bLength) {

        int[][] lengthOfLCS = new int[aLength + 1][bLength + 1];

        for (int i = 0; i <= aLength; i++) {
            for (int j = 0; j <= bLength; j++) {
                if (i == 0 || j == 0) {
                    lengthOfLCS[i][j] = 0; //if they are equal we update the value
                }else if (a[i - 1].equals(b[j - 1])) {
                    lengthOfLCS[i][j] = 1 + lengthOfLCS[i - 1][j - 1]; //if they are not
                    // we update the length of longest common subsequence
                }else {
                    lengthOfLCS[i][j] = max(lengthOfLCS[i - 1][j], lengthOfLCS[i][j - 1]); //updating the array
                }
            }
        }
        int value = lengthOfLCS[aLength][bLength];
        int tmp = value;

        String[] longestCommonSubsequence = new String[value + 1];
        int i = aLength;
        int j = bLength;
        while (i > 0 && j > 0) {
            if (a[i - 1].equals(b[j - 1])) {
                longestCommonSubsequence[value - 1] = a[i - 1];
                i--;
                j--;
                value--;
            }
            else if (lengthOfLCS[i - 1][j] > lengthOfLCS[i][j - 1])
                i--;
            else
                j--;
        }

        for (int k = 0; k <= tmp -1; k++) {
            System.out.print(longestCommonSubsequence[k]+" ");
        }
        System.out.println();
        System.out.println("The number of uncrossed wires is: "+ lengthOfLCS[aLength][bLength]);
    }

    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter lamps and outlets number: ");
        int length = sc.nextInt();
        String[] outlet = new String[length];
        String[] lamps=new String[length];
        System.out.println("Please enter outlet hexadecimals one by one(top down): ");
        for (int i = 0; i < length; i++) {
            String userInput = sc.next();
            outlet[i] = userInput;
        }
        System.out.println("Please enter lamp hexadecimals one by one(top down): ");
        for (int i = 0; i < length; i++) {
            String userInput = sc.next();
            lamps[i] = userInput;
        }
        System.out.println("The input from user is : ");
        System.out.println("Outlets: "+Arrays.toString(outlet));
        System.out.println("Lamps:   "+Arrays.toString(lamps));

        uncrossedWires(outlet, lamps, outlet.length, lamps.length);
    }
}
