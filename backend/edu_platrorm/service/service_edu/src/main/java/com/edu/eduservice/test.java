package com.edu.eduservice;

import org.bouncycastle.asn1.x509.qualified.RFC3739QCObjectIdentifiers;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class test {




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取输入
        int n = scanner.nextInt();
        int[] balls = new int[n];
        for (int i = 0; i < n; i++) {
            balls[i] = scanner.nextInt();
        }

        // 动态规划求解
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // 初始化dp表，单个球的区间不需要添加球
        for (int i = 0; i < n; i++) {
            dp[i][i] = 2;  // 单个球需要两个相同颜色的球来消除
        }

        // 填表
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                // 如果首尾相同
                if (balls[i] == balls[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i+1][j-1] + (len == 2 ? 0 : -1));
                }

                // 尝试分割区间
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j]);
                }
            }
        }

        // 输出结果
        System.out.println(dp[0][n-1] - n);  // 减去原来的球数，因为最终需要的是添加的球数
    }

}
