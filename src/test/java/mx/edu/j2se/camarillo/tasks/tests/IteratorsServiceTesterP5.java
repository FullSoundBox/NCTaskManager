package mx.edu.j2se.camarillo.tasks.tests;

import mx.edu.j2se.camarillo.tasks.ArrayTaskList;
import mx.edu.j2se.camarillo.tasks.LinkedTaskList;
import mx.edu.j2se.camarillo.tasks.Task;
import org.junit.Assert;
import org.junit.Test;
import java.util.Iterator;

public class IteratorsServiceTesterP5 {

    @Test
    public void arrayIteratorsTest(){
        ArrayTaskList taskListOne = new ArrayTaskList();
        Task taskToAdd0 = new Task("task to Add 0",7);
        Task taskToAdd1 = new Task("task to Add 1",9,15,2);
        Task taskToAdd2 = new Task("task to Add 2",15);
        Task taskToAdd3 = new Task("task to Add 3",17,20,1);
        Task taskToAdd4 = new Task("task to Add 4",18);
        Iterator<Task> i1 = taskListOne.iterator();

        Assert.assertFalse(i1.hasNext());

        System.out.println("Primer for each (lista vacia)");
        for (Task task: taskListOne) {
            System.out.println(task);
        }

        System.out.println("Segundo for each con tres tareas");
        taskListOne.add(taskToAdd0);
        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);
        System.out.println();

        for (Iterator<Task> i2 = taskListOne.iterator(); i2.hasNext();) {
            Task item = i2.next();
            System.out.println(item);
        }

        for (Task task: taskListOne) {
            System.out.println(task);
        }
    }

    @Test
    public void linkedIteratorTest(){
        LinkedTaskList taskListOne = new LinkedTaskList();
        Task taskToAdd0 = new Task("task to Add 0",7);
        Task taskToAdd1 = new Task("task to Add 1",9,15,2);
        Task taskToAdd2 = new Task("task to Add 2",15);
        Task taskToAdd3 = new Task("task to Add 3",17,20,1);
        Task taskToAdd4 = new Task("task to Add 4",18);
        Iterator<Task> i1 = taskListOne.iterator();

        Assert.assertFalse(i1.hasNext());
        taskListOne.add(taskToAdd0);
        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);
        Assert.assertTrue(i1.hasNext());

        for(Task task: taskListOne){
            System.out.println(task);
        }
    }

    @Test
    public void taskEqualsTest(){
        Task taskToAdd0 = new Task("task to Add 0",7);
        Task taskToAdd1 = new Task("task to Add 1",9,15,2);
        Task taskToAdd2 = new Task("task to Add 0",7);
        Task taskToAdd3 = new Task("task to Add 1",9,15,2);

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

        Task taskToAdd0 = new Task("task to Add 0",7);
        Task taskToAdd1 = new Task("task to Add 1",9,15,2);
        Task taskToAdd2 = new Task("task to Add 2",15);
        Task taskToAdd3 = new Task("task to Add 3",17,20,1);
        Task taskToAdd4 = new Task("task to Add 4",18);

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
        Task taskToAdd0 = new Task("task to Add 0",7);
        Task taskToAdd1 = new Task("task to Add 1",9,15,2);
        Task taskToAdd2 = new Task("task to Add 2",15);

        //System.out.println(taskListOne);
        //System.out.println();

        taskListOne.add(taskToAdd0);
        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);
        System.out.println(taskListOne);
    }

    @Test
    public void cloneTest(){
        ArrayTaskList taskListOne = new ArrayTaskList();
        ArrayTaskList taskListTwo = new ArrayTaskList();
        LinkedTaskList taskListThree = new LinkedTaskList();
        Task taskToAdd0 = new Task("task to Add 0",7);
        Task taskToAdd1 = new Task("task to Add 1",9,15,2);
        Task taskToAdd2 = new Task("task to Add 2",15);
        Task taskToAdd3 = new Task();
        Task taskToAdd4 = new Task();

        taskToAdd3.clone(taskToAdd0);
        taskToAdd4.clone(taskToAdd1);
        Assert.assertTrue(taskToAdd3.equals(taskToAdd0));
        Assert.assertTrue(taskToAdd4.equals(taskToAdd1));

        taskListOne.add(taskToAdd0);
        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);
        taskListOne.add(taskToAdd3);
        taskListOne.add(taskToAdd4);

        taskListTwo.clone(taskListOne);
        taskListThree.clone(taskListOne);
        Assert.assertTrue(taskListTwo.equals(taskListOne));
        Assert.assertTrue(taskListThree.equals(taskListOne));
    }
}

