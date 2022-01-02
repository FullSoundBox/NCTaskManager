package mx.edu.j2se.camarillo.tasks.tests;
import mx.edu.j2se.camarillo.tasks.*;
import org.junit.Test;

import java.time.LocalDateTime;

public class StreamTesterP6 {
    @Test
    public void linkedStreamTest(){
        AbstractTaskList taskListOne = new LinkedTaskList();
        Task taskToAdd0 = new Task("task to Add 0",
                LocalDateTime.of(2021,1,1,10,0));
        Task taskToAdd1 = new Task("task to Add 1",
                LocalDateTime.of(2021,1, 1,10,0),
                LocalDateTime.of(2021,2, 1,10,0),12);
        Task taskToAdd2 = new Task("task to Add 2",
                LocalDateTime.of(2021,1, 7,12,0));
        Task taskToAdd3 = new Task("task to Add 3",
                LocalDateTime.of(2021,1, 17,10,0),
                LocalDateTime.of(2021,1, 20,10,0),8);
        Task taskToAdd4 = new Task("task to Add 4",
                LocalDateTime.of(2021,1, 18,10,0));

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
        Task taskToAdd0 = new Task("task to Add 0",
                LocalDateTime.of(2021,1,1,10,0));
        Task taskToAdd1 = new Task("task to Add 1",
                LocalDateTime.of(2021,1, 1,10,0),
                LocalDateTime.of(2021,2, 1,10,0),12);
        Task taskToAdd2 = new Task("task to Add 2",
                LocalDateTime.of(2021,1, 7,12,0));
        Task taskToAdd3 = new Task("task to Add 3",
                LocalDateTime.of(2021,1, 17,10,0),
                LocalDateTime.of(2021,1, 20,10,0),8);
        Task taskToAdd4 = new Task("task to Add 4",
                LocalDateTime.of(2021,1, 18,10,0));

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
