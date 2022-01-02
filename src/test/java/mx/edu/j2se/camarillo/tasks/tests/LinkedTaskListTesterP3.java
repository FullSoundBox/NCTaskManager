package mx.edu.j2se.camarillo.tasks.tests;

import mx.edu.j2se.camarillo.tasks.LinkedTaskList;
import mx.edu.j2se.camarillo.tasks.AbstractTaskList;
import mx.edu.j2se.camarillo.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class LinkedTaskListTesterP3 {
    /**
     * This test verifies proper working of add() method and getTask() method of LinkedTaskList
     */
    @Test
    public void emptyLinkedListTest(){
        Task taskToAddX = new Task("task to Add X",
                LocalDateTime.of(2021,1,1,9,0));
        Task taskToAddY = new Task("task to Add Y",
                LocalDateTime.of(2021,2,1,9,0));
        LinkedTaskList taskListOne = new LinkedTaskList();
        taskToAddX.setActive(true);
        taskToAddY.setActive(true);

        Assert.assertEquals(0,taskListOne.size());//Empty task list
        Assert.assertFalse(taskListOne.remove(taskToAddX));

        taskListOne.add(taskToAddX);
        Assert.assertFalse(taskListOne.remove(taskToAddY));
        Assert.assertEquals(1,taskListOne.size());
    }

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
        LinkedTaskList taskListOne = new LinkedTaskList();

        taskToAdd0.setActive(true);
        taskToAdd1.setActive(true);
        taskToAdd2.setActive(true);
        taskToAdd3.setActive(true);
        taskToAdd4.setActive(true);

        Assert.assertEquals(0,taskListOne.size());//Empty task list

        taskListOne.add(taskToAdd0);
        Assert.assertEquals(taskToAdd0.toString(),taskListOne.getTask(0).toString());//task 0 must be there
        Assert.assertEquals(1,taskListOne.size());//size must be 1

        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);
        taskListOne.add(taskToAdd3);
        taskListOne.add(taskToAdd4);
        Assert.assertEquals(5,taskListOne.size());//Added 4 more, should be 5

        //Check that all task are there, and they are in order
        Assert.assertEquals(taskToAdd0.toString(),taskListOne.getTask(0).toString());
        Assert.assertEquals(taskToAdd1.toString(),taskListOne.getTask(1).toString());
        Assert.assertEquals(taskToAdd2.toString(),taskListOne.getTask(2).toString());
        Assert.assertEquals(taskToAdd3.toString(),taskListOne.getTask(3).toString());
        Assert.assertEquals(taskToAdd4.toString(),taskListOne.getTask(4).toString());

        //Check that if I modify the original Task, the list should have the changes
        taskToAdd0.setTitle("Testing linking of objects");
        Assert.assertEquals(taskToAdd2.getTitle(),taskListOne.getTask(2).getTitle());
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
        Task taskToAdd5 = new Task("task to Add 4",
                LocalDateTime.of(2021,1, 18,10,0));
        LinkedTaskList taskListOne = new LinkedTaskList();
        Assert.assertEquals(false,taskListOne.remove(taskToAdd0)); //Empty taskList

        taskToAdd0.setActive(true);
        taskToAdd1.setActive(true);
        taskToAdd2.setActive(true);
        taskToAdd3.setActive(true);
        taskToAdd4.setActive(true);

        taskListOne.add(taskToAdd0);
        Assert.assertEquals(taskToAdd0.toString(),taskListOne.getTask(0).toString());
        Assert.assertEquals(1,taskListOne.size());
        taskListOne.remove(taskToAdd0);
        Assert.assertEquals(0,taskListOne.size());//Empty task list again

        taskListOne.add(taskToAdd0);
        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);
        taskListOne.add(taskToAdd3);
        taskListOne.add(taskToAdd4);
        Assert.assertEquals(5,taskListOne.size());
        Assert.assertEquals(taskToAdd0.toString(),taskListOne.getTask(0).toString());
        Assert.assertEquals(taskToAdd1.toString(),taskListOne.getTask(1).toString());
        Assert.assertEquals(taskToAdd2.toString(),taskListOne.getTask(2).toString());
        Assert.assertEquals(taskToAdd3.toString(),taskListOne.getTask(3).toString());
        Assert.assertEquals(taskToAdd4.toString(),taskListOne.getTask(4).toString());
        Assert.assertEquals(false,taskListOne.remove(taskToAdd5)); //Task not found

        taskListOne.remove(taskToAdd0);
        taskListOne.remove(taskToAdd2);
        taskListOne.remove(taskToAdd4);
        Assert.assertEquals(taskToAdd1.toString(),taskListOne.getTask(0).toString());
        Assert.assertEquals(taskToAdd3.toString(),taskListOne.getTask(1).toString());
    }

    @Test
    public void incomingTester(){
        LinkedTaskList taskListOne = new LinkedTaskList();
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

        Assert.assertEquals(taskToAdd0.toString(),taskListOne.getTask(0).toString());
        Assert.assertEquals(taskToAdd1.toString(),taskListOne.getTask(1).toString());
        Assert.assertEquals(taskToAdd2.toString(),taskListOne.getTask(2).toString());
        Assert.assertEquals(taskToAdd3.toString(),taskListOne.getTask(3).toString());
        Assert.assertEquals(taskToAdd4.toString(),taskListOne.getTask(4).toString());

        AbstractTaskList incomingTasks;
        incomingTasks = taskListOne.incoming(
                LocalDateTime.of(2021,1,1,9,0),
                LocalDateTime.of(2021,1,18,23,0));
        /*
        System.out.println();
        System.out.println("Hay " + incomingNineToFive.size() + " Tareas de 9 a 5");
        System.out.println(incomingNineToFive.getTask(0));
        System.out.println(incomingNineToFive.getTask(1));
        System.out.println();*/

        Assert.assertEquals(2,incomingTasks.size());
        Assert.assertEquals(taskToAdd1.toString(),incomingTasks.getTask(0).toString());
        Assert.assertEquals(taskToAdd3.toString(),incomingTasks.getTask(1).toString());
    }
}
