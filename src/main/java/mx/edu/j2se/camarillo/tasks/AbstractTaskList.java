package mx.edu.j2se.camarillo.tasks;

import java.util.Iterator;

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

    public AbstractTaskList incoming(int from, int to){
        AbstractTaskList incomingTasks;
        if (this.getClass()==ArrayTaskList.class)
            incomingTasks = new ArrayTaskList();
        else
            incomingTasks = new LinkedTaskList();

        for (int i=0; i<this.size();i++) {
            int j = this.getTask(i).nextTimeAfter(from);
            if(j>=from && j<=to){incomingTasks.add(this.getTask(i));}
        }
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
        return (int) this.listName.hashCode();
    }
}
