package com.yui.study.encryption;

/**
 * Diffie-Hellman密钥交换算法
 * p -> 素数
 * g -> p的原根
 * ax = g^a mod p
 * bx = g^b mod p
 * bx^a mod p = ax^b mod p
 * <p>
 * 欧拉函数 对正整数n，欧拉函数是小于n的正整数中与n互质的数的数目（φ(1)=1）
 * φ(7) -> 1, 2, ,3 ,4 ,5 ,6 -> 6
 * 质数m的欧拉函数等于 质数-1 -> φ(m) = m -1;
 * <p>
 * 原根： 设m是正整数，a是整数，若a模m的阶等于φ(m)，则称a为模m的一个原根
 * 若a模m的阶等于φ(m) -> 若a模m的阶乘等于φ(m)
 * m!=m ×(m-1) ×(m-2) ×(m-3)... 5×4 ×3 ×2 ×1
 * // 5,7,10,11,14,15,17,19,20,21
 * 举每个数i（1<=i<φ(m)）算gi≡1(modm)若满足则这个g一定不是原根
 * φ(23) = 22
 * <p>
 * A:
 * ax = g^a mod p
 * have ax g a p bx
 * <p>
 * B:
 * bx = g^b mod p
 * have bx g b p ax
 * <p>
 * every one
 * know: ax g p bx ,
 * unknown: a, b
 * <p>
 * the password：
 * password = bx^a mod p = ax^b mod p
 *
 * @author XuZhuohao
 * @date 2018/11/23
 */
public class DiffieHellman {
    public static void main(String[] args) {
        DiffieHellman diffieHellman = new DiffieHellman();
        diffieHellman.personA();
        //test(15, 23);
    }

    public static void test(int a, int p) {
        for (int i = 2; i < p - 1; i++) {
            double result = Math.pow(a, i) % p;
            System.out.println("i = " + i + ",result = " + result);
            if (result == 1) {
                System.err.println("i=" + i);
            }
        }
    }


    public void personA() {
        // A: A=g^a mod p
        // 素数
        double p = 23;
        // 底数 5,7,10,11,14,15,17,19,20,21
        double g = 15;
        // 秘数
        double a = 2;
        double ax = df(g, a, p);
        double bx = personB(p, g, ax);
        System.out.println("ax = " + ax + ",bx = " + bx);
        // B^a mod p   a^(XA*XB) mod p
        System.out.println("约定密码为：" + df(bx, a, p));
        /*
        A:
        ax = g^a mod p
        have ax g a p bx

        B:
        bx = g^b mod p
        have bx g b p ax

        every one
        know: ax g p bx ,
        unknown: a, b

        the password：
        password = bx^a mod p = ax^b mod p
         */
    }

    public double personB(double p, double g, double ax) {
        // 选定秘数
        double b = 15;
//        double bx = Math.pow(g, b) % p
        double bx = df(g, b, p);
//        System.out.println("约定密码为：" + (Math.pow(ax, b) % p))
        System.out.println("约定密码为：" + df(ax, b, p));
        return bx;
    }

    private double df(double g, double a, double p) {
        return Math.pow(g, a) % p;
    }

}
