package com.zx;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName dsfaf
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/24 19:14
 * @Version 1.0
 */
public class dsfaf {
    @Test
    public void ans(){
        char []charsCo = new char[]{'F','C','A','B'};
        char []charsCo1 = new char[]{'F','C','A','B'};
        Arrays.sort(charsCo);
        System.out.println(charsCo);

        System.out.println(charsCo[1] == charsCo1[1]);
    }
}
