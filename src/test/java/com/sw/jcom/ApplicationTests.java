package com.sw.jcom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class ApplicationTests {
    public final static Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    };

	@Test
	public void contextLoads() {

		TreeSet<Integer> treeSet = new TreeSet<>(comparator);
		TreeSet<Integer> treeSet2 = new TreeSet<>(comparator);

		treeSet.add(-1);
		treeSet.add(1);
		treeSet.add(3);

        treeSet2.add(1);
        treeSet2.add(4);
        treeSet2.add(2);
        treeSet2.add(0);

		Iterator<Integer> iterator = treeSet.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}

        Iterator<Integer> iterator2 = treeSet2.iterator();
        while (iterator2.hasNext()){
            System.out.println(iterator2.next());
        }

	}

}
