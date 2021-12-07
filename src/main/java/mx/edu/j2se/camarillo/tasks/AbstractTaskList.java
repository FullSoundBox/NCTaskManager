package mx.edu.j2se.camarillo.tasks;

public abstract class AbstractTaskList {
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);

    public AbstractTaskList incoming(int from, int to){
        AbstractTaskList incomingTasks;
        if (this.getClass()==ArrayTaskList.class)
            incomingTasks = new ArrayTaskList();
        else
            incomingTasks = new LinkedTaskList();

        for (int i=0; i<this.size();i++) {
            if (this.getTask(i).isActive() && this.getTask(i).getStartTime()<=to) {
                incomingTasks.add(this.getTask(i));
            }
        }

        for (int i=0; i<this.size();i++) {
            int j = this.getTask(i).nextTimeAfter(from);
            if(!(j>=from && j<=to)){incomingTasks.remove(this.getTask(i));}
        }
        return incomingTasks;
    }
}
