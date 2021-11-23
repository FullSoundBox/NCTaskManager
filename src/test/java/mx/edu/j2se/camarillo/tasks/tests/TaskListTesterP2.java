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
        Assert.assertEquals(1,taskListOne.size());
        taskListOne.remove(taskToAdd0);
        Assert.assertEquals(0,taskListOne.size());
    }

    public static void main(String[] args) {
        ArrayTaskList taskListOne = new ArrayTaskList();
        System.out.println("Number of tasks in the array: " + taskListOne.size());

        Task taskToAdd1 = new Task("task to Add 1",7);
        Task taskToAdd2 = new Task("task to Add 2",9,15,2);
        Task taskToAdd3 = new Task("task to Add 3",15);
        Task taskToAdd4 = new Task("task to Add 4",17,20,1);
        Task taskToAdd5 = new Task("task to Add 5",18);

        //Testing incoming tasks
        taskToAdd1.setActive(true);
        taskToAdd2.setActive(true);
        taskToAdd3.setActive(true);
        taskToAdd4.setActive(true);
        taskToAdd5.setActive(true);
        ArrayTaskList incomingNineToFive = new ArrayTaskList();
        //incomingNineToFive = taskListOne.incoming(9,17);

        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);
        taskListOne.add(taskToAdd3);
        taskListOne.add(taskToAdd4);
        taskListOne.add(taskToAdd5);

        //System.out.println(taskListOne.getTask(4).nextTimeAfter(9));
        //System.out.println(taskListOne.getTask(4).nextTimeAfter(17));
        System.out.println();
        System.out.println("\n\n");
        taskListOne.incoming(9,17);

        System.out.println("\n\n");
        System.out.println(incomingNineToFive.size());
        System.out.println(incomingNineToFive.getTask(0));
        System.out.println(incomingNineToFive.getTask(1));
        System.out.println(incomingNineToFive.getTask(2));
        System.out.println(incomingNineToFive.getTask(3));
        System.out.println(incomingNineToFive.getTask(4));
        /*
        for (int i = 0; i < taskListOne.; i++) {
            System.out.println(taskListOne.getTask().toString());
        }*/
    }
}
