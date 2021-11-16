package mx.edu.j2se.camarillo.tasks;

public class Task {
	String taskTitle;
	int startTime;
	int endTime;
	int taskInterval;
	boolean taskActive;
	boolean taskRepeatability;

	public Task(){
		super();
		taskTitle = null;
		startTime = 0;
		endTime = 0;
		taskInterval = 0;
		taskActive = false;
		taskRepeatability = false;
	}

	public Task(String title, int time) {
		/**
		 * Creates a non-repetitive, un-active task.
		 */
		this.taskTitle = title;
		this.startTime = time;
		this.endTime = time;
		this.taskActive = false;
		this.taskRepeatability = false;
	}

	public Task(String title, int start, int end, int interval) {
		/**
		 * Creates a repetitive, un-active task
		 */
		this.taskTitle = title;
		this.startTime = start;
		this.endTime = end;
		this.taskInterval = interval;
		this.taskActive = false;
		this.taskRepeatability = true;
	}

	public String getTitle() {
		/**
		 * Returns the task title
		 */
		return taskTitle;
	}

	public void setTitle(String title) {
		/**
		 * Sets the task title.
		 */
		taskTitle = title;
	}

	public boolean isActive() {
		/**
		 * Tells if the task is active or not
		 */
		return taskActive;
	}

	public void setActive(boolean active) {
		/**
		 * Sets the state of the tasks. Parameter may be true or false.
		 */
		taskActive = active;
	}

	public int getTime() {
		/**
		 * Returns the time of the non-repetitive task. If it is repetitive,
		 * returns the start time of the task.
		 */
		return startTime;
	}

	public void setTime(int time) {
		/**
		 * Sets the time of the non-repetitive task. If it is repetitive, the task is turned into
		 * a non-repetitive task.
		 */
		startTime = time;
		endTime = time;
		taskInterval = 0;
		taskRepeatability = false;
	}

	public int getStartTime() {
		/**
		 * Returns the start time of a repetitive task. If it's a non-repetitive task
		 * it returns the time of the task.
		 */
		return startTime;
	}

	public int getEndTime() {
		/**
		 * Returns the end time of a repetitive task. If it's a non-repetitive task
		 * a warning is returned and getTime() should be used.
		 */
		return endTime;
	}

	public int getRepeatInterval(){
		/**Returns the repetition interval of the repetitive task. If it's a non-repetitive task
		 * a value of zero is returned.
		 */
		return 0;
	}

	public void setTime(int start, int end, int interval) {
		/**
		 * Sets the start and end time, and aslo the repetition interval of a repetitive task.
		 * If it is a non-repetitive task, it's turned into a repetitive task.
		 */
		startTime = start;
		endTime = end;
		taskInterval = interval;
		taskRepeatability = true;
	}

	public boolean isRepeated() {
		/**
		 * Tells if a task is repetitive or non-repetitive
		 */
		return taskRepeatability;
	}

	public int nextTimeAfter (int current){
		/**
		 * Tells the next execution time of a task given a current time. If the task is
		 * un-active, it returns -1. If after the specified time the task is not executed
		 * anymore, this method returns a -1.
		 */
		if (taskActive == false) {
			return -1;
		}
		else{
			if (taskRepeatability==false) {
				return ((startTime - current) >= 0) ? (startTime - current) : -1;
			}
			else{
				return ((startTime - current) < 0 || (endTime - current) < 0) ? -1 :(startTime - current);
			}
		}
	}
	public static void main(String[] args) {

	}
}

