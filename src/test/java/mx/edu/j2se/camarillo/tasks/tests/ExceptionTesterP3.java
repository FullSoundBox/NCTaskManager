package mx.edu.j2se.camarillo.tasks.tests;

import mx.edu.j2se.camarillo.tasks.ArrayTaskList;
import mx.edu.j2se.camarillo.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

/**
 * Testing the exceptions thrown when a negative time is used in a task constructor, or when trying
 * to set a time with setTime() method.
 */
public class ExceptionTesterP3 {
    @Test (expected = IllegalArgumentException.class)
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
    public void intervalNotPosException(){
        //Interval less or equal to zero
        Task dummyTask = new Task("Dummy Rep Task",1, 1,-1);
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

}
