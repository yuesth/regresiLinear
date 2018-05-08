package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	private static List<Double> array_x;
	private  static  List<Double> array_y;
	private static Scanner sc = new Scanner(System.in);
	private static int pilihFungsi;
	static double sigma_x= 0, sigma_y= 0, sigma_xy= 0, sigma_x2= 0;

	static void cari_nilai_linier(List nilaiX, List nilaiY){
		int i=0;
		while (i < nilaiX.size()){
			sigma_x += (double) nilaiX.get(i);
			sigma_y += (double) nilaiY.get(i);
			sigma_xy += ((double) nilaiX.get(i) * (double) nilaiY.get(i));
			sigma_x2 += Math.pow((double) nilaiX.get(i),2);
			i++;
		}
	}

	static void cari_nilai_berpangkat(List nilaiX, List nilaiY){
		int b = 0;
		while (b < nilaiX.size()){
			sigma_x += Math.log((double) nilaiX.get(b));
			sigma_y += Math.log((double) nilaiY.get(b));
			sigma_xy += ((double) nilaiX.get(b) * (double) nilaiY.get(b));
			sigma_x2 += sigma_x2 += Math.pow((double) nilaiX.get(b),2);
			b++;
		}
		System.out.println(sigma_x+", "+sigma_y);
	}

	static void cari_nilai_eksponensial(List nilaiX, List nilaiY){
		cari_nilai_berpangkat(nilaiX, nilaiY);
	}

	static double rumus_b(){
		double hasil_b = ((array_x.size()*sigma_xy) - (sigma_x * sigma_y)) / ((array_x.size()*sigma_x2) - Math.pow(sigma_x,2));
		return  hasil_b;
	}

	static double rumus_a(){
		double hasil_a = ((sigma_y * sigma_x2) - (sigma_x * sigma_xy)) / ((array_x.size()*sigma_x2) - Math.pow(sigma_x,2));
		return hasil_a;
	}

	public static void regresi_sederhana (List x, List y){
		if(pilihFungsi == 1){
			cari_nilai_linier(x,y);
			double nilai_a = rumus_a();
			double nilai_b = rumus_b();
			System.out.println("a = "+nilai_a+"; b = "+nilai_b);
			String y_linier = "y = "+nilai_a+"+"+nilai_b+"X";
			System.out.println(y_linier);
		}
		else if(pilihFungsi == 2){
			cari_nilai_berpangkat(x,y);
			double nilai_a = rumus_a();
			double nilai_b = rumus_b();
			System.out.println("a = "+nilai_a+"; b = "+nilai_b);
			String y_berpangkat = "y = "+nilai_a+"X^"+nilai_b;
			System.out.println(y_berpangkat);
		}
		else if(pilihFungsi == 3){
			cari_nilai_eksponensial(x,y);
			double nilai_a = Math.exp(rumus_a());
			double nilai_b = rumus_b();
			System.out.println("a = "+nilai_a+"; b = "+nilai_b);
			String y_eksponensial = "y = "+nilai_a+"e^"+nilai_b+"X";
			System.out.println(y_eksponensial);
		}

	}

    public static void main(String[] args) {
		System.out.println("--Selamat datang di program analisis regresi linier--");
		System.out.println("Pilih fungsi: (1)Fungsi Linier (2)Fungsi berpangkat (3)Fungsi eksponensial");
		pilihFungsi = sc.nextInt();
		System.out.println("Masukkan batas sampel : ");
		int batas_sampel = sc.nextInt();
		array_x = new ArrayList<>();
		array_y = new ArrayList<>();
		for(int i=0; i<batas_sampel; i++){
			System.out.print("nilai x ke- "+(i+1)+" : ");
			array_x.add(sc.nextDouble());
		}
		for(int i=0; i<batas_sampel; i++){
			System.out.print("nilai y ke- "+(i+1)+" : ");
			array_y.add(sc.nextDouble());
		}

		//regresi sederhana
		//regresi_sederhana(array_x, array_y);
		cari_nilai_berpangkat(array_x,array_y);
	}

}
