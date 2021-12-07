package mx.edu.j2se.camarillo.tasks.tests;

import mx.edu.j2se.camarillo.tasks.*;

import org.junit.Assert;
import org.junit.Test;

public class AbstractTaskListTestP4 {
    @Test
    public void arrayTaskListTest(){
        AbstractTaskList arrayTaskListOne = new ArrayTaskList();

        Task taskToAdd0 = new Task("task to Add 0",7);
        Task taskToAdd1 = new Task("task to Add 1",9,15,2);
        Task taskToAdd2 = new Task("task to Add 2",15);
        Task taskToAdd3 = new Task("task to Add 3",17,20,1);
        Task taskToAdd4 = new Task("task to Add 4",18);

        taskToAdd0.setActive(true);
        taskToAdd1.setActive(true);
        //taskToAdd2.setActive(true);
        taskToAdd3.setActive(true);
        taskToAdd4.setActive(true);

        //Testing size()
        Assert.assertEquals(0,arrayTaskListOne.size());

        //Testing add()
        arrayTaskListOne.add(taskToAdd0);
        arrayTaskListOne.add(taskToAdd1);
        arrayTaskListOne.add(taskToAdd2);
        arrayTaskListOne.add(taskToAdd3);
        arrayTaskListOne.add(taskToAdd4);
        Assert.assertEquals(5,arrayTaskListOne.size());

        //Testing remove()
        arrayTaskListOne.remove(taskToAdd4);
        Assert.assertEquals(4,arrayTaskListOne.size());
        arrayTaskListOne.add(taskToAdd4);

        //Testing incoming()
        AbstractTaskList incomingNineToFive = arrayTaskListOne.incoming(9,17);
        Assert.assertEquals(ArrayTaskList.class,incomingNineToFive.getClass());
        Assert.assertEquals(2,incomingNineToFive.size());
        Assert.assertEquals(taskToAdd1.toString(),incomingNineToFive.getTask(0).toString());
        Assert.assertEquals(taskToAdd3.toString(),incomingNineToFive.getTask(1).toString());
    }

    @Test
    public void linkedTaskListTest(){
        AbstractTaskList linkedTaskListOne = new LinkedTaskList();

        Task taskToAdd0 = new Task("task to Add 0",7);
        Task taskToAdd1 = new Task("task to Add 1",9,15,2);
        Task taskToAdd2 = new Task("task to Add 2",15);
        Task taskToAdd3 = new Task("task to Add 3",17,20,1);
        Task taskToAdd4 = new Task("task to Add 4",18);

        taskToAdd0.setActive(true);
        taskToAdd1.setActive(true);
        //taskToAdd2.setActive(true);
        taskToAdd3.setActive(true);
        taskToAdd4.setActive(true);

        //Testing size()
        Assert.assertEquals(0,linkedTaskListOne.size());

        //Testing add()
        linkedTaskListOne.add(taskToAdd0);
        linkedTaskListOne.add(taskToAdd1);
        linkedTaskListOne.add(taskToAdd2);
        linkedTaskListOne.add(taskToAdd3);
        linkedTaskListOne.add(taskToAdd4);
        Assert.assertEquals(5,linkedTaskListOne.size());

        //Testing remove()
        linkedTaskListOne.remove(taskToAdd4);
        Assert.assertEquals(4,linkedTaskListOne.size());
        linkedTaskListOne.add(taskToAdd4);

        //Testing incoming()
        AbstractTaskList incomingNineToFive = linkedTaskListOne.incoming(9,17);
        Assert.assertEquals(LinkedTaskList.class,incomingNineToFive.getClass());
        Assert.assertEquals(2,incomingNineToFive.size());
        Assert.assertEquals(taskToAdd1.toString(),incomingNineToFive.getTask(0).toString());
        Assert.assertEquals(taskToAdd3.toString(),incomingNineToFive.getTask(1).toString());
    }

    @Test
    public void abstractFactoryTest(){
        AbstractTaskList arrayTaskList;
        arrayTaskList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        Assert.assertEquals(ArrayTaskList.class,arrayTaskList.getClass());

        AbstractTaskList linkedTaskList;
        linkedTaskList = TaskListFactory.createTaskList(ListTypes.types.LINKED);
        Assert.assertEquals(LinkedTaskList.class,linkedTaskList.getClass());
    }
}
