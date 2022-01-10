package mx.edu.j2se.camarillo.tasks.tests;

import mx.edu.j2se.camarillo.tasks.ArrayTaskList;
import mx.edu.j2se.camarillo.tasks.Task;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Testing the exceptions thrown when a negative time is used in a task constructor, or when trying
 * to set a time with setTime() method.
 * @version 2.0
 */
public class ExceptionTesterP3 {
    @Test (expected = IllegalArgumentException.class)
    public void invalidIntervalException(){
        Task dummyTask = new Task("Negative Interval Task",
                LocalDateTime.of(2021,1,1,9,0),
                LocalDateTime.of(2021,1,1,10,0),
                -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void invalidIntervalException2(){
        Task dummyTask = new Task("Negative Interval Task",
                LocalDateTime.of(2021,1,1,9,0),
                LocalDateTime.of(2021,1,1,10,0),
                1);

        dummyTask.setTime(
                LocalDateTime.of(2021,1,1,9,0),
                LocalDateTime.of(2021,1,1,10,0),
                -1);
    }

    @Test (expected = NullPointerException.class)
    public void nullDatesException(){
        LocalDateTime start = null;
        LocalDateTime end = null;
        Task dummyTask = new Task("Null dates Task",start,end, 1);
    }

    @Test (expected = NullPointerException.class)
    public void nullDatesException2(){
        LocalDateTime start = null;
        Task dummyTask = new Task("Null dates Task",start);
    }

    @Test (expected = NullPointerException.class)
    public void nullDatesException3(){
        LocalDateTime start = LocalDateTime.of(2021,1,1,9,0);
        Task dummyTask = new Task("Null dates Task",start);
        start = null;
        dummyTask.setTime(start);
    }

    @Test (expected = NullPointerException.class)
    public void nullDatesException4(){
        LocalDateTime current = null;
        Task dummyTask = new Task("Null dates Task",
                LocalDateTime.of(2021,1,1,9,0));
        current = null;
        dummyTask.nextTimeAfter(current);
    }

    @Test (expected = IllegalArgumentException.class)
    public void invalidDatesException(){
        LocalDateTime start = LocalDateTime.of(2021,2,1,10,0);
        LocalDateTime end = LocalDateTime.of(2021,1,1,10,0);
        Task dummyTask = new Task("Invalid dates Task",start,end, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void invalidDatesException2(){
        LocalDateTime start = LocalDateTime.of(2021,2,1,10,0);
        LocalDateTime end = LocalDateTime.of(2021,1,1,10,0);
        Task dummyTask = new Task("Invalid dates Task",
                LocalDateTime.of(2021,1,1,10,0),
                LocalDateTime.of(2021,2,1,10,0), 1);

        dummyTask.setTime(start,end,1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void outOfBounds(){
        ArrayTaskList dummyTaskList = new ArrayTaskList();
        dummyTaskList.getTask(5);
    }

    @Test (expected = NullPointerException.class)
    public void nullTaskLink(){
        ArrayTaskList dummyTaskList = new ArrayTaskList();
        dummyTaskList.add(null);
    }

    @Test (expected = NullPointerException.class)
    public void nullTaskLink2(){
        Task dummyTaskToClone;
        dummyTaskToClone = null;
        Task dummyTask = new Task();
        try{
            dummyTask = dummyTaskToClone.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = NullPointerException.class)
    public void nullTaskLink3(){
        Task dummyTaskToCompare;
        dummyTaskToCompare = null;

        Task dummyTask = new Task();
        dummyTask.equals(dummyTaskToCompare);
    }

    /*
    @Test (expected = IllegalArgumentException.class)
    @Deprecated
    public void negTimeException1(){
        //Negative time is used, constructing non-repetitive task
        Task dummyTask = new Task("Dummy",-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void negTimeException2(){
        //Negative startTime is used, constructing repetitive task
        Task dummyTask = new Task("Dummy",-1,1,1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void negTimeException3(){
        //Negative endTime is used, constructing repetitive task
        Task dummyTask = new Task("Dummy",1,-1,1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void negTimeSetterException(){
        Task dummyTask = new Task("Dummy",9);
        dummyTask.setTime(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void negTimeSetterException2(){
        Task dummyTask = new Task("Dummy",9);
        dummyTask.setTime(-1,1,1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void negTimeSetterException3(){
        Task dummyTask = new Task("Dummy",9);
        dummyTask.setTime(1,-1,1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void intervalNotPos(){
        Task dummyTask = new Task("Dummy",1,1,1);
        dummyTask.setTime(1,1,-1);
    }

    */

}
