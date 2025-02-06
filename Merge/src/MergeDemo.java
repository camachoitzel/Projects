public class MergeDemo {
    public static void main(String[] args) {
        Integer[] nums = {12,7,9,3,5,2,10,8,10,1,4,6,11};

        Merge.sort(nums);
        Merge.show(nums);

        for(int i = 0; i < nums.length; i++){
            System.out.print(nums[i] + " ");
        }
        System.out.println("");
    }
}
