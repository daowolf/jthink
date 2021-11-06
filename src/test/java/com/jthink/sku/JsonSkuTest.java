package com.jthink.sku;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class JsonSkuTest {
	public static void main(String[] args) {
		System.out.println("Hello,Wordl json spec");
		List<Integer> item1=new ArrayList<Integer>();
		item1.add(1);
		List<Integer> item2=new ArrayList<Integer>();
		item2.add(2);
		List<Integer> item3=new ArrayList<Integer>();
		item3.add(3);
		List<List<Integer>> allItem=new ArrayList<List<Integer>>();
		allItem.add(item1);
		allItem.add(item2);
		allItem.add(item3);
		Gson gson=new Gson();
		String jsonStr=gson.toJson(allItem);
		System.out.println("alljson:");
		System.out.println(jsonStr);
	}
	
}
