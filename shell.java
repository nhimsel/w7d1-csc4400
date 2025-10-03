class Main {
    public static void main(String[] args) {
            
    }

    private static int[] RandomizedArray(int size, int start, int end) {
        if (size > end-start+1) {
            System.out.println("there are enough unique values between "+start+
                    " and "+end+" to get "+size+" unique vlaues.");
            int[] nullarr = {};
            return nullarr;
        }

        int[] arr = new int[end-start+1];

        for (int i=0; i<arr.length; i++) {
            arr[i] = start+i;
        }

        for (int i=0; i<arr.length-1; i++) {
            int tval = arr[i];
            int tindex = (int) (Math.random() * (arr.length));
            arr[i] = arr[tindex];
            arr[tindex] = tval;
        }
        int[] result = new int[size];
        for (int i=0; i<size; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    private static void printArr(int[] arr) {
        for (int i:arr) {
            System.out.print(i+"//");
        }
        System.out.println();
    }

    private static boolean checkSort(int[] arr, boolean ascending) {
        if (ascending) {
            for (int i=1; i<arr.length; i++)
                if (arr[i-1] > arr[i]) return false;
        }
        else{
            for (int i=1; i<arr.length; i++)
                if (arr[i-1] < arr[i]) return false;
        }
        return true;
    }

}

class Sort {
    static int[] Shell(int[] arr, int[] intervals) {
    }

    static int[] Shell(int[] arr) {
    }

    static int[] Insertion(int[] arr) {
        int key, tpos;
        for (int i=1; i<arr.length; i++) {
            key = arr[i];
            for (tpos=i-1; tpos>=0; tpos--) {
                if (arr[tpos]>key) {
                   arr[tpos+1]=arr[tpos];
                }
                else break;
            }
            arr[tpos+1] = key;
        }
        return arr;
    }
}
