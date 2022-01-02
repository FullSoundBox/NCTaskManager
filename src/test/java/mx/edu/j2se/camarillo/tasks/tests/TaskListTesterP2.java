package mx.edu.j2se.camarillo.tasks.tests;

import mx.edu.j2se.camarillo.tasks.Task;
import mx.edu.j2se.camarillo.tasks.ArrayTaskList;
import mx.edu.j2se.camarillo.tasks.AbstractTaskList;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TaskListTesterP2 {
    /**
     * This test verifies proper working of add() method and getTask() method
     */
    @Test
    public void addTaskTest(){
        Task taskToAdd0 = new Task("task to Add 0",
                LocalDateTime.of(2021,1,1,10,0));
        Task taskToAdd1 = new Task("task to Add 1",
                LocalDateTime.of(2021,1, 1,10,0),
                LocalDateTime.of(2021,2, 1,10,0),2);
        Task taskToAdd2 = new Task("task to Add 2",
                LocalDateTime.of(2021,1, 7,12,0));
        Task taskToAdd3 = new Task("task to Add 3",
                LocalDateTime.of(2021,1, 17,10,0),
                LocalDateTime.of(2021,1, 20,10,0),1);
        Task taskToAdd4 = new Task("task to Add 4",
                LocalDateTime.of(2021,1, 18,10,0));
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
        Task taskToAdd0 = new Task("task to Add 0",
                LocalDateTime.of(2021,1,1,10,0));
        Task taskToAdd1 = new Task("task to Add 1",
                LocalDateTime.of(2021,1, 1,10,0),
                LocalDateTime.of(2021,2, 1,10,0),2);
        Task taskToAdd2 = new Task("task to Add 2",
                LocalDateTime.of(2021,1, 7,12,0));
        Task taskToAdd3 = new Task("task to Add 3",
                LocalDateTime.of(2021,1, 17,10,0),
                LocalDateTime.of(2021,1, 20,10,0),1);
        Task taskToAdd4 = new Task("task to Add 4",
                LocalDateTime.of(2021,1, 18,10,0));
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
    public void incomingTester() {
        ArrayTaskList taskListOne = new ArrayTaskList();
        Assert.assertEquals(0,taskListOne.size());

        Task taskToAdd0 = new Task("task to Add 0",
                LocalDateTime.of(2021,1,1,8,0));
        Task taskToAdd1 = new Task("task to Add 1",
                LocalDateTime.of(2021,1, 1,10,0),
                LocalDateTime.of(2021,2, 1,10,0),12);
        Task taskToAdd2 = new Task("task to Add 2",
                LocalDateTime.of(2021,1, 7,12,0));
        Task taskToAdd3 = new Task("task to Add 3",
                LocalDateTime.of(2021,1, 15,10,0),
                LocalDateTime.of(2021,1, 20,10,0),8);
        Task taskToAdd4 = new Task("task to Add 4",
                LocalDateTime.of(2021,1, 21,10,0));

        //Testing incoming tasks
        taskToAdd0.setActive(true);
        taskToAdd1.setActive(true);
        //taskToAdd2.setActive(true);
        taskToAdd3.setActive(true);
        taskToAdd4.setActive(true);

        taskListOne.add(taskToAdd0);
        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);
        taskListOne.add(taskToAdd3);
        taskListOne.add(taskToAdd4);
        Assert.assertEquals(5,taskListOne.size());

        AbstractTaskList incomingTasks;

        incomingTasks = taskListOne.incoming(
                LocalDateTime.of(2021,1,1,9,0),
                LocalDateTime.of(2021,1,18,23,0));

//        taskListOne.getStream().forEach(System.out::println);
//        System.out.println();
//        incomingTasks.getStream().forEach(System.out::println);

        Assert.assertEquals(2,incomingTasks.size());
        Assert.assertEquals(taskToAdd1.toString(),incomingTasks.getTask(0).toString());
        Assert.assertEquals(taskToAdd3.toString(),incomingTasks.getTask(1).toString());
    }
}
