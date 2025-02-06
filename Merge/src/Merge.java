public class Merge {

    public static void sort(Comparable[] a){

        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length -1);
    }

    private static void sort(Comparable[] a,Comparable[] aux, int lo, int hi){

        //base case
        if(hi <= lo){
            return;
        }
        int mid = lo + (hi-lo)/2;
        sort(a, aux,lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid ,hi);
    }
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){

        //copy to aux array
        for(int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }

        //System.arraycopy(a, lo, aux, lo, hi-lo+1)
        int i = lo ,  j = mid +1;

        //merge back to a[]
        for(int k = lo; k <= hi; k++){
            if(i > mid){
                a[k] = aux[j++];
            }
            else if (j > hi){
                a[k] = aux[i++];
            }
            else if(less(aux[i], aux[j])){
                a[k] = aux[i++];
            }
            else{
                a[k] = aux[j++];
            }
        }


    }
    private static boolean less(Comparable v , Comparable w){
        return (v.compareTo(w) < 0);
    }

    private static void exch(Comparable[] a , int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    private static boolean isSorted(Comparable[] a, int lo, int hi){
        for(int i = 1; i < a.length; i++){
            if(less(a[i], a[i-1])){
                return false;
            }
        }
        return true;
    }

    public static void show(Comparable[] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");

        }
        System.out.println("");
    }
}
