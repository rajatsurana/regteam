package com.rajat.registrationcop290.Tools;

import java.util.LinkedList;

public class main {
	public static void main(String[] args){
		String[] array=new String[15];
		array[0]="arpit";
		array[1]="kapoor";
		array[2]="arpita";
		array[3]="rajat";
		array[4]="parichay";
		array[5]="anu";
		array[6]="ghanendra";
		array[7]="rajat saurana";
		array[8]="alia";
		array[9]="aashna";
		array[10]="ritvik";
		array[11]="sudhanshu";
		array[12]="xeavior";
		array[13]="anurup";
		array[14]="anur";
		System.out.println(array.length);

		new mergesort(array);

		for(int i=0;i<15;i++){
			System.out.println(array[i]);
		}
		LinkedList<String> list=new LinkedList<String>();
		binary_search binary=new binary_search();
		list=binary.search("an", array);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
}
