package com.company;

import java.util.*;

/**
 * Sure thing
 * Created by _kbluue_ on 10/3/2017.
 */
public class HKR {

    static class inner{
        private class Private{
            void print(){
                System.out.println("Inner Class Accessed");
            }
        }
    }

    void callInner(){
        new inner().new Private().print();
    }

    void multiIn(int... ins){
        if(ins.length == 0){
            System.out.println("No input provided");
        } else {
            int sum = 0;
            for (int a : ins) {
                sum += a;
            }
            System.out.println(("Sum: " + sum + "\nn: " + ins.length + "\nAverage:" + (((double) sum) / ins.length)));
        }
    }

    static String cutString(String string,String cut){
        String out[] = string.split(cut, 2);
        return out[0]+out[1];
    }

    public int numberNeeded(String first, String second) {
        String control = (first.length() > second.length()) ? second : first;
        String other = (first.equals(control)) ? second : first;
        for (int i = 0; i < control.length(); i++) {
            String charr = control.substring(i, i+1);
            if (other.contains(charr)){
                i--;
            }
        }
        return control.length() + other.length();
    }

    boolean arrayContain(Object[] array,Object obj){
        for (Object piece : array) if (piece.equals(obj)) return true;
        return false;
    }

    Object[] arraySwap(Object[] array,Object old,Object obj){
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(old)) {
                array[i] = obj;
                break;
            }
        }
        return array;
    }

    boolean mapMatch(){
        Scanner in = new Scanner(System.in);
        Map<String, Integer> magazine = new HashMap<>();
        in.nextLine();
        String magazineS = in.nextLine();
        String ransomS = in.nextLine();
        for (String word : magazineS.split(" ")) {
            if (magazine.get(word) == null) magazine.put(word, 1);
            else magazine.put(word, magazine.get(word) + 1);
        }
        for (String word : ransomS.split(" ")){
            if (magazine.get(word) == null || magazine.get(word) == 0) return false;
            magazine.put(word, magazine.get(word) - 1);
        }
        return true;
    }

    public static boolean isBalanced(String expression) {
        String openers = "{[(", closer = "}])", control = "";
        for (String s : expression.split("")) {
            if (openers.contains(s)) control += s;
            else {
                if (control.length() == 0 || control.charAt(control.length() - 1) != openers.charAt(closer.indexOf(s))) return false;
                else control = control.substring(0, control.length() - 1);
            }
        }
        return control.length() == 0;
    }

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    boolean checkBST(Node root) {
        return checkRoot(root) && checkBST(root.right) && checkBST(root.left);
    }

    boolean checkRoot(Node root){
        if (root == null) return true;
        boolean b1 = true, b2 = true;
        if (root.left != null) b1 = root.data > root.left.data;
        if (root.right != null) b2 = root.data < root.right.data;
        return b1 && b2;
    }

    double runningMedian(ArrayList<Integer> in){
        boolean even = in.size()%2 == 0;
        int midpoint = in.size()/2;
        return !even ? in.get(midpoint) : (in.get(midpoint) + in.get(midpoint-1))/2.0;
    }

    ArrayList<Integer> insert(ArrayList<Integer> arr,int in){
        int r1 = 0, r2 = arr.size(); //ranges
        if (r2 == 0 || in > arr.get(arr.size() - 1)) {
            arr.add(in);
            return arr;
        }
        if (r2 == 1 || in < arr.get(0)){
            arr.add((in < arr.get(0)) ? 0:1, in);
            return arr;
        }
        int r;
        while (true){
            r = (r1 + r2)/2;
            if (arr.get(r) >= in && arr.get(r-1) <= in){
                arr.add(r, in);
                break;
            }
            if (arr.get(r) > in) r2 = r;
            else r1 = r;
        }
        return arr;
    }

    static void phonebook(String op, String contact,List<String> list, Map<String, Integer> map){
        if (op.equals("add")) {
            int pos = Math.abs(Collections.binarySearch(list, contact)) - 1;
            if (pos < 0) pos = 0;
            list.add(pos, contact);
            Integer i = map.get(contact.substring(0,1));
            map.put(contact.substring(0,1),(i == null) ? 1 : i+1);
            return;
        }
        if (op.equals("find")){
            Integer checks = map.get(contact.substring(0,1));
            if (checks == null) {
                System.out.println(0);
                return;
            }
//            System.out.println(checks);
            int start = Math.abs(Collections.binarySearch(list, contact.substring(0,1))) - 1;
            if (start < 0) start = 0;
            int count = 0;
            System.out.println(start + checks + " " + list.size());
            for (int i = start; i < start+checks; i++) {
                String con = list.get(i);
//                System.out.println(con);
                if (con.length() < contact.length()) continue;
                if (contact.equals(con.substring(0, contact.length()))) count++;
            }
//            System.out.println(list.toString());
            System.out.println(count);
        }
    }

    static class Player{
        String name;
        int score;

        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    static class Checker implements Comparator<Player>{

        @Override
        public int compare(Player o1,Player o2) {
            if (o1.score != o2.score) return o2.score - o1.score;
            return o1.name.compareTo(o2.name);
        }
    }

    static long BubbleSortOptimized(int arr[]) {
        long totalSwaps = 0, swapsInCycle;

        int iteration = 0, start = 0, stop = arr.length - 1, control = 0;
        boolean ordered, nsCaught;
        while (true){
            swapsInCycle = 0;
            ordered = true;
            nsCaught = false;
            for (int i = start; i < stop; i++) {
                iteration++;
                if (i > 1) {
                    if (!nsCaught && arr[i-2] > arr[i-1]){
                        ordered = false;
                        start = i-2;
                        nsCaught = true;
                    }
                }
                if (arr[i] > arr[i+1]){
                    int hold = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = hold;
                    swapsInCycle++;
                    control = i;
                }
            }
            System.out.println(Arrays.toString(arr));
            if (ordered) {
                System.out.println("iteration: " + iteration);
                return totalSwaps + swapsInCycle;
            }
            totalSwaps += swapsInCycle;
            stop = control;
        }
    }

    int[] myMergeSort(int[] in){
        int len = in.length;
        //merge using a level counter
        int level = 1;
        long out = 0;
        while (level < len){
//            System.out.println(Arrays.toString(in));
            for (int i = 0; i < (len - level);) { //used to ignore the end part of the array that is already sorted
                int a = i, b = i + level, hold[] = new int[(b + level > len) ? len - i : (2 * level)]; //hold array created to store already sorted sub sections
                for (int j = 0; j < hold.length; j++) { //comparing pairs on each level
                    if (in[a] <= in[b]){
                        hold[j] = in[a];
                        a++;
                        if (a >= (i + level)) {
                            hold = fill(hold, in, j + 1, b);
                            break;
                        }
                    } else {
                        out++;
                        hold[j] = in[b];
                        b++;
                        if (b >= (i + level + level) || b >= len) {
                            hold = fill(hold, in, j + 1, a);
                            break;
                        }
                    }
                }

                for (int value : hold) in[i++] = value; // returning hold content into array
                System.out.println(Arrays.toString(hold) + "..." + i + "..." + level + "..." + len);
            }
            level = 2 * level; // initiating for next level
        }

//        return out;
        return in;
    }

    /***
    * Created to fill up the hold array with already sorted part of the corresponding subsection when the content of a subsection is used up
     *
    ***/
    private int[] fill(int[] hold, int[] in, int holdStart, int inStart) {
        for (int i = holdStart; i < hold.length; i++) {
            hold[i] = in[inStart++];
        }
        return hold;
    }

//    void testMySort(){
//        for (int i = 1; i < 7000; i++) {
//            int test[] = new int[i];
//            for (int j = 0; j < test.length; j++) {
//                test[j] = (int)(Math.random() * 111111);
//            }
//            test = myMergeSort(test);
//            System.out.println(Arrays.toString(test));
//            System.out.println(isSorted(test) + "..." + i);
//        }
//    }

    private boolean isSorted(int[] in){
        for (int i = 0; i < in.length - 1; i++) {
            if (in[i + 1] < in[i]) return false;
        }
        return true;
    }

    public int getBiggestRegion(int[][] matrix) {
        int out = 0;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (visited[i][j] || matrix[i][j] == 0) continue;
                visited[i][j] = true;
                Object[] hold = getNeighbours(matrix, visited, i, j);
                visited = (boolean[][]) hold[0];
                System.out.println(hold[1]);
                out = ((int) hold[1] > out) ? (int)hold[1] : out;
            }
        }
        return out;
    }

    private Object[] getNeighbours(int[][] matrix,boolean[][] visited,int X,int Y){
        int neighbours = 0, pos[][] = new int[][]{{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}};
        for (int i = 0; i < 8; i++) {
//            System.out.println(Arrays.deepToString(visited));
            int x = X - pos[i][0], y = Y - pos[i][1];
            if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) continue;
            if (!visited[x][y] && matrix[x][y] == 1){
                visited[x][y] = true;
                System.out.printf("X: %d, Y: %d\n", x, y);
                Object[] hold = getNeighbours(matrix, visited, x, y);
                visited = (boolean[][]) hold[0];
                neighbours += ((int) hold[1]);
            }
            visited[x][y] = true;
        }
        return new Object[]{visited, neighbours + 1};
    }

    class IceCream implements Comparable {
        int flavor;
        int index;

        public IceCream(int flavor, int index) {
            this.flavor = flavor;
            this.index = index;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof IceCream) return this.flavor - ((IceCream) o).flavor;
            else {
                try {
                    throw new Exception("Illegal Object");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof IceCream) return ((IceCream) o).flavor == this.flavor;
            else {
                try {
                    throw new Exception("Illegal Object");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        }
    }

    void IcecreamPair(){
        int t;
        int n, money;

        Scanner in = new Scanner(System.in);
        t = in.nextInt();
        for(int test = 0; test < t; test++) {

            money = in.nextInt();
            n = in.nextInt();
            IceCream[] arr = new IceCream[n];

            for (int i = 0; i < n; i++)
                arr[i] = new HKR().new IceCream(in.nextInt(), i + 1);

            Arrays.sort(arr);

            for (int i = 0; i < arr.length; i++) {
                boolean breaker = false;
                for (int j = arr.length-1; j > i; j--) {
                    if (arr[i].flavor + arr[j].flavor == money){
                        System.out.printf("%d(%d) + %d(%d) --> %d\n", arr[i].index, arr[i].flavor, arr[j].index, arr[j].flavor, money);
                        breaker = true;
                    }
                    if (breaker) break;
                }
                if (breaker) break;
                if (i >= arr.length) System.out.println("No answer");
            }
        }
    }

    int lonelyInteger(int[] in){
        Arrays.sort(in);
        System.out.println("sorted");
        for (int i = 0; i < in.length - 1; i+=2) {
            if (in[i] != in[i+1]) return in[i];
            System.out.println(i);
        }
        return in[in.length - 1];
    }

    private boolean isPrime(int number){
        if (number < 4) return true;
        for (int i = 2; i < (number / i) + 1; i++) {
            if (number%i == 0) return false;
        }
        return true;
    }

    private boolean isPrime(int number,boolean sure){
        if (!sure) return isPrime(number);
        if (number < 4) return true;
        for (int i = 2; i < number; i++) {
            if (number%i == 0) return false;
        }
        return true;
    }

    void printIsPrime(int number){
        System.out.println(isPrime(number) ? "Prime" : "Not prime");
    }

    void printAllPrint(int range){
        for (int i = 1; i <= range; i++) {
//            if (isPrime(i, true)) System.out.println(i);
            isPrime(i, false);
        }
    }

    void compare(){
        int true1[] = new int[169];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 169; i++) {
            true1[i] = in.nextInt();
        }
        for (int i = 0; i < 169; i++) {
            if (true1[i] != in.nextInt()) System.out.println(true1[i]);
        }
        System.out.println("Complete");
    }

    long DR(int n){
        if (n < 3) return n;
        if (n == 3) return 4;
        return DR(n - 1) + DR(n - 2) + DR(n - 3);
    }

    long DR2(int n){
        if (n < 3) return n;
        if (n == 3) return 4;
        if (n <= 6) return DR(n);
        n -= 3;
        long[] coff = new long[n];
        coff[0] = 1;
        coff[1] = 2;
        coff[2] = 4;
        for (int i = 3; i < coff.length; i++) {
            coff[i] = coff[i - 1] + coff[i - 2] + coff[i - 3];
        }
        return ((4*coff[n-1]) + (2*(coff[n-2] + coff[n-3])) + (coff[n-2]));
    }

    long DR3(int n){
        if (n < 3) return n;
        if (n == 3) return 4;
        long coff[] = new long[]{1,2,4};
        int i = 3;
        while (true) {
            coff[i++ % 3] = coff[0] + coff[1] + coff[2];
            if (i >= n) return coff[--i % 3];
        }
    }

    long DR4(int n){
        if (n < 3) return n;
        if (n == 3) return 4;
        long coff[] = new long[]{1,2,4};
        for (int i = 3; i < n; i++) {
            coff[i%3] = coff[0] + coff[1] + coff[2];
        }
        return coff[(n - 1) %3];
    }

    public static void main(String[] args) {
//        int sum = 0;
//        for (int i = 0; i < 10; i++) {
//            long startTime = System.nanoTime();
//            new HKR().printAllPrint(1000);
//            sum += System.nanoTime() - startTime;
//        }
//        System.out.println(sum);
//        new HKR().printAllPrint(1000);
//        new HKR().compare();
        int n = 1;
        while (true){
            long startTime = System.nanoTime();
            long ans = new HKR().DR4(n);
            long timeTaken = System.nanoTime() - startTime;
            int secs = (int) (timeTaken/1000000000);
            System.out.println(ans + " --> " + n + " --> " + (secs/60) + ":" + (secs%60)+ "." + ((timeTaken/10000000) % 100));
            n++;
            if (secs > 300) break;
        }
//        System.out.println(new HKR().DR2(7));
    }
}
