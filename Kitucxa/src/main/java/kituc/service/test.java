package kituc.service;

import java.math.BigInteger;

public class test {
    public static void main(String[] args) {
        BigInteger a = new BigInteger("2");
        BigInteger b = new BigInteger("7");
        System.out.println(a.multiply(b).divide(a.gcd(b)));
    }
}
