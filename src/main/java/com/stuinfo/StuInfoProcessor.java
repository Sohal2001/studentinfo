package com.stuinfo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StuInfoProcessor {

    static HashMap<Integer, String> students = new HashMap<Integer, String>();

    public boolean addStudent(Student student) {
        if (student == null || students.containsKey(student.getRollNo())) {
            System.out.println("Student with same Roll Number Already present please try again...");
            return false;
        }
        students.put(student.getRollNo(), student.getName());
        return true;
    }

    public Map<Integer, String> getSortedByRollNumber() {
        if (students.isEmpty()) {
            System.out.println("Students Record is not Available...Please add some records");
            return null;
        }
        TreeMap<Integer, String> sortedMap = new TreeMap<Integer, String>();
        sortedMap.putAll(students);
        return sortedMap;
    }

    public LinkedHashMap<Integer, String> getSortedByName() {
        if (students.isEmpty()) {
            System.out.println("Students Record is not Available...Please add some records");
            return null;
        }
        LinkedHashMap sortedMap = students.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return sortedMap;
    }
}
