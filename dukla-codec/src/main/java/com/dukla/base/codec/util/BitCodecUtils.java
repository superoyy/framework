package com.dukla.base.codec.util;

public final class BitCodecUtils {

    /**
     * 1、计算某个正数的二进制表示法中 1 的个数
     * 算法思路：每次for循环，都将num的二进制中最右边的 1 清除。
     * 为什么n &= (n – 1)能清除最右边的1呢？
     * 因为从二进制的角度讲，n相当于在n - 1的最低位加上1。
     * 举个例子，8（1000）= 7（0111）+ 1（0001），所以8 & 7 = （1000）&（0111）= 0（0000），
     * 清除了8最右边的1（其实就是最高位的1，因为8的二进制中只有一个1）。
     * 再比如7（0111）= 6（0110）+ 1（0001），所以7 & 6 = （0111）&（0110）= 6（0110），
     * 清除了7的二进制表示中最右边的1（也就是最低位的1）。
     */
    public static int get1Count(int val){
        int count = 0;
        for(; val > 0; count++){
            val &= (val - 1);
        }
        return count;
    }

    /**
     * 2、获取某个数的第 pos 位（判断某个数的第 pos 位是0 还是 1）
     * 思路：如果第 pos 位 与 1 相与 结果为 1 表明第 pos 位为 1；如果为 0 表明第 pos 位为0
     */
    public static boolean is1AtPos(int val, int pos){
        return ((val & (1 << pos)) != 0);//true 表示第pos位为1,否则为0
    }

    /**
     * 3、将第 i 位设置为1
     * 思路：第 i 位与0 或，值不变。第 i 位与1 或，变成1。因此，我们的目标是 num 与 一个第 i 位值为1，
     * 其它位的值都为0的数相 或
     */
    public static int setPosTo1(int val, int pos){
        int mask = 1 << pos;//00000100
        return (val | mask);
    }

    /**
     * 4、将第 i 位设置为0（清0）
     * 思路：第 i 位和0与，第 i 位就变成了0。其它位 都与 1 与，其它位保持不变。这样，就只把第 i 位清0了
     */
    public static int setPosTo0(int val, int pos){
        int mask = ~(1 << pos);//111111011
        return (val & mask);
    }


}
