package com.company.basic;

import java.util.*;


public class Prac1 {
	public static void main(String[] args) {
Scanner scan=new Scanner(System.in);      
		int n=scan.nextInt();
		for(int i=1;i<=n;i++) {
			for(int a=1;a<=i;a++) {
				if (a== 1 || a== i) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
			}
			for(int s=1;s<=2*(n-i);s++) {
				System.out.print(" ");
			}
			for(int a=1;a<=i;a++) {
				if (a== 1 || a== i) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
			}
			System.out.println();
		}
		// Lower Part-
		for(int i=n;i>0;i--) {
			for(int a=1;a<=i;a++) {
				if (a== 1 || a== i) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
			}
			for(int s=1;s<=2*(n-i);s++) {
				System.out.print(" ");
			}
			for(int a=1;a<=i;a++) {
				if (a== 1 || a== i) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
			}
			System.out.println();
		}
