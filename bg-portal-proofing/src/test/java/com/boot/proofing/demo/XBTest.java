package com.boot.proofing.demo;/**
 * @description
 * @autor xbwu on 2018/1/9.
 */


import com.boot.core.common.asyn.TestAsynTask;
import com.boot.core.common.asyn.ThreadPool;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

/**
 * 小彬随便测试
 *
 * @author xbwu
 * @create 2018-01-09
 **/
public class XBTest {

    public static void main(String... args) throws IOException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行线程");
            }
        });
        //匿名内部类
        new Thread(()->{
            System.out.println("执行线程");
        });

        final List<BigDecimal> prices = Arrays.asList(
                new BigDecimal("10"), new BigDecimal("30"), new BigDecimal("17"),
                new BigDecimal("20"), new BigDecimal("15"), new BigDecimal("18"),
                new BigDecimal("45"), new BigDecimal("12"));

        final BigDecimal totalOfDiscountedPrices =
                prices.stream()
                        .filter(price -> price.compareTo(BigDecimal.valueOf(20)) > 0)
                        .map(price -> price.multiply(BigDecimal.valueOf(0.9)))
                        .reduce(BigDecimal.ZERO,(b1,b2)->{return b1.add(b2);});
        System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
    }
    @Test
    public void testInstant(){
        Instant now = Instant.now();
        System.out.println(now);
    }
}
