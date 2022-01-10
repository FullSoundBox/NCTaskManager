package mx.edu.j2se.camarillo.tasks.tests;

import mx.edu.j2se.camarillo.tasks.AbstractTaskList;
import mx.edu.j2se.camarillo.tasks.ArrayTaskList;
import mx.edu.j2se.camarillo.tasks.LinkedTaskList;
import mx.edu.j2se.camarillo.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Iterator;

public class IteratorsServiceTesterP5 {

    @Test
    public void arrayIteratorsTest(){
        ArrayTaskList taskListOne = new ArrayTaskList();
        Task taskToAdd0 = new Task("task to Add 0",
                LocalDateTime.of(2021,1,1,10,0));
        Task taskToAdd1 = new Task("task to Add 1",
                LocalDateTime.of(2021,1, 1,10,0),
                LocalDateTime.of(2021,2, 1,10,0),12);
        Task taskToAdd2 = new Task("task to Add 2",
                LocalDateTime.of(2021,1, 7,12,0));
        Iterator<Task> i1 = taskListOne.iterator();

        Assert.assertFalse(i1.hasNext());
        taskListOne.add(taskToAdd0);
        Assert.assertEquals(taskToAdd0,i1.next());

        System.out.println("Primer for each (lista vacia)");
        for (Task task: taskListOne) {
            System.out.println(task);
        }

        System.out.println("Segundo for each con tres tareas");
        taskListOne.add(taskToAdd0);
        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);

        System.out.println();
        for (Task task: taskListOne) {
            System.out.println(task);
        }
    }

    @Test
    public void linkedIteratorTest(){
        LinkedTaskList taskListOne = new LinkedTaskList();
        Task taskToAdd0 = new Task("task to Add 0",
                LocalDateTime.of(2021,1,1,10,0));
        Task taskToAdd1 = new Task("task to Add 1",
                LocalDateTime.of(2021,1, 1,10,0),
                LocalDateTime.of(2021,2, 1,10,0),12);
        Task taskToAdd2 = new Task("task to Add 2",
                LocalDateTime.of(2021,1, 7,12,0));
        Iterator<Task> i1 = taskListOne.iterator();

        Assert.assertFalse(i1.hasNext());
        taskListOne.add(taskToAdd0);
        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);
        Assert.assertTrue(i1.hasNext());
        Assert.assertEquals(taskToAdd0,i1.next());
        Assert.assertEquals(taskToAdd1,i1.next());
        Assert.assertEquals(taskToAdd2,i1.next());

//        System.out.println();
        for(Task task: taskListOne){
            System.out.println(task);
        }
    }

    @Test
    public void taskEqualsTest(){
        Task taskToAdd0 = new Task("task to Add 0",
                LocalDateTime.of(2021,1,1,10,0));
        Task taskToAdd1 = new Task("task to Add 1",
                LocalDateTime.of(2021,1, 1,10,0),
                LocalDateTime.of(2021,2, 1,10,0),12);
        Task taskToAdd2 = new Task("task to Add 0",
                LocalDateTime.of(2021,1,1,10,0));
        Task taskToAdd3 = new Task("task to Add 1",
                LocalDateTime.of(2021,1, 1,10,0),
                LocalDateTime.of(2021,2, 1,10,0),12);

        Assert.assertFalse(taskToAdd0.equals(taskToAdd1));
        Assert.assertTrue(taskToAdd0.equals(taskToAdd2));
        Assert.assertFalse(taskToAdd1.equals(taskToAdd0));
        Assert.assertTrue(taskToAdd1.equals(taskToAdd3));

        Assert.assertEquals(taskToAdd0.hashCode(),taskToAdd2.hashCode());
        Assert.assertEquals(taskToAdd1.hashCode(),taskToAdd3.hashCode());
        Assert.assertFalse(taskToAdd0.hashCode()==taskToAdd1.hashCode());
        Assert.assertFalse(taskToAdd2.hashCode()==taskToAdd3.hashCode());
    }

    @Test
    public void listEqualsTest(){
        ArrayTaskList taskListOne = new ArrayTaskList();
        ArrayTaskList taskListTwo = new ArrayTaskList();
        LinkedTaskList taskListThree = new LinkedTaskList();

        taskListOne.setListName("taskListOne");
        taskListTwo.setListName("taskListOne");
        taskListThree.setListName("taskListThree");

        Task taskToAdd0 = new Task("task to Add 0",
                LocalDateTime.of(2021,1,1,10,0));
        Task taskToAdd1 = new Task("task to Add 1",
                LocalDateTime.of(2021,1, 1,10,0),
                LocalDateTime.of(2021,2, 1,10,0),12);
        Task taskToAdd2 = new Task("task to Add 2",
                LocalDateTime.of(2021,1, 7,12,0));

        taskListOne.add(taskToAdd0);
        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);

        taskListTwo.add(taskToAdd0);
        taskListTwo.add(taskToAdd1);
        taskListTwo.add(taskToAdd2);

        taskListThree.add(taskToAdd0);
        taskListThree.add(taskToAdd1);
        taskListThree.add(taskToAdd2);

        Assert.assertTrue(taskListOne.equals(taskListTwo));
        Assert.assertTrue(taskListTwo.equals(taskListOne));

        Assert.assertTrue(taskListOne.equals(taskListThree));
        Assert.assertTrue(taskListThree.equals(taskListOne));

        Assert.assertEquals(taskListOne.hashCode(),taskListTwo.hashCode());
        Assert.assertFalse(taskListOne.hashCode()==taskListThree.hashCode());
    }

    @Test
    public void toStringTest(){
        ArrayTaskList taskListOne = new ArrayTaskList();
        taskListOne.setListName("taskListOne");
        Task taskToAdd0 = new Task("task to Add 0",
                LocalDateTime.of(2021,1,1,10,0));
        Task taskToAdd1 = new Task("task to Add 1",
                LocalDateTime.of(2021,1, 1,10,0),
                LocalDateTime.of(2021,2, 1,10,0),12);
        Task taskToAdd2 = new Task("task to Add 2",
                LocalDateTime.of(2021,1, 7,12,0));

        taskListOne.add(taskToAdd0);
        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);
        System.out.println(taskListOne);
    }

    @Test
    public void cloneTest(){
        AbstractTaskList taskListOne = new ArrayTaskList("Original Tasklist");

        AbstractTaskList taskListTwo = new ArrayTaskList("ArrayTasklist copy of Original");
        AbstractTaskList taskListThree = new LinkedTaskList("LinkedTasklist copy of Original");

        Task taskToAdd0 = new Task("task to Add 0",
                LocalDateTime.of(2021,1,1,10,0));
        Task taskToAdd1 = new Task("task to Add 1",
                LocalDateTime.of(2021,1, 1,10,0),
                LocalDateTime.of(2021,2, 1,10,0),12);
        Task taskToAdd2 = new Task("task to Add 2",
                LocalDateTime.of(2021,1, 7,12,0));
        Task taskToAdd3 = new Task();
        Task taskToAdd4 = new Task();

        //Trying to clone a task
        try{
            taskToAdd3 = taskToAdd0.clone();
            taskToAdd4 = taskToAdd1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(taskToAdd3.equals(taskToAdd0));
        Assert.assertTrue(taskToAdd4.equals(taskToAdd1));
        //Verifying we didn't copy just the reference
        taskToAdd0.setTitle("We modify the title");
        taskToAdd0.setTime(LocalDateTime.now());
        taskToAdd1.setTitle("We modify the title too");
        taskToAdd1.setTime(LocalDateTime.now());
        Assert.assertFalse(taskToAdd3.equals(taskToAdd0));//They aren't equal. Thus, they are different instances
        Assert.assertFalse(taskToAdd4.equals(taskToAdd1));

        taskListOne.add(taskToAdd0);
        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);
        taskListOne.add(taskToAdd3);
        taskListOne.add(taskToAdd4);

        try{
            taskListTwo = taskListOne.clone();
            taskListThree = taskListOne.clone();
            Assert.assertTrue(taskListTwo.equals(taskListOne));
            Assert.assertTrue(taskListThree.equals(taskListOne));
            Assert.assertTrue(taskListTwo.equals(taskListThree));
        }catch (CloneNotSupportedException e1){
            //Exception e1 to be handled
        }

        //Verifying we created a new instance and not just copied the reference
        taskListOne.setListName("modified the title");
        taskListOne.remove(taskToAdd2);
        Assert.assertFalse(taskListTwo.equals(taskListOne));
        Assert.assertFalse(taskListThree.equals(taskListOne));
    }
}

