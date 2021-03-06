package mx.edu.j2se.camarillo.tasks;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class LinkedTaskList extends AbstractTaskList{
    private Node first;
    private Node last;

    public LinkedTaskList(){}

    public LinkedTaskList(String taskListName){
        this.setListName(taskListName);
    }

    /**
     *
     * Adds a task to the last node in the linked list
     * @param task
     */
    public void add(Task task) throws NullPointerException{
        if (task==null) throw new NullPointerException();

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
    public boolean remove(Task task) throws NullPointerException{
        if (task==null) throw new NullPointerException();
        Node node = first;

        while(node!=null) {
            if (node.getPointer() == null && node.getValue() == null) {//Empty list
                first = null;
                last = null;
                return false;
            }else if (node.getPointer()==last && node.getPointer().getValue()==task) {//Last item to be removed
                node.setPointer(null);
                last = node;
                return true;
            }else if (node.getValue()==task){
                if (node == first && node.getPointer()==null){
                    first = null;
                    last = null;
                    return true;
                }
                node.setValue(node.getPointer().getValue());
                if (node == first){ first = node.getPointer();}
                return true;
            }
            else{node=node.getPointer();}
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

    @Override
    public Iterator<Task> iterator(){
        return new Iterator<Task>() {
            Node current = new Node();
            @Override
            public boolean hasNext() {
                try{
                    if(current.getValue()==null)
                        current.setPointer(first);
                    if(current.getPointer()==null || current==getLast())
                        return false;
                }
                catch(Exception e1){
                    //System.out.println(e1);
                    return false;
                }
                return true;
            }

            @Override
            public Task next() {
                current=current.getPointer();
                return (Task) current.getValue();
            }
        };
    }

    @Override
    public Stream<Task> getStream(){
        return StreamSupport.stream(this.spliterator(), false);
    }

    /**
     * Returns the first node of the linked list
     * @return Linkedlist first node
     */
    public Node getFirst(){
        return this.first;
    }

    /**
     *
     * @return Linkedlist last node
     */
    public Node getLast(){
        return this.last;
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

        public Node(Node pointer) {
            this.value = null;
            this.pointer = pointer;
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
}
