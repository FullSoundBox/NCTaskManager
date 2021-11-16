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

    @Test
    public void testTitle(){
        /**
         * Test task title setter and getter methods
         */
        Task taskOrdenarLibrero = new Task("Ordenar Librero", 12);

        Assert.assertEquals("Ordenar Librero",taskOrdenarLibrero.getTitle());
        taskOrdenarLibrero.setTitle("Ordenar Escritorio");
        Assert.assertEquals("Ordenar Escritorio",taskOrdenarLibrero.getTitle());
    }

    @Test
    public void testNonRepetitive(){
        /**
         * Test non-repetitive task active status, time setters and getters and
         * repetitive task transformation
         */

        Task taskOrdenarLibrero = new Task("Ordenar Librero", 12);
        //Active status
        Assert.assertFalse(taskOrdenarLibrero.isActive());
        taskOrdenarLibrero.setActive(true);
        Assert.assertTrue(taskOrdenarLibrero.isActive());

        //Time setters and getters
        Assert.assertEquals(12,taskOrdenarLibrero.getTime());
        taskOrdenarLibrero.setTime(11);
        Assert.assertEquals(11,taskOrdenarLibrero.getTime());

        //TODO: Arreglar el nextTimeAfter
    }

    @Test
    public void testRepetitive(){

        Task taskCalisthenics = new Task("Hacer Barras", 8, 9, 24);

        Assert.assertFalse(taskCalisthenics.isActive());
        taskCalisthenics.setActive(true);
        Assert.assertTrue(taskCalisthenics.isActive());

    }

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

    @Test
    public void testNextTimeAfter(){
        Task taskOrdenarLibrero = new Task("Ordenar Librero", 26);//Non-repetitive

        Assert.assertEquals(-1,taskOrdenarLibrero.nextTimeAfter(23));// task is not active
        taskOrdenarLibrero.setActive(true);
        Assert.assertEquals(0,taskOrdenarLibrero.nextTimeAfter(26)); //0 date same day as the task
        Assert.assertEquals(3,taskOrdenarLibrero.nextTimeAfter(23)); //3 date before the task
        Assert.assertEquals(-1,taskOrdenarLibrero.nextTimeAfter(27)); //-1 date after the task

        Task taskCalisthenics = new Task("Hacer Barras", 5, 12, 24);//Repetitive

        Assert.assertEquals(-1,taskCalisthenics.nextTimeAfter(8)); //task is not active
        taskCalisthenics.setActive(true);
        Assert.assertEquals(2,taskCalisthenics.nextTimeAfter(3));//task active, date before start
        //Assert.assertEquals(2,taskCalisthenics.nextTimeAfter(3));//task active, between start and end time
        //Assert.assertEquals(2,taskCalisthenics.nextTimeAfter(3));//task active, between start and end time
        Assert.assertEquals(-1,taskCalisthenics.nextTimeAfter(13));//task active, after end time
    }

    public static void main(String[] args) {
        Task taskCalisthenics = new Task("Hacer Barras", 8, 9, 24);

        System.out.println(taskCalisthenics.getTitle());
    }
}
