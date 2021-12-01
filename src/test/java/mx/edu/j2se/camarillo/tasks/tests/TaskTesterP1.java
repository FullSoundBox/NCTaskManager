package mx.edu.j2se.camarillo.tasks.tests;

import mx.edu.j2se.camarillo.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

/**
 * Task class tester program. This program evaluates the several methods included in
 * the Task class.
 * @author Abraham Camarillo
 * @since <pre> November 15, 2021</pre>
 * @version 1.0
 */
public class TaskTesterP1 {

    /**
     * Test task title setter and getter methods
     */
    @Test
    public void testTitle(){
        Task taskOrdenarLibrero = new Task("Ordenar Librero", 12);
        Task taskCalisthenics = new Task("Hacer Barras", 8, 12, 1);

        Assert.assertEquals("Ordenar Librero",taskOrdenarLibrero.getTitle());
        taskOrdenarLibrero.setTitle("Ordenar Escritorio");
        Assert.assertEquals("Ordenar Escritorio",taskOrdenarLibrero.getTitle());

        Assert.assertEquals("Hacer Barras",taskCalisthenics.getTitle());
        taskCalisthenics.setTitle("Correr en la Manana");
        Assert.assertEquals("Correr en la Manana",taskCalisthenics.getTitle());
    }

    /**
     * Test non-repetitive task active status, time setters and getters and
     * repetitive task transformation
     */
    @Test
    public void testNonRepetitive(){
        Task taskOrdenarLibrero = new Task("Ordenar Librero", 12);
        //Active status
        Assert.assertFalse(taskOrdenarLibrero.isActive());
        taskOrdenarLibrero.setActive(true);
        Assert.assertTrue(taskOrdenarLibrero.isActive());
        taskOrdenarLibrero.setActive(false);
        Assert.assertFalse(taskOrdenarLibrero.isActive());

        //Time setters and getters
        Assert.assertEquals(12,taskOrdenarLibrero.getTime());
        taskOrdenarLibrero.setTime(11);
        Assert.assertEquals(11,taskOrdenarLibrero.getTime());

        //Get repeat interval, being a non-repetitive, should return 0
        Assert.assertEquals(0,taskOrdenarLibrero.getRepeatInterval());
    }

    @Test
    public void testRepetitive(){
        Task taskCalisthenics = new Task("Hacer Barras", 8, 12, 2);

        //Test repetitive task if active or not
        Assert.assertFalse(taskCalisthenics.isActive());
        taskCalisthenics.setActive(true);
        Assert.assertTrue(taskCalisthenics.isActive());
        taskCalisthenics.setActive(false);
        Assert.assertFalse(taskCalisthenics.isActive());

        //Repetitive task setTime and getTime
        Assert.assertEquals(8,taskCalisthenics.getStartTime());
        Assert.assertEquals(12,taskCalisthenics.getEndTime());
        taskCalisthenics.setTime(9,15,3);
        Assert.assertEquals(9,taskCalisthenics.getStartTime());
        Assert.assertEquals(15,taskCalisthenics.getEndTime());

        //Get repeat interval
        Assert.assertEquals(3,taskCalisthenics.getRepeatInterval());
    }

    /**
     * This test verifies if the repeatability of task is modified when setTime method with more
     * parameters is used. A non-repetitive task should turn into a repetitive task when startTime, endTime
     * and execution interval are specified, and viceversa
     */
    @Test
    public void testRepeated(){
        Task taskOrdenarLibrero = new Task("Ordenar Librero", 12);//Non-repetitive
        Task taskCalisthenics = new Task("Hacer Barras", 8, 9, 24);//Repetitive

        //Transform a non-repetitive task into a repetitive task
        Assert.assertFalse(taskOrdenarLibrero.isRepeated());
        taskOrdenarLibrero.setTime(9,10,24);
        Assert.assertTrue(taskOrdenarLibrero.isRepeated());

        //Transform a repetitive task into a non-repetitive task
        Assert.assertTrue(taskCalisthenics.isRepeated());
        taskCalisthenics.setTime(10);
        Assert.assertFalse(taskCalisthenics.isRepeated());
    }

    /**
     * Verifying nextTimeAfter possible cases, when task are not active, when the current time is after task
     * execution time, before execution time and between startTime and endTime.
     */
    @Test
    public void testNextTimeAfter(){
        Task taskOrdenarLibrero = new Task("Ordenar Librero", 26);//Non-repetitive

        //Testing non-repetitive task
        Assert.assertEquals(-1,taskOrdenarLibrero.nextTimeAfter(23));// task is not active
        taskOrdenarLibrero.setActive(true);
        Assert.assertEquals(26,taskOrdenarLibrero.nextTimeAfter(26)); //0 date same day as the task
        Assert.assertEquals(26,taskOrdenarLibrero.nextTimeAfter(23)); //3 date before the task
        Assert.assertEquals(-1,taskOrdenarLibrero.nextTimeAfter(27)); //-1 date after the task

        Task taskCalisthenics = new Task("Hacer Barras", 5, 24, 3);//Repetitive

        //Testing repetitive task
        Assert.assertEquals(-1,taskCalisthenics.nextTimeAfter(8)); //task is not active
        taskCalisthenics.setActive(true);
        Assert.assertEquals(5,taskCalisthenics.nextTimeAfter(3));//active, date before startTime
        Assert.assertEquals(5,taskCalisthenics.nextTimeAfter(5));//active, date is the same as startTime
        Assert.assertEquals(8,taskCalisthenics.nextTimeAfter(7));//active, between startTime and next execution
        Assert.assertEquals(8,taskCalisthenics.nextTimeAfter(8));//active, same as next execution
        Assert.assertEquals(-1,taskCalisthenics.nextTimeAfter(25));//active, after end time
    }
}
