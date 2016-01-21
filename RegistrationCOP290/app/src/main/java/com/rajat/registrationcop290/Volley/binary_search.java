package com.rajat.registrationcop290.Volley;

import java.util.LinkedList;

/**
 * Created by Lenovo on 1/20/2016.
 */
public class binary_search {
    public LinkedList search (String key,String[] array){
        int j = search_index(key,array,0,array.length);
        LinkedList list=new LinkedList();
        list.add(array[j]);
        for(int i=j-1;i>=0;i--){
            if(key.contentEquals(array[i].substring(0,key.length()))) list.addFirst(array[i]);
            else break;
        }
        for(int i=j+1;j<array.length;i++){
            if(key.contentEquals(array[i].substring(0,key.length()))) list.addLast(array[i]);
            else break;
        }
        return list;
    }
    private int search_index(String key,String[] array,int inital,int last){
        int mid =(last-inital)/2;
        if(mid<=0)throw new NullPointerException();
        if(key.contentEquals(array[mid]))return mid;
        else if((key.compareToIgnoreCase(array[mid])>0))return search_index(key,array,mid,last);
        else return search_index(key,array,inital,mid);
    }
}
