package mx.edu.j2se.camarillo.tasks.tests;
import mx.edu.j2se.camarillo.tasks.*;
import org.junit.Test;

public class StreamTesterP6 {
    @Test
    public void linkedStreamTest(){
        AbstractTaskList taskListOne = new LinkedTaskList();
        Task taskToAdd0 = new Task("task to Add 0",7);
        Task taskToAdd1 = new Task("task to Add 1",9,15,2);
        Task taskToAdd2 = new Task("task to Add 2",15);
        Task taskToAdd3 = new Task("task to Add 3",17,20,1);
        Task taskToAdd4 = new Task("task to Add 4",18);

        taskListOne.add(taskToAdd0);
        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);
        taskListOne.add(taskToAdd3);
        taskListOne.add(taskToAdd4);

        //Stream<Task> s1 = taskListOne.getStream();

        taskListOne.getStream()
                .forEach(System.out::println);
    }

    @Test
    public void arrayStreamTest(){
        AbstractTaskList taskListOne;
        taskListOne = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        Task taskToAdd0 = new Task("task to Add 0",7);
        Task taskToAdd1 = new Task("task to Add 1",9,15,2);
        Task taskToAdd2 = new Task("task to Add 2",15);
        Task taskToAdd3 = new Task("task to Add 3",17,20,1);
        Task taskToAdd4 = new Task("task to Add 4",18);

        taskListOne.add(taskToAdd0);
        taskListOne.add(taskToAdd1);
        taskListOne.add(taskToAdd2);
        taskListOne.add(taskToAdd3);
        taskListOne.add(taskToAdd4);

        //Stream<Task> s1 = taskListOne.getStream();

        taskListOne.getStream()
                .forEach(System.out::println);
    }
}
