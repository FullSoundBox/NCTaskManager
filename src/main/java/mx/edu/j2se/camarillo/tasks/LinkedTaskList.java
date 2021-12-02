package mx.edu.j2se.camarillo.tasks;

public class LinkedTaskList {
    private Node first;
    private Node last;

    /**
     *
     * Adds a task to the last node in the linked list
     * @param task
     */
    public void add(Task task){
        Node node = new Node();
        node.setValue(task);

        if (size() == 0) {
            this.last = node;
            this.first = node;
        } else {
            this.last.setPointer(node);
            this.last = node;
        }
    }

    /**
     * Looks for a task with the same Title and start time. When found, the array element is set to an undefined
     * task, and then returns true. If the task was not there, it returns false.
     * @param task
     * @return true if task was found and erased
     */
    public boolean remove(Task task){
        int iteration = 0;
        Node node = first;

        while(node!=null) {
            if (node.getPointer() == null && node == first) {//Empty list
                first = null;
                last = null;
                return false;
            } else if (node.getPointer()==last && (Task)node.getPointer().getValue()==task) {
                node.setPointer(null);
                last = node;
                return true;}
            else if ((Task)node.getValue()==task){
                node.setValue(node.getPointer().getValue());
                node.setPointer(node.getPointer());
                if (node == first){
                    this.first = node.getPointer();
                }
                if (node == last){
                    this.last = node;
                }
                node = node.getPointer();
                return true;
            } else{
                node=node.getPointer();
            }
        }
        return false;
    }

    /**
     * Looks for not undefined task. If encountered, an acumulator is increased and when finished, returns the
     * amount of tasks in the list.
     * @return
     */
    public int size(){
        int size = 0;
        Node node = first;
        while (node != null) {
            node = node.getPointer();
            size++;
        }
        return size;
    }

    /**
     * Returns a task taken from the array taskList
     * @param index
     * @return taskList[index]
     */
    public Task getTask(int index){
        int iteration = 0;
        Node node = first;
        while (iteration<index) {
            node = node.getPointer();
            iteration++;
        }
        return (Task)node.getValue();
    }

    public LinkedTaskList incoming(int from, int to){
        LinkedTaskList incomingTasks = new LinkedTaskList();
        Node node = first;
        while(node!=null) {
            if (((Task)node.getValue()).isActive() == true && ((Task)node.getValue()).getStartTime()<=to) {
                incomingTasks.add(((Task)node.getValue()));
            }
            node = node.getPointer();
        }

        for(int i=0;i<incomingTasks.size();i++) {
            int j = incomingTasks.getTask(i).nextTimeAfter(from);
            if(!(j>=from && j<=to)){incomingTasks.remove(incomingTasks.getTask(i));}
        }
        return incomingTasks;
    }

    public class Node {

        private Object value;
        private Node pointer;

        public Node() {
            this.value = null;
            this.pointer = null;
        }

        public Node(Object value) {
            this.value = value;
            this.pointer = null;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Node getPointer() {
            return pointer;
        }

        public void setPointer(Node pointer) {
            this.pointer = pointer;
        }

    }

    public void removeLast(){
        Node node = first;
        while (node != null) {
            if (node.getPointer() == null && node == first) {
                first = null;
                last = null;
                break;
            } else if (node.getPointer().getPointer() == null) {
                node.setPointer(null);
                last = node;
                break;
            }
            node = node.getPointer();
        }
    }
}
