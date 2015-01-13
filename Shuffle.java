import java.io.*;
import java.util.Scanner;
 
class Shuffle {
 
    public static void main(String[] args) throws IOException {
 
        Scanner in = null;
        PrintWriter out = null;
        String line = null;
        String[] token = null;
        int i, n, lineNumber = 0;
        List Comp = new List();;    //compare with Set
        List L = new List();;   //Set
        List P = new List();;  //Permutation
 
        if (args.length < 2) {
            System.out.println("Usage: FileIO infile outfile");
            System.exit(1);
        }
 
        in = new Scanner(new File(args[0]));
        out = new PrintWriter(new FileWriter(args[1]));
 
        while (in.hasNextLine()) {
            lineNumber++;
            line = in.nextLine() + " ";   
            token = line.split("\\s+");  
            n = token.length;
            out.println("Line " + lineNumber + " contains " + n + " token" + (n == 1 ? "" : "s") + ":");
            for (i = 0; i < n; i++) {
                out.println(token[i]);
            }
            out.println();
        }
 
        in.close();
        out.close();
        //fill in input in P
        for (int increment = 0; increment < token.length; increment++) {   
            int temp = Integer.parseInt(token[increment]);
            P.insertBack(temp);
        }
        //fill in L
        for (int incrementL = 1; incrementL < P.getLength(); incrementL++) { 
            L.insertBack(incrementL);
        }
        Comp = L.copy();
        int count = 0;
 
        while (!L.equals(Comp)) {
            shuffle(L, P);
            count++;
        }
        System.out.println("" + L.toString());
    }
 
    static void shuffle(List L, List P) {
        for (int i = 0; i < P.getLength(); i++) {
            int tempL = L.getFront();
            int tempP = P.getFront();
            L.deleteFront();
            P.deleteFront();
 
 
            L.moveTo(L.getLength());
            for (int j = 0; j <= i; j++) {
                L.movePrev();
            }
            for (int k = 0; k < tempP; k++) {
                L.moveNext();
            }
            L.insertAfterCurrent(tempL);
 
        }
    }
}