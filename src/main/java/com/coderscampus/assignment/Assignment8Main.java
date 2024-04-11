package com.coderscampus.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.HashMap;

public class Assignment8Main {
	
	private static final int HashMap = 0;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Assignment8 numbers = new Assignment8();
		ExecutorService executor = Executors.newFixedThreadPool(10);
		ArrayList<Integer> fullList = new ArrayList<Integer>();
		HashMap<Integer, Integer> uniqueNumbers = new HashMap<Integer, Integer>();
		
		for (int i=0; i<1000; i++) {
			
			Callable <List<Integer>> callable = () -> {return numbers.getNumbers();};
			Future<List<Integer>> future = executor.submit(callable);
			List<Integer> result = future.get();
			fullList.addAll(result);
        }
		
		executor.shutdown();
		
		for (Integer i : fullList) {
			if (uniqueNumbers.containsKey(i)) {
				uniqueNumbers.put(i, uniqueNumbers.get(i) + 1);
			} else {
				uniqueNumbers.put(i, 1);
			}
		}
		
		System.out.println(uniqueNumbers);
	}
}

