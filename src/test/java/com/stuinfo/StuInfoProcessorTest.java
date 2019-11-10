package com.stuinfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RunWith(PowerMockRunner.class)
@PrepareForTest(StuInfoProcessor.class)
@PowerMockIgnore({"javax.management.", "com.sun.org.apache.xerces.",
        "javax.xml.", "org.xml.", "org.w3c.dom.",
        "com.sun.org.apache.xalan.", "javax.activation.*"})
public class StuInfoProcessorTest {

    @InjectMocks
    StuInfoProcessor stuInfoProcessor;

    @Mock
    Student student;

    Map<Integer, String> actualStu = new TreeMap<Integer, String>();

    @Before
    public void init() {
        PowerMockito.mockStatic(StuInfoProcessor.class);
    }

    @Test
    public void testAddStudent() {
        student = Mockito.mock(Student.class);
        Mockito.when(student.getRollNo()).thenReturn(10);
        Mockito.when(student.getName()).thenReturn("Sohal");
        Assert.assertTrue(stuInfoProcessor.addStudent(student));

        Mockito.when(student.getRollNo()).thenReturn(7);
        Mockito.when(student.getName()).thenReturn("Shaan");
        Assert.assertTrue(stuInfoProcessor.addStudent(student));

        Assert.assertFalse(stuInfoProcessor.addStudent(null));

        Mockito.when(student.getRollNo()).thenReturn(10);
        Mockito.when(student.getName()).thenReturn("Sam");
        Assert.assertFalse(stuInfoProcessor.addStudent(student));
    }

    @Test
    public void testGetSortedByRollNumber() {
        StuInfoProcessor.students = new HashMap<>();
        Assert.assertNull(stuInfoProcessor.getSortedByRollNumber());
        student = Mockito.mock(Student.class);
        addStudentsForMock();
        dummyStuList();
        Assert.assertEquals(actualStu, stuInfoProcessor.getSortedByRollNumber());
    }

    @Test
    public void testGetSortedByName() {
        StuInfoProcessor.students = new HashMap<>();
        student = Mockito.mock(Student.class);
        Assert.assertNull(stuInfoProcessor.getSortedByName());
        student = Mockito.mock(Student.class);
        addStudentsForMock();
        dummyStuList();

        Assert.assertEquals(actualStu, stuInfoProcessor.getSortedByName());
    }

    public void dummyStuList() {
        actualStu.put(10, "Sam");
        actualStu.put(7, "Sohal");
        actualStu.put(2, "Shaan");
        actualStu.put(5, "Kishan");
    }

    public void addStudentsForMock() {
        Mockito.when(student.getRollNo()).thenReturn(10);
        Mockito.when(student.getName()).thenReturn("Sam");
        stuInfoProcessor.addStudent(student);
        Mockito.when(student.getRollNo()).thenReturn(7);
        Mockito.when(student.getName()).thenReturn("Sohal");
        stuInfoProcessor.addStudent(student);
        Mockito.when(student.getRollNo()).thenReturn(2);
        Mockito.when(student.getName()).thenReturn("Shaan");
        stuInfoProcessor.addStudent(student);
        Mockito.when(student.getRollNo()).thenReturn(5);
        Mockito.when(student.getName()).thenReturn("Kishan");
        stuInfoProcessor.addStudent(student);
    }
}