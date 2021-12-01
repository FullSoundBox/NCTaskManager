package mx.edu.j2se.camarillo.tasks;

public class LinkedTaskList {
    private Task[] taskList = new Task[5];  //TODO: redefine methods to fulfill practice requirements

    /**
     *
     * Looks for undefined task slot in the array, if found, rewrites the parameters of the object
     * with the task to add
     * @param task
     */
    public void add(Task task){

    }

    /**
     * Looks for a task with the same Title and start time. When found, the array element is set to an undefined
     * task, and then returns true. If the task was not there, it returns false.
     * @param task
     * @return true if task was found and erased
     */
    public boolean remove(Task task){
        if (true/*found*/)
            return true;
        else
            return false;
    }

    /**
     * Looks for not undefined task. If encountered, an acumulator is increased and when finished, returns the
     * amount of task in the array.
     * @return
     */
    public int size(){
        int numberOfTasks=0;
        return numberOfTasks;
    }

    /**
     * Returns a task taken from the array taskList
     * @param index
     * @return taskList[index]
     */
    public Task getTask(int index){
        return taskList[index];
    }

    public LinkedTaskList incoming(){
        LinkedTaskList lista = new LinkedTaskList();
        return lista;
    }

}
