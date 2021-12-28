package mx.edu.j2se.camarillo.tasks.tests;

import mx.edu.j2se.camarillo.tasks.AbstractTaskList;
import mx.edu.j2se.camarillo.tasks.ArrayTaskList;
import mx.edu.j2se.camarillo.tasks.Task;
import mx.edu.j2se.camarillo.tasks.Tasks;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class TasksClassTestP7 {

    /**
     * This test evaluates the correct integration of the LocalDateTime class, it's APIs
     * and the immutability of the startTime and endTime parameters of the Task class
     */
    @Test
    public void localDateTimeTest(){
        LocalDateTime start = LocalDateTime.of(2021,3,1,8,15);
        LocalDateTime end = LocalDateTime.of(2021,9,1,8,15);
        long interval = 24;
        Task run = new Task("Morning run",start,end,interval);

        start.plusDays(10);
        end.minusYears(1);
        Assert.assertEquals(LocalDateTime.of(2021,3,1,8,15),
                run.getStartTime());
        Assert.assertEquals(LocalDateTime.of(2021,9,1,8,15),
                run.getEndTime());

        LocalDateTime otherTime = run.getStartTime();
        otherTime.plusHours(72);
        Assert.assertEquals(LocalDateTime.of(2021,3,1,8,15),
                run.getStartTime());
    }

    /**
     * This test evaluates the proper function of incoming static method (taken from Tasks class). This
     * incoming implementation takes an iterable class (which could be any type of collection) and
     * evaluates if there are any tasks within the specified range.
     * Then the method returns another iterable-class variable.
     */
    @Test
    public void staticIncomingTest(){
        Task lunch = new Task("Lunch with a beautiful girl",
                LocalDateTime.of(2021,8,24,16,0));

        LocalDateTime start = LocalDateTime.of(2021,3,1,8,15);
        LocalDateTime end = LocalDateTime.of(2021,9,1,8,15);
        long interval = 24;
        Task run = new Task("Morning run",start,end,interval);

        Task medication = new Task("Taking medication",
                LocalDateTime.of(2021,8,20,8,15),
                LocalDateTime.of(2021,8,28,0,0),
                12);

        Task friends = new Task("Meeting with friends",
                LocalDateTime.of(2021,9,1,18,0));

        lunch.setActive(true);
        run.setActive(true);
        medication.setActive(true);
        friends.setActive(true);

        ArrayTaskList originalTaskList = new ArrayTaskList();
        originalTaskList.add(lunch);
        originalTaskList.add(run);
        originalTaskList.add(medication);
        originalTaskList.add(friends);

        LocalDateTime from = LocalDateTime.of(2021,8,25,8,0);
        LocalDateTime to = LocalDateTime.of(2021,8,26,8,0);
        ArrayTaskList incomingTaskList = (ArrayTaskList)Tasks.incoming(originalTaskList,from,to);

        incomingTaskList.setListName("incomingTaskList");

        System.out.println(incomingTaskList);
        System.out.println(incomingTaskList.getClass());
    }

    @Test
    public void calendarTest(){
        Task lunch = new Task("Lunch with a beautiful girl",
                LocalDateTime.of(2021,8,24,16,0));

        LocalDateTime start = LocalDateTime.of(2021,3,1,8,15);
        LocalDateTime end = LocalDateTime.of(2021,9,1,8,15);
        long interval = 24;
        Task run = new Task("Morning run",start,end,interval);

        Task medication = new Task("Taking medication",
                LocalDateTime.of(2021,8,20,8,15),
                LocalDateTime.of(2021,8,28,0,0),
                12);

        Task friends = new Task("Meeting with friends",
                LocalDateTime.of(2021,9,1,18,0));

        SortedMap<LocalDateTime, Set<Task>> calendarOne = new TreeMap<>();

        lunch.setActive(true);
        run.setActive(true);
        medication.setActive(true);
        friends.setActive(true);

        AbstractTaskList originalTaskList = new ArrayTaskList();
        originalTaskList.add(lunch);
        originalTaskList.add(run);
        originalTaskList.add(medication);
        originalTaskList.add(friends);

        LocalDateTime from = LocalDateTime.of(2021,8,25,8,0);
        LocalDateTime to = LocalDateTime.of(2021,8,26,8,0);
        calendarOne = Tasks.calendar(originalTaskList,from,to);

//        System.out.println(calendarOne);

        System.out.println();
        for (Map.Entry mapElement : calendarOne.entrySet()) {
            LocalDateTime key = (LocalDateTime) mapElement.getKey();

            // Finding the value
            Set<Task> value = (Set<Task>) mapElement.getValue();

            System.out.println(key + " : " + value);
        }
    }
}
