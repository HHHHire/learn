package generic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author ddh
 * @date 2019/8/21 13:02
 * @description 泛型限定
 **/
public class GenericEx {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("hello"));

        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("hi"));

        printColl(students);
        printColl(people);
    }
    private static void printColl(ArrayList<? extends Person> arrayList){
        Iterator<? extends Person> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getName());
        }
    }

}

class Student extends Person{

    Student(String name) {
        super(name);
    }
}

class Person {
    private String name;

    Person(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

