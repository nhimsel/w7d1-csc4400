class Main {
    public static void main(String[] args) {
        int arrsize = 100000;
        int[] arr = RandomizedArray(arrsize, -500000, 1000000); 

        int[] quickarr = arr.clone();
        int[] shellarr = arr.clone();
        
        long quickstart = System.currentTimeMillis();
        Sort.Quick(quickarr);
        long quickfin = System.currentTimeMillis();

        long quicktime = quickfin-quickstart;
        System.out.println("algorithm: quick\ttime: "+quicktime+"ms\tarraysize:"+arrsize);
        System.out.println("the array was sorted correctly:"+checkSort(quickarr));

        long shellstart = System.currentTimeMillis();
        Sort.Shell(shellarr);
        long shellfin = System.currentTimeMillis();

        long shelltime = shellfin-shellstart;
        System.out.println("algorithm: shell\ttime: "+shelltime+"ms\tarraysize:"+arrsize);
        System.out.println("the array was sorted correctly:"+checkSort(shellarr));
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

    private static boolean checkSort(int[] arr) {
        for (int i=1; i<arr.length; i++)
            if (arr[i-1] > arr[i]) return false;
        return true;
    }
}

class Sort {
    static void Quick(int[] arr, int lo, int hi) {
        if (hi<=lo)
            return;

        int olo=lo,ohi=hi;
        int piv = arr[lo];
        while (lo<=hi) {
            while (lo<=hi && arr[lo]<piv) 
                lo++;
            while (lo<=hi && arr[hi]>piv)
                hi--;
            if(lo>hi)
                break;
            else{
                int tmp=arr[lo];
                arr[lo]=arr[hi];
                arr[hi]=tmp;
                lo++;
                hi--;
            }
        }
        Quick(arr,olo,hi);
        Quick(arr,lo,ohi);
        return;
    }

    static int[] Quick(int[] arr) {
        Quick(arr, 0, arr.length-1);
        return arr;
    }

    static int[] Shell(int[] arr, int[] intervals) {
        //check that the intervals are legal
        if (intervals[intervals.length-1]!=1) {
            System.out.println("the intervals do not end in 1. bailing");
            return arr;
        }
        else {
            for (int i=0; i<intervals.length-1; i++) {
                if (intervals[i]<=intervals[i+1]) {
                    System.out.println("the intervals are not in descending order. bailing");
                    return arr;
                }
            }
        }

        //loop through all intervals
        for (int i=0; i<intervals.length; i++) {
            int offset = 0;
            while (offset < intervals[i]) {
                // make the temp arr
                int tmpsize = arr.length / intervals[i];
                //adjust size if there is one more possible value
                if ((arr.length)>tmpsize*intervals[i]+offset) tmpsize++; 
                int[] tmp = new int[tmpsize];
                //fill tmp
                for (int j=0; j<tmpsize; j++)
                    tmp[j]=arr[intervals[i]*j+offset];
                //sort the tmp arr
                Insertion(tmp);
                //fill the sorted vals back into arr
                for (int j=0; j<tmpsize; j++)
                    arr[intervals[i]*j+offset]=tmp[j];
                offset++;
            }
        }
        return arr;
    }

    static int[] Shell(int[] arr) {
        int n = 0; 
        //determine max interval
        while (Math.pow(2.0, (double)++n)<arr.length);
        int[] intervals = new int[n--];
        //fill interval arr
        for (int i=0; i<=n; i++)
            intervals[i]=(int)Math.pow(2.0,(double)(n-i));
        //shell sort with the intervals
        Shell(arr, intervals);
        return arr;
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
