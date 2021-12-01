package mx.edu.j2se.camarillo.tasks;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayTaskList {
    private Task[] taskList = new Task[0];

    /**
     *
     * Looks for undefined task slot in the array, if found, rewrites the parameters of the object
     * with the task to add
     * @param task
     * @throws NullPointerException if the Task reference is empty, this throws a NullPointerException
     */
    public void add(Task task) throws NullPointerException{
        if (task==null) {throw new NullPointerException("Empty link");}
        Task[] dummy = new Task[taskList.length+1];
        System.arraycopy(taskList,0,dummy,0,taskList.length);
        dummy[taskList.length]=task;
        taskList = dummy;
    }

    /**
     * Looks for a task with the same Title and start time. When found, the array element is set to an undefined
     * task, and then returns true. If the task was not there, it returns false.
     * @param task
     * @return true if task was found and erased
     */
    public boolean remove(Task task){
        Task[] dummy = new Task[taskList.length-1];
        for (int i = 0; i < taskList.length; i++) {
            //System.out.println(task);
            //System.out.println("\t" + taskList[i]);
            //System.out.println("\t" + i + "\t" +(taskList[i]==task));
            if (taskList[i]==task){
                System.arraycopy(taskList, 0, dummy, 0, i);
                System.arraycopy(taskList, i+1, dummy, i, dummy.length-i);
                taskList = dummy;
                return true;
            }
        }
        return false;
    }

    /**
     * Looks for not undefined task. If encountered, an acumulator is increased and when finished, returns the
     * amount of task in the array.
     * @return
     */
    public int size(){
        return taskList.length;
    }

    /**
     * Returns a task taken from the array taskList
     * @param index
     * @return taskList[index]
     */
    public Task getTask(int index) throws IndexOutOfBoundsException{
        if (index>taskList.length-1 || index<0) {throw new IndexOutOfBoundsException("Index exceeds permissible limits of the list");}
        return taskList[index];
    }

    /**
     * Creates an ArrayTaskList subset from a given ArrayTaskList, if the tasks are comprehended between
     * "from" time and "to" time
     * @param from
     * @param to
     * @return
     */
    public ArrayTaskList incoming(int from, int to){
        ArrayTaskList incomingTasks = new ArrayTaskList();
        for (Task task: taskList) {
            if (task.isActive() == true && task.getStartTime()<=to) {
                incomingTasks.add(task);
            }
        }

        for (Task task: incomingTasks.taskList) {
            int j = task.nextTimeAfter(from);
            if(!(j>=from && j<=to)){incomingTasks.remove(task);}
        }
        return incomingTasks;
    }
}
