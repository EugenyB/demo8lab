package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        List<Student> students = createList();
        printCollection(students);
        System.out.println("---------------------");
        // rating >= 74
        List<Student> list74 = findWithRatingNotLess(students, 74);
        System.out.println("All with rating >= 74");
        printCollection(list74);
        // list of faculties
        Set<String> faculties = findAllFaculties(students);
        printCollection(faculties);
        // find all students by faculties
//        Map<String, List<Student>> map = findAllStudentsByFaculties(students);
        Map<String, List<Student>> map = findAllStudentsByFacultiesStream(students);
        printMap(map);
    }

    private void printMap(Map<String, List<Student>> map) {
        for (Map.Entry<String, List<Student>> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            for (Student student : entry.getValue()) {
                System.out.println("    " + student);
            }
        }
    }

    private Map<String, List<Student>> findAllStudentsByFacultiesStream(List<Student> students) {
        return students.stream().collect(Collectors.groupingBy(Student::getFaculty));
    }

    private Map<String, List<Student>> findAllStudentsByFaculties(List<Student> students) {
        Map<String, List<Student>> result = new HashMap<>();
        for (Student student : students) {
            String f = student.getFaculty();
            if (result.containsKey(f)) {
                result.get(f).add(student);
            } else {
                result.put(f, new ArrayList<>(List.of(student)));
            }
        }
        return result;
    }

    private Set<String> findAllFaculties2(List<Student> students) {
        return students.stream().map(Student::getFaculty).collect(Collectors.toSet());
    }

    private Set<String> findAllFaculties(List<Student> students) {
        Set<String> result = new LinkedHashSet<>();
        for (Student student : students) {
            result.add(student.getFaculty());
        }
        return result;
    }

    private List<Student> findWithRatingNotLess(List<Student> students, int min) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getRating() >= min) {
                result.add(student);
            }
        }
        return result;
    }

    public void printCollection(Collection<?> collection) {
        for (Object obj : collection) {
            System.out.println(obj);
        }
    }

    private List<Student> createList() {
        List<Student> students = new ArrayList<>(List.of(
                new Student(1, "Ivan", "CS", 95),
                new Student(2, "Masha", "FVS", 87),
                new Student(3, "Katya", "KN", 64),
                new Student(4, "Vova", "CS", 78),
                new Student(5, "Petya", "FVS", 60),
                new Student(6, "Senya", "CS", 91),
                new Student(7, "Vasya", "CS", 99),
                new Student(8, "Ivan", "KN", 81),
                new Student(9, "Vasya", "KN", 75),
                new Student(10, "Masha", "CS", 78)
        ));
        return students;
    }
}