package mx.edu.j2se.camarillo.tasks;

public class ArrayTaskList {
    private Task[] taskList = new Task[5];

    /**
     * Creates an Arrray  of undefined Tasks with Task() constructor
     */
    public ArrayTaskList(){
        for (int i=0; i<taskList.length; i++) {
            taskList[i] = new Task();
        }
    }

    /**
     *
     * Looks for undefined task slot in the array, if found, rewrites the parameters of the object
     * with the task to add
     * @param task
     */
    public void add(Task task){
        for (int i = 0; i < taskList.length; i++) {
            if (taskList[i].getTitle()==null && taskList[i].getStartTime()==0){ //TODO: try to comparing the complete object
                taskList[i].Task(task);
                break;
            }
        }

    }

    /**
     * Looks for a task with the same Title and start time. When found, the array element is set to an undefined
     * task, and then returns true. If the task was not there, it returns false.
     * @param task
     * @return true if task was found and erased
     */
    public boolean remove(Task task){
        for (int i = 0; i < taskList.length; i++) {
            if(taskList[i].getTitle()==task.getTitle()
                    && taskList[i].getStartTime()==task.getTime()) {
                taskList[i] = new Task();
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
        int numberOfTasks=0;
        for (int i=0; i<taskList.length;i++) {
            if (taskList[i].getTitle()!=null && taskList[i].getStartTime()!=0) {
                numberOfTasks++;
            }
        }
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

    /**
     * Creates an ArrayTaskList subset from a given ArrayTaskList, if the tasks are comprehended between
     * "from" time and "to" time
     * @param from
     * @param to
     * @return
     */
    public ArrayTaskList incoming(int from, int to){
        ArrayTaskList incomingTasks = new ArrayTaskList();
        for (int i=0; i<incomingTasks.taskList.length; i++) {
            taskList[i] = new Task();
        }

        for (int i=0 ; i<taskList.length ; i++) {
            if (taskList[i].nextTimeAfter(from)>= from && taskList[i].nextTimeAfter(from)<=to){
                incomingTasks.add(taskList[i]);
            }
        }
        return incomingTasks;
    }
}
