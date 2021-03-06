package com.rajat.registrationcop290.Tools;

import java.util.LinkedList;

/**
 * Created by Lenovo on 1/20/2016.
 */
public class binary_search {
    public LinkedList<String> search (String key,String[] array){
        int j = search_index(key,array,0,array.length);
        LinkedList<String> list=new LinkedList<String>();
        list.add(array[j]);
        for(int i=j-1;i>=0;i--){
            if(equal(key,array[i])) list.addFirst(array[i]);
            else break;
        }
        for(int i=j+1;i<array.length;i++){
            if(equal(key,array[i])) list.addLast(array[i]);
            else break;
        }
        return list;
    }
    private int search_index(String key,String[] array,int inital,int last){
        int mid =(last+inital)/2;
        if(inital>last)throw new NullPointerException();
        if(equal(key,array[mid]))return mid;
        else if((key.compareToIgnoreCase(array[mid])>0))return search_index(key,array,mid+1,last);
        else return search_index(key,array,inital,mid-1);
    }
    private boolean equal(String a, String b ){
    	if (b.length()>=a.length())return a.contentEquals(b.substring(0,a.length()));
    	else return false;
    	}
}
