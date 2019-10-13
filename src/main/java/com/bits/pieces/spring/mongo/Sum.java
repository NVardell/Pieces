package com.bits.pieces.spring.mongo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 10/9/2019
 */
final public class Sum {
//    private static Sum s = new Sum();
//    public synchronized static Sum getInstance(){
//        return s;
//    }
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<Integer> l = new ArrayList<>();
        for(int i=1; i<=3; i++) {
            l.add(i);
            for(Object o : l)
                System.out.println(o + " ");
        }
//        Sum s = new Sum();
//        System.out.println(String.format("%1tB", Calendar.getInstance()) );
////        System.out.println(String.format("%1$", Calendar.getInstance()) );
//        System.out.println(String.format("%tT", Calendar.getInstance()) );
//        System.out.println(String.format("%tT", Calendar.getInstance().toString()) );
//        System.out.println(String.format("%tH:%tM:%tS", Calendar.getInstance(), Calendar.getInstance(), Calendar.getInstance()) );

//        int x=-1;
//        x = x>>32;
//        System.out.println(x);
//        x=-1;
//        x = x >>>1;
//        System.out.println(x);
//        x=-1;
//        x = x>>1;
//        System.out.println(x);
//        x=-1;
//        x = x >>> 32;
//        System.out.println(x);
//        x=-1;
//        x = x >>> 0;
//        System.out.println(x);
    }
//    int low, high;
//    int[] array;
//    Sum(int[] arr, int l, int h) {
//        array = arr;
//        low = l;
//        high = h;
//    }
//
//    @Override
//    protected Object compute(){
//        if(high-low <=4){
//            long sum=0;
//            for(int i=low; i<high; i++)
//                sum+=array[i];
//        }
//        else {
//            int mid = low + (high-low)/2;
//            Sum left = new Sum(array, low, mid);
//            Sum right = new Sum(array, mid, high);
//            left.fork();
//            long rightAns = right.process();
//            long leftAns = left.join();
//            return leftAns + rightAns;
//
//        }
//        return null;
//    }
//
//    private void fork() {
//    }
//
//    static long sumArray(int[] array) {
//        return new ForkJoin().invoke(new Sum(array,0,array.length));
//    }
//
////    @Override
////    protected Object compute() {
////        return null;
////    }
}
