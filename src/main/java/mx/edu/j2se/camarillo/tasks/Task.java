package mx.edu.j2se.camarillo.tasks;
import java.time.LocalDateTime;

public class Task {
	private String taskTitle;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private long taskInterval;
	private boolean taskActive;
	private boolean taskRepeatability;

	public Task(){
		super();
		taskTitle = null;
		startTime = null;
		endTime = null;
		taskInterval = 0;
		taskActive = false;
		taskRepeatability = false;
	}

	/**
	 * Creates a non-repetitive, un-active task.
	 */
	public Task(String title, LocalDateTime time) throws IllegalArgumentException {
		//if (time<0){throw new IllegalArgumentException("Time cannot be a negative number");}

		this.taskTitle = title;
		this.startTime = time;
		this.endTime = time;
		this.taskActive = false;
		this.taskRepeatability = false;
	}

	/**
	 * Creates a repetitive, un-active task
	 */
	public Task(String title, LocalDateTime start, LocalDateTime end, long interval) throws IllegalArgumentException{
		//if (start<0 || end<0) {throw new IllegalArgumentException("Time cannot be a negative number");}
		//if (interval<=0) {throw new IllegalArgumentException("The interval of repetitive tasks should be more than zero");}

		this.taskTitle = title;
		this.startTime = start;
		this.endTime = end;
		this.taskInterval = interval;
		this.taskActive = false;
		this.taskRepeatability = true;
	}

	/**
	 * Returns the task title
	 */
	public String getTitle() {
		return taskTitle;
	}

	/**
	 * Sets the task title.
	 */
	public void setTitle(String title) {
		taskTitle = title;
	}

	/**
	 * Tells if the task is active or not
	 */
	public boolean isActive() {
		return taskActive;
	}

	/**
	 * Sets the state of the tasks. Parameter may be true or false.
	 */
	public void setActive(boolean active) { taskActive = active; }

	/**
	 * Returns the time of the non-repetitive task. If it is repetitive,
	 * returns the start time of the task.
	 */
	public LocalDateTime getTime() {
		return startTime;
	}

	/**
	 * Sets the time of the non-repetitive task. If it is repetitive, the task is turned into
	 * a non-repetitive task.
	 */
	public void setTime(LocalDateTime time) throws IllegalArgumentException{
		//if (time<0){throw new IllegalArgumentException("Time cannot be a negative number");}

		startTime = time;
		endTime = time;
		taskInterval = 0;
		taskRepeatability = false;
	}

	/**
	 * Returns the start time of a repetitive task. If it's a non-repetitive task
	 * it returns the time of the task.
	 */
	public LocalDateTime getStartTime() {
		return startTime;
	}

	/**
	 * Returns the end time of a repetitive task. If it's a non-repetitive task
	 * a warning is returned and getTime() should be used.
	 */
	public LocalDateTime getEndTime() {
		return endTime;
	}

	/**Returns the repetition interval of the repetitive task. If it's a non-repetitive task
	 * a value of zero is returned.
	 */
	public long getRepeatInterval(){
		return taskInterval;
	}

	/**
	 * Sets the start and end time, and also the repetition interval of a repetitive task.
	 * If it is a non-repetitive task, it's turned into a repetitive task.
	 */
	public void setTime(LocalDateTime start, LocalDateTime end, long interval) throws IllegalArgumentException{
		//if (start<0 || end<0) {throw new IllegalArgumentException("Time cannot be a negative number");}
		//if (interval<=0) {throw new IllegalArgumentException("The interval of repetitive tasks should be more than zero");}

		startTime = start;
		endTime = end;
		taskInterval = interval;
		taskRepeatability = true;
	}

	/**
	 * Tells if a task is repetitive or non-repetitive
	 */
	public boolean isRepeated() {
		return taskRepeatability;
	}

	/**
	 * Tells the next execution time of a task given a current time. If the task is
	 * un-active, it returns -1. If after the specified time the task is not executed
	 * anymore, this method returns a -1.
	 */
	public LocalDateTime nextTimeAfter (LocalDateTime current){
		//if (current<=0) { throw new IllegalArgumentException("Current time must be positive");}

		if (!taskActive) {
			return null;
		}
		else{
			if (!taskRepeatability) {
				return (current.isBefore(startTime) ? startTime : null);
			}
			else{
				if(current.isAfter(endTime)){
					return null;
				}
				else {
					LocalDateTime nextTime = startTime;
					while(current.isAfter(nextTime)){nextTime = nextTime.plusHours(taskInterval);}
					return (nextTime);
				}
			}
		}
	}

	public boolean equals(Task task){
		return (this.taskTitle.equals(task.getTitle()) &&
		this.startTime==task.getStartTime() &&
		this.endTime==task.getEndTime() &&
		this.taskInterval==task.getRepeatInterval() &&
		this.taskActive==task.isActive() &&
		this.taskRepeatability==task.isRepeated());
	}

	@Override
	public int hashCode(){
		return this.getTitle().hashCode();
	}

	/**
	 * Copies the parameters of another object
	 */
	public void clone(Task task){
		this.taskTitle=task.getTitle();
		this.startTime=task.getStartTime();
		this.endTime=task.getEndTime();
		this.taskInterval=task.getRepeatInterval();
		this.taskActive=task.isActive();
		this.taskRepeatability=task.isRepeated();
	}

	/**
	 *
	 * Prints the parameters of the object
	 */
	public String toString(){
		String para;
			para = "Title: " + this.taskTitle
					+ ", Start Time: " + this.startTime
					+ ", End Time: " + this.endTime
					+ ", Interval: " + this.taskInterval
					+ ", Active: " + this.taskActive
					+ ", Repetitive: " + this.taskRepeatability;
		return para;
	}
}

