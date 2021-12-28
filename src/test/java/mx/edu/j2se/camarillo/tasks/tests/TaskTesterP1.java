package mx.edu.j2se.camarillo.tasks.tests;

import mx.edu.j2se.camarillo.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Task class tester program. This program evaluates the several methods included in
 * the Task class.
 * @author Abraham Camarillo
 * @since <pre> November 15, 2021</pre>
 * @version 2.0
 */
public class TaskTesterP1 {

    /**
     * Test task title setter and getter methods
     */
    @Test
    public void testTitle(){
        Task taskOrdenarLibrero = new Task("Ordenar Librero",
                LocalDateTime.of(2021,1,1,7,0));
        Task taskCalisthenics = new Task("Hacer Barras",
                LocalDateTime.of(2021,1,1,7,0),
                LocalDateTime.of(2021,4,1,7,0), 24);

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
        Task taskOrdenarLibrero = new Task("Ordenar Librero",
                LocalDateTime.of(2021,1,1,7,0));
        //Active status
        Assert.assertFalse(taskOrdenarLibrero.isActive());
        taskOrdenarLibrero.setActive(true);
        Assert.assertTrue(taskOrdenarLibrero.isActive());
        taskOrdenarLibrero.setActive(false);
        Assert.assertFalse(taskOrdenarLibrero.isActive());

        //Time setters and getters
        Assert.assertEquals(LocalDateTime.of(2021,1,1,7,0),
                taskOrdenarLibrero.getTime());
        taskOrdenarLibrero.setTime(LocalDateTime.of(2021,1,10,8,0));
        Assert.assertEquals(LocalDateTime.of(2021,1,10,8,0),taskOrdenarLibrero.getTime());

        //Get repeat interval, being a non-repetitive, should return 0
        Assert.assertEquals(0,taskOrdenarLibrero.getRepeatInterval());
    }

    @Test
    public void testRepetitive(){
        Task taskCalisthenics = new Task("Hacer Barras",
                LocalDateTime.of(2021,1,1,7,0),
                LocalDateTime.of(2021,4,1,7,0), 24);

        //Test repetitive task if active or not
        Assert.assertFalse(taskCalisthenics.isActive());
        taskCalisthenics.setActive(true);
        Assert.assertTrue(taskCalisthenics.isActive());
        taskCalisthenics.setActive(false);
        Assert.assertFalse(taskCalisthenics.isActive());

        //Repetitive task setTime and getTime
        Assert.assertEquals(LocalDateTime.of(2021,1,1,7,0),
                taskCalisthenics.getStartTime());
        Assert.assertEquals(LocalDateTime.of(2021,4,1,7,0),
                taskCalisthenics.getEndTime());

        taskCalisthenics.setTime(LocalDateTime.of(2021,2,1,8,0),
                LocalDateTime.of(2021,5,1,7,0),48);
        Assert.assertEquals(LocalDateTime.of(2021,2,1,8,0),
                taskCalisthenics.getStartTime());
        Assert.assertEquals(LocalDateTime.of(2021,5,1,7,0),
                taskCalisthenics.getEndTime());

        //Get repeat interval
        Assert.assertEquals(48,taskCalisthenics.getRepeatInterval());
    }

    /**
     * This test verifies if the repeatability of task is modified when setTime method with more
     * parameters is used. A non-repetitive task should turn into a repetitive task when startTime, endTime
     * and execution interval are specified, and viceversa
     */
    @Test
    public void testRepeated(){
        Task taskOrdenarLibrero = new Task("Ordenar Librero",
                LocalDateTime.of(2021,1,1,7,0));//Non-repetitive
        Task taskCalisthenics = new Task("Hacer Barras",
                LocalDateTime.of(2021,1,1,7,0),
                LocalDateTime.of(2021,4,1,7,0), 24);//Repetitive

        //Transform a non-repetitive task into a repetitive task
        Assert.assertFalse(taskOrdenarLibrero.isRepeated());
        taskOrdenarLibrero.setTime(LocalDateTime.of(2021,1,1,7,0),
                LocalDateTime.of(2021,4,1,7,0), 24);
        Assert.assertTrue(taskOrdenarLibrero.isRepeated());

        //Transform a repetitive task into a non-repetitive task
        Assert.assertTrue(taskCalisthenics.isRepeated());
        taskCalisthenics.setTime(LocalDateTime.of(2021,1,1,7,0));
        Assert.assertFalse(taskCalisthenics.isRepeated());
    }

    /**
     * Verifying nextTimeAfter possible cases, when task are not active, when the current time is after task
     * execution time, before execution time and between startTime and endTime.
     */
    @Test
    public void testNextTimeAfter(){
        Task taskOrdenarLibrero = new Task("Ordenar Librero",
                LocalDateTime.of(2021,1,1,7,0));//Non-repetitive

        //Testing non-repetitive task
        Assert.assertEquals(null,
                taskOrdenarLibrero.nextTimeAfter(LocalDateTime.of(2021,1,1,7,0)));// task is not active
        taskOrdenarLibrero.setActive(true);
        Assert.assertEquals(null,
                taskOrdenarLibrero.nextTimeAfter(LocalDateTime.of(2021,1,1,7,0)));
        Assert.assertEquals(LocalDateTime.of(2021,1,1,7,0),
                taskOrdenarLibrero.nextTimeAfter(LocalDateTime.of(2021,1,1,3,0)));
        Assert.assertEquals(null,
                taskOrdenarLibrero.nextTimeAfter(LocalDateTime.of(2021,1,1,15,0)));

        Task taskCalisthenics = new Task("Hacer Barras",
                LocalDateTime.of(2021,1,1,7,0),
                LocalDateTime.of(2021,4,1,7,0), 24);//Repetitive

        //Testing repetitive task
        Assert.assertEquals(null,
                taskCalisthenics.nextTimeAfter(LocalDateTime.of(2021,1,1,7,0))); //task is not active
        taskCalisthenics.setActive(true);
        Assert.assertEquals(LocalDateTime.of(2021,1,1,7,0),
                taskCalisthenics.nextTimeAfter(LocalDateTime.of(2021,1,1,5,0)));//active, date before startTime
        Assert.assertEquals(LocalDateTime.of(2021,1,1,7,0),
                taskCalisthenics.nextTimeAfter(LocalDateTime.of(2021,1,1,7,0)));//active, date is the same as startTime
        Assert.assertEquals(LocalDateTime.of(2021,3,1,7,0),
                taskCalisthenics.nextTimeAfter(LocalDateTime.of(2021,3,1,7,0)));//active, between startTime and next execution
        Assert.assertEquals(LocalDateTime.of(2021,1,1,7,0), //TODO: Check different implementation for this case
                taskCalisthenics.nextTimeAfter(LocalDateTime.of(2021,1,1,7,0)));//active, same as next execution
        Assert.assertEquals(null,
                taskCalisthenics.nextTimeAfter(LocalDateTime.of(2021,5,1,7,0)));//active, after end time
    }
}
