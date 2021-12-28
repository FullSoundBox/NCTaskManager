package mx.edu.j2se.camarillo.tasks;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.*;

public abstract class AbstractTaskList implements Iterable<Task>{
    private String listName;
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);

    public void setListName(String name){
        this.listName = name;
    }

    public String getListName(){
        return this.listName;
    }

    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to){
        AbstractTaskList incomingTasks;
        if (this.getClass()==ArrayTaskList.class)
            incomingTasks = new ArrayTaskList();
        else
            incomingTasks = new LinkedTaskList();

        /*
        for (int i=0; i<this.size();i++) {
            int j = this.getTask(i).nextTimeAfter(from);
            if(j>=from && j<=to){incomingTasks.add(this.getTask(i));}
        }*/

        try{
            this.getStream()
                    .filter(task -> task.nextTimeAfter(from).isAfter(from) && task.nextTimeAfter(from).isBefore(to))
                    .forEach(incomingTasks::add);
        }catch(Exception e1){}

        return incomingTasks;
    }

    @Override
    public String toString(){
        String para;
        para = listName + "\nSize of the list: " + this.size() + "\n";
        for(Task task: this){
            para+=task.toString()+"\n";
        }
        return para;
    }

    public void clone(AbstractTaskList taskList){
        for (Task task: taskList) {
            this.add(task);
        }
    }

    public boolean equals(AbstractTaskList taskList){
        Iterator i1 = taskList.iterator();
        int index=0;
        for (Task task: this) {
            if(!task.equals(taskList.getTask(index))){
                return false;}
            if (i1.hasNext())
                index++;
            else
                return false;
        }
        return true;
    }

    @Override
    public int hashCode(){
        return this.listName.hashCode();
    }

    public abstract Stream<Task> getStream();
}
