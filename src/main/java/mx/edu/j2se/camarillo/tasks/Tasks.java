package mx.edu.j2se.camarillo.tasks;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        AbstractTaskList incomingTasks;
        if (tasks.getClass()==LinkedTaskList.class)
            incomingTasks = new LinkedTaskList();
        else
            incomingTasks = new ArrayTaskList();

        try{
            for (Task task: tasks) {
                //System.out.println(task);
                if(task.nextTimeAfter(start)!=null &&
                        task.nextTimeAfter(start).isAfter(start) && task.nextTimeAfter(start).isBefore(end))
                    incomingTasks.add(task);
            }
        }catch(Exception e1) {
            System.out.println(e1);
        }
        return incomingTasks;
    }

    /**
     * Creates a new calendar based on an Iterable object (any Collection) and returns a calendar (TreeMap)
     * based on the tasks withing the specified dates (start, end).
     * @param tasks
     * @param start
     * @param end
     * @return
     */
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        SortedMap<LocalDateTime, Set<Task>> newCalendar = new TreeMap<>();
        try{
            for (Task task: tasks) {
                Set<Task> set = new HashSet<>();
                LocalDateTime dummy = task.nextTimeAfter(start);
                while (dummy!=null && dummy.isAfter(start) && dummy.isBefore(end)) {
                    set.add(task);
                    if (newCalendar.get(dummy)==null)
                        newCalendar.put(dummy,set);
                    else
                        newCalendar.get(dummy).add(task);
                    dummy = dummy.plusHours(task.getRepeatInterval());
                }
            }
        }catch(Exception e1){
            System.out.println(e1);
        }
        return newCalendar;
    }
}
