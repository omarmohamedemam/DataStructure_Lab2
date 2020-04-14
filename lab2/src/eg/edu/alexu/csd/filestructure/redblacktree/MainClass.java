package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.Map;

public class MainClass {
    public static  void main(String[] args) {
        ITreeMapClass tree = new ITreeMapClass();

        tree.put(2,2);
        tree.put(4,4);
        tree.put(3,3);
        tree.put(9,9);
        tree.put(8,8);
        tree.put(3,3);
        tree.put(5,5);


        System.out.println("Ceiling of key 9 = "+ tree.ceilingKey(9));
        System.out.println("floor of key 5 = "+ tree.floorKey(5));
        System.out.println("Key of First Element ="+ tree.firstKey());
        System.out.println("Key of Last Element ="+ tree.lastKey());













    }
}
