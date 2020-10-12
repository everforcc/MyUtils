package cn.cc.onjava8.test;

import org.junit.jupiter.api.Test;

import java.util.*;

public class OnJava8 {

    @Test
    void a1(){
        int[] ary = {1,2,3,};
        System.out.println(Arrays.toString(ary));
    }

    @Test
    void a2(){
        List list = Arrays.asList(1,2,3);

        LinkedList<Integer> petsLL = new LinkedList<>(list);
        HashSet<Integer> petsHS = new HashSet<>(list);
        TreeSet<Integer> petsTS = new TreeSet<>(list);

        display(list.iterator());
        display(petsLL.iterator());
        display(petsHS.iterator());
        display(petsTS.iterator());

        //

        display(list);
        display(petsLL);
        display(petsHS);
        display(petsTS);
        //
        Iterable<Integer> ip = petsHS ;
    }

    public static void display(Iterator<Integer> it) {
        while(it.hasNext()) {
            Integer p = it.next();
            System.out.print(p.intValue() + ":" + p + " ");
        }
        System.out.println();
    }

    public static void display(Iterable<Integer> ip) {
        Iterator<Integer> it = ip.iterator();
        while(it.hasNext()) {
            Integer p = it.next();
            System.out.print(p.intValue() + ":" + p + " ");
        }
        System.out.println();
    }

    @Test
    void a22(){
        List<Integer> pets = new ArrayList(){{
            add(9);
            add(9);
            add(9);
        }};
        Iterator<Integer> it = pets.iterator();
        while(it.hasNext()) {
            Integer p = it.next();
            System.out.print(p.intValue() + ":" + p + " ");
        }
        System.out.println();
        // A simpler approach, when possible:
        for(Integer p : pets)
            System.out.print(p.intValue() + ":" + p + " ");
        System.out.println();
        // An Iterator can also remove elements:
        it = pets.iterator();
        for(int i = 0; i < 3; i++) {
            it.next();
            it.remove();
        }
        System.out.println(pets);
    }

    @Test
    void a3(){
        // 这个是数组所以不行
        List list = new ArrayList(){{
            add(9);
            add(9);
            add(9);
        }};
        List<Integer> pets = list;
        ListIterator<Integer> it = pets.listIterator();
        while(it.hasNext())
            System.out.print(it.next() +
                    ", " + it.nextIndex() +
                    ", " + it.previousIndex() + "; ");
        System.out.println("---");
        // Backwards:
        while(it.hasPrevious())
            System.out.print(it.previous().intValue() + " ");
        System.out.println("---");
        System.out.println(pets);

        // 从第三个开始所有没有，那么从 0开始
        // it = pets.listIterator(3);
        it = pets.listIterator(2);

        while(it.hasNext()) {
            it.next();
            it.set(new Integer(123));
            //it.remove();
        }
        System.out.println("---");
        System.out.println(pets);
    }

    @Test
    void a4(){
        /*Deque<String> stack = new ArrayDeque<>();
        for(String s : "My dog has fleas".split(" "))
            stack.push(s);
        while(!stack.isEmpty())
            System.out.print(stack.pop() + " ");*/

        Random rand = new Random(47);
        Map<Integer, Integer> m = new HashMap<>();
        for(int i = 0; i < 10000; i++) {
            // Produce a number between 0 and 20:
            int r = rand.nextInt(20);
            Integer freq = m.get(r); // [1]
            m.put(r, freq == null ? 1 : freq + 1);
        }
        System.out.println(m);


    }




}
