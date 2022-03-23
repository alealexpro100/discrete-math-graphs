package com.example.demo1;

public class Solution {

    public static String main(String vector, int type, int argInd) {

        if ( vector.chars().filter( x->x == '1' ).count() + vector.chars().filter( x->x == '0' ).count() != vector.length()
        || (1 << argInd) > vector.length()
        || Integer.toBinaryString(vector.length()).chars().filter( x-> x== '1' ).count() != 1 ) {

            return null;
        }

        var str = new StringBuilder();
        var size = vector.length() / ( 1 << argInd );
        System.out.println(size + "");
        var cntIter = vector.length() / 2 / size;
        var start = (type == 0 ? 0 : size);
        for (int i = 0; i < cntIter; ++i) {
            str.append(vector, start, start + size);
            start += 2 * size;
        }

        return new String( str );
    }
}
