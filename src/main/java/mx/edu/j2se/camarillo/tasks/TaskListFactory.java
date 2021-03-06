package mx.edu.j2se.camarillo.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type){
        if (type==ListTypes.types.ARRAY){
            return new ArrayTaskList();
        } else if (type==ListTypes.types.LINKED) {
            return new LinkedTaskList();
        } else{
            return null;
        }
    }
}
