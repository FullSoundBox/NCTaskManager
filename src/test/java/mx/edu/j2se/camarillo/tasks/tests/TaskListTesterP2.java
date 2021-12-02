package mx.edu.j2se.camarillo.tasks.tests;

import mx.edu.j2se.camarillo.tasks.Task;
import mx.edu.j2se.camarillo.tasks.ArrayTaskList;
import org.junit.Assert;
import org.junit.Test;

public class TaskListTesterP2 {
    /**
     * This test verifies proper working of add() method and getTask() method
     */
    @Test
    public void addTaskTest(){
        Task taskToAdd0 = new Task("task to Add 1",7);
        Task taskToAdd1 = new Task("task to Add 2",9,15,2);
        Task taskToAdd2 = new Task("task to Add 3",15);
        Task taskToAdd3 = new Task("task to Add 4",17,20,1);
        Task taskToAdd4 = new Task("task to Add 5",18);
        ArrayTaskList taskListOne = new ArrayTaskList();

        taskToAdd0.setActive(true);
        taskToAdd1.setActive(true);
        taskToAdd2.setActive(true);
        taskToAdd3.setActive(true);
        taskToAdd4.setActive(true);

        taskListOne.add(taskToAdd0);
        Assert.assertEquals(taskToAdd0.toString(),taskListOne.getTask(0).toString());
    }

    @Test
    public void removeTaskTest(){
        Task taskToAdd0 = new Task("task to Add 0",7);
        Task taskToAdd1 = new Task("task to Add 1",9,15,2);
        Task taskToAdd2 = new Task("task to Add 2",15);
        Task taskToAdd3 = new Task("task to Add 3",17,20,1);
        Task taskToAdd4 = new Task("task to Add 4",18);
        ArrayTaskList taskListOne = new ArrayTaskList();

        taskToAdd0.setActive(true);
        taskToAdd1.setActive(true);
        taskToAdd2.setActive(true);
        taskToAdd3.setActive(true);
        taskToAdd4.setActive(true);

        Assert.assertEquals(0,taskListOne.size());
        taskListOne.add(taskToAdd0);
        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);
        taskListOne.add(taskToAdd3);
        Assert.assertEquals(taskToAdd0.toString(),taskListOne.getTask(0).toString());
        Assert.assertEquals(4,taskListOne.size());
        taskListOne.remove(taskToAdd0);
        Assert.assertEquals(3,taskListOne.size());
        //taskToAdd1 should be in 0
        Assert.assertEquals(taskToAdd1.toString(),taskListOne.getTask(0).toString());

    }

    @Test
    public void incomingTester(){
        ArrayTaskList taskListOne = new ArrayTaskList();
        Assert.assertEquals(0,taskListOne.size());

        Task taskToAdd1 = new Task("task to Add 1",7);
        Task taskToAdd2 = new Task("task to Add 2",9,15,2);
        Task taskToAdd3 = new Task("task to Add 3",15);
        Task taskToAdd4 = new Task("task to Add 4",17,20,1);
        Task taskToAdd5 = new Task("task to Add 5",18);

        //Testing incoming tasks
        taskToAdd1.setActive(true);
        taskToAdd2.setActive(true);
        //taskToAdd3.setActive(true);
        taskToAdd4.setActive(true);
        taskToAdd5.setActive(true);
        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);
        taskListOne.add(taskToAdd3);
        taskListOne.add(taskToAdd4);
        taskListOne.add(taskToAdd5);
        Assert.assertEquals(5,taskListOne.size());

        ArrayTaskList incomingNineToFive = new ArrayTaskList();
        incomingNineToFive = taskListOne.incoming(9,17);
        /*
        System.out.println();
        System.out.println("Hay " + incomingNineToFive.size() + " Tareas de 9 a 5");
        System.out.println(incomingNineToFive.getTask(0));
        System.out.println(incomingNineToFive.getTask(1));*/

        Assert.assertEquals(2,incomingNineToFive.size());
        Assert.assertEquals(taskToAdd2.toString(),incomingNineToFive.getTask(0).toString());
        Assert.assertEquals(taskToAdd4.toString(),incomingNineToFive.getTask(1).toString());
    }
}
