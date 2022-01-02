package mx.edu.j2se.camarillo.tasks.tests;

import mx.edu.j2se.camarillo.tasks.*;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.*;

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

        //Testing ArrayTaskList
        ArrayTaskList originalTaskList = new ArrayTaskList();
        originalTaskList.add(lunch);
        originalTaskList.add(run);
        originalTaskList.add(medication);
        originalTaskList.add(friends);

        LocalDateTime from = LocalDateTime.of(2021,8,25,8,0);
        LocalDateTime to = LocalDateTime.of(2021,8,27,8,0);
        ArrayTaskList incomingTaskList = (ArrayTaskList)Tasks.incoming(originalTaskList,from,to);
        incomingTaskList.setListName("incomingTaskList");

        Assert.assertEquals(2,incomingTaskList.size());
        Assert.assertEquals(run,incomingTaskList.getTask(0));
        Assert.assertEquals(medication,incomingTaskList.getTask(1));//Wasn't appearing because of the interval

        //Testing LinkedTaskList
        LinkedTaskList originalLinkedTaskList = new LinkedTaskList();
        originalLinkedTaskList.add(lunch);
        originalLinkedTaskList.add(run);
        originalLinkedTaskList.add(medication);
        originalLinkedTaskList.add(friends);

        LinkedTaskList incomingTaskList2 = (LinkedTaskList)Tasks.incoming(originalLinkedTaskList,from,to);
        Assert.assertEquals(2,incomingTaskList2.size());
        Assert.assertEquals(run,incomingTaskList.getTask(0));
        Assert.assertEquals(medication,incomingTaskList.getTask(1));

        //Testing ArrayList
        ArrayList<Task> arrayList = new ArrayList<>();
        arrayList.add(lunch);
        arrayList.add(run);
        arrayList.add(medication);
        arrayList.add(friends);
//        ArrayList incomingTaskList3 = (ArrayList)Tasks.incoming(originalTaskList,from,to);
        ArrayTaskList incomingTaskList3 = (ArrayTaskList)Tasks.incoming(arrayList,from,to);
        Assert.assertEquals(2,incomingTaskList.size());
        Assert.assertEquals(run,incomingTaskList3.getTask(0));
        Assert.assertEquals(medication,incomingTaskList3.getTask(1));

        //Testing LinkedList
        LinkedList<Task> linkedList = new LinkedList<>();
        linkedList.add(lunch);
        linkedList.add(run);
        linkedList.add(medication);
        linkedList.add(friends);
//        ArrayList incomingTaskList4 = (ArrayList)Tasks.incoming(originalTaskList,from,to);
        ArrayTaskList incomingTaskList4 = (ArrayTaskList)Tasks.incoming(linkedList,from,to);
        Assert.assertEquals(2,incomingTaskList.size());
        Assert.assertEquals(run,incomingTaskList4.getTask(0));
        Assert.assertEquals(medication,incomingTaskList4.getTask(1));
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

        //Testing ArrayTaskList
        AbstractTaskList originalTaskList = new ArrayTaskList();
        originalTaskList.add(lunch);
        originalTaskList.add(run);
        originalTaskList.add(medication);
        originalTaskList.add(friends);

        //Testing LinkedTaskList
        LinkedTaskList originalLinkedTaskList = new LinkedTaskList();
        originalLinkedTaskList.add(lunch);
        originalLinkedTaskList.add(run);
        originalLinkedTaskList.add(medication);
        originalLinkedTaskList.add(friends);

        //Testing ArrayList
        ArrayList<Task> arrayList = new ArrayList<>();
        arrayList.add(lunch);
        arrayList.add(run);
        arrayList.add(medication);
        arrayList.add(friends);

        //Testing LinkedList
        LinkedList<Task> linkedList = new LinkedList<>();
        linkedList.add(lunch);
        linkedList.add(run);
        linkedList.add(medication);
        linkedList.add(friends);

        LocalDateTime from = LocalDateTime.of(2021,8,25,8,0);
        LocalDateTime to = LocalDateTime.of(2021,8,26,8,0);
//        calendarOne = Tasks.calendar(originalTaskList,from,to);
//        calendarOne = Tasks.calendar(originalLinkedTaskList,from,to);
//        calendarOne = Tasks.calendar(arrayList,from,to);
        calendarOne = Tasks.calendar(linkedList,from,to);

        for (Map.Entry mapElement : calendarOne.entrySet()) {
            LocalDateTime key = (LocalDateTime) mapElement.getKey();

            // Finding the value
            Set<Task> value = (Set<Task>) mapElement.getValue();

            System.out.println(key + " : " + value);
        }
    }
}
