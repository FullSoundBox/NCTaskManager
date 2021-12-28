package mx.edu.j2se.camarillo.tasks;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        AbstractTaskList incomingTasks;
        if (tasks.getClass()==ArrayTaskList.class)
            incomingTasks = new ArrayTaskList();
        else
            incomingTasks = new LinkedTaskList();

        try{
            for (Task task: tasks) {
                LocalDateTime dummy = task.nextTimeAfter(start);
                if(dummy!=null){
                    if (dummy.isAfter(start) && dummy.isBefore(end)) //
                        incomingTasks.add(task);
                }
            }
        }catch(Exception e1) {
            System.out.println(e1);
        }
        return incomingTasks;
    }   //TODO: Null-pointer exceptions could be handled more gracefully

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
                    //System.out.println(dummy + "\t" + set);
                    newCalendar.put(dummy,set);
                    newCalendar.get(dummy).add(task);
                    //System.out.println(newCalendar);
                    dummy = dummy.plusHours(task.getRepeatInterval());
                }
            }
        }catch(Exception e1){
            System.out.println(e1);
        }
        return newCalendar;
    }  // TODO: Check why Morning run is missing
}
