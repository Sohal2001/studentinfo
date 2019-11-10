package com.stuinfo;

import java.util.Map;
import java.util.Scanner;

public class StudentInfoRunner {

    public static void main(String[] args) {
        Scanner sc;
        String input;
        int option=-1;
        while (option != 0) {
            try {
                sc = new Scanner(System.in);
                System.out.println("\nSelect your option:\n" +
                        "1 for Enter Student information\n" +
                        "2 for Print students list sorted by Name\n" +
                        "3 for Print students list sorted by Roll Number\n" +
                        "0 for Exit Program");
                input = sc.next();
                try {
                    option = Integer.parseInt(input);
                } catch (Exception e) {
                    System.out.println("Wrong option, Try again...");
                    continue;
                }
                if (option == 1) {
                    Student s = new Student();

                    System.out.println("Enter Student Name: ");
                    String name = sc.next();
                    boolean allLetters = name.chars().allMatch(Character::isLetter);
                    if (!name.equals("") && name != null && name.length() >= 2 && allLetters) {
                        s.setName(name);
                    } else {
                        System.out.println("Name is not valid try again...");
                        continue;
                    }

                    System.out.println("Enter Student Roll Number: ");
                    int rollNo = sc.nextInt();
                    if (rollNo > 0) {
                        s.setRollNo(rollNo);
                    }
                    if (!new StuInfoProcessor().addStudent(s)) {
                        continue;
                    }
                    System.out.println("Student with roll number " + rollNo + " and name " + name + " is created successfully.\n");
                    continue;
                } else if (option == 2) {
                    Map<Integer, String> byName = new StuInfoProcessor().getSortedByName();
                    if (byName != null) {
                        for (Map.Entry<Integer, String> e : byName.entrySet()) {
                            System.out.println("name:" + e.getValue() + ", Roll Number:" + e.getKey());
                        }
                    }
                    continue;
                } else if (option == 3) {
                    Map<Integer, String> byRollNo = new StuInfoProcessor().getSortedByRollNumber();
                    if (byRollNo != null) {
                        for (Map.Entry<Integer, String> e : byRollNo.entrySet()) {
                            System.out.println("name:" + e.getValue() + ", Roll Number:" + e.getKey());
                        }
                    }
                    continue;
                }
                else{
                    System.out.println("Only Acceptable option is [ 0, 1, 2, 3],  Try again with one of the value ...");
                }
            }
            catch (Exception e){
                continue;
            }
        }
    }
}
