package mx.edu.j2se.camarillo.tasks;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This class will be the basic unit of this project. Tasks have some descriptive test
 * that tells us details about it. They can be active or un-active, and they have a startTime and
 * an endTime. Also, tasks can be repetitive or non-repetitive. Further we implement methods to
 * work with tasks execution times and to manipulate them.
 * @author Abraham Camarillo
 */
public class Task implements Serializable, Cloneable {
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
	 * @param time use a LocalDateTime with this format (YYYY/MM/DD/HH/MM)
	 * @autor Abraham Camarillo
	 */
	public Task(String title, LocalDateTime time){
		if (time==null){throw new NullPointerException("Date is null");}

		this.taskTitle = title;
		this.startTime = LocalDateTime.of(
				time.getYear(),time.getMonthValue(),time.getDayOfMonth(),time.getHour(),time.getMinute());
		this.endTime = LocalDateTime.of(
				time.getYear(),time.getMonthValue(),time.getDayOfMonth(),time.getHour(),time.getMinute());
		this.taskActive = false;
		this.taskRepeatability = false;
	}

	/**
	 * Creates a repetitive, un-active task
	 * @param start use a LocalDateTime with this format (YYYY/MM/DD/HH/MM)
	 * @param end use a LocalDateTime with this format (YYYY/MM/DD/HH/MM)
	 * @param interval represented in hours
	 * @author Abraham Camarillo
	 */
	public Task(String title, LocalDateTime start, LocalDateTime end, long interval) throws IllegalArgumentException{
		if (start==null || end==null) {throw new NullPointerException("Dates can't be null");}
		if (interval<=0) {throw new IllegalArgumentException("The interval of repetitive tasks should be positive");}
		if (start.isAfter(end)) {throw new IllegalArgumentException("Start date can't be after end date");}

		this.taskTitle = title;
		this.startTime = LocalDateTime.of(
				start.getYear(),start.getMonthValue(),start.getDayOfMonth(),start.getHour(),start.getMinute());
		this.endTime = LocalDateTime.of(
				end.getYear(),end.getMonthValue(),end.getDayOfMonth(),end.getHour(),end.getMinute());
		this.taskInterval = interval;
		this.taskActive = false;
		this.taskRepeatability = true;
	}

	/**
	 * Returns the task title
	 * @return String with the task title in no specific format
	 * @author Abraham Camarillo
	 */
	public String getTitle() {
		return taskTitle;
	}

	/**
	 * Sets the task title.
	 * @author Abraham Camarillo
	 */
	public void setTitle(String title) {
		taskTitle = title;
	}

	/**
	 * Tells if the task is active or not
	 * @return boolean value if the task is to be executed or not
	 * @author Abraham Camarillo
	 */
	public boolean isActive() {
		return taskActive;
	}

	/**
	 * Sets the state of the tasks. Parameter may be true or false.
	 * @author Abraham Camarillo
	 */
	public void setActive(boolean active) { taskActive = active; }

	/**
	 * Returns the time of the non-repetitive task. If it is repetitive,
	 * returns the start time of the task.
	 * @return LocalDateTime object with the format (YYYY/MM/DD/HH/MM)
	 * @author Abraham Camarillo
	 */
	public LocalDateTime getTime() {
		return startTime;
	}

	/**
	 * Sets the time of the non-repetitive task. If it is repetitive, the task is turned into
	 * a non-repetitive task.
	 * @throws IllegalArgumentException
	 * @author Abraham Camarillo
	 */
	public void setTime(LocalDateTime time) throws IllegalArgumentException{
		if (time==null){throw new NullPointerException("Time is null");}

		startTime = LocalDateTime.of(
				time.getYear(),time.getMonthValue(),time.getDayOfMonth(),time.getHour(),time.getMinute());
		endTime = LocalDateTime.of(
				time.getYear(),time.getMonthValue(),time.getDayOfMonth(),time.getHour(),time.getMinute());
		taskInterval = 0;
		taskRepeatability = false;
	}

	/**
	 * Returns the start time of a repetitive task. If it's a non-repetitive task
	 * it returns the time of the task.
	 * @return LocalDateTime object with the format (YYYY/MM/DD/HH/MM)
	 * @author Abraham Camarillo
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
	 * @throws IllegalArgumentException if startTime is after endTime or if interval is not positive
	 */
	public void setTime(LocalDateTime start, LocalDateTime end, long interval) throws IllegalArgumentException{
		if (start==null || end==null) {throw new NullPointerException("Dates can't be null");}
		if (interval<=0) {throw new IllegalArgumentException("The interval of repetitive tasks should be positive");}
		if (start.isAfter(end)) {throw new IllegalArgumentException("Start date can't be after end date");}

		startTime = LocalDateTime.of(
				start.getYear(),start.getMonthValue(),start.getDayOfMonth(),start.getHour(),start.getMinute());
		endTime = LocalDateTime.of(
				end.getYear(),end.getMonthValue(),end.getDayOfMonth(),end.getHour(),end.getMinute());
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
	 * un-active, it returns null. If after the specified time the task is not executed
	 * anymore, this method returns null.
	 * @param current date of reference
	 * @return next date task is executed, null if is not
	 */
	public LocalDateTime nextTimeAfter (LocalDateTime current){
		if (current==null) { throw new NullPointerException("Current date is null");}

		if (!taskActive) {
			return null;
		}
		else{
			if (!taskRepeatability) {
				return (current.isBefore(startTime) ? startTime: null);
			}
			else{
				if(current.isAfter(endTime) || current.isEqual(endTime)){
					return null;
				}
				else {
					LocalDateTime nextTime = startTime;
					while (!nextTime.isAfter(current)){
						//System.out.println(nextTime);
						nextTime = nextTime.plusHours(taskInterval);
					}
					return nextTime;
//					return current.plusHours(taskInterval);
				}
			}
		}
	}

	/**
	 * Determines if two tasks are equal, that is to say, they have identical parameters
 	 * @param task Not null task to compare
	 * @return true if they have the same parameters, false if one of them is not equal
	 * @author Abraham Camarillo
	 */
	public boolean equals(Task task){
		if(task==null) throw new NullPointerException("Task to compare is null");

		return (this.taskTitle.equals(task.getTitle()) &&
		this.startTime.equals(task.getStartTime()) &&
		this.endTime.equals(task.getEndTime()) &&
		this.taskInterval==task.getRepeatInterval() &&
		this.taskActive==task.isActive() &&
		this.taskRepeatability==task.isRepeated());
	}

	/**
	 * Generate a hashCode using the title of the task
	 * @return integer value based on the task title
	 */
	@Override
	public int hashCode(){
		return this.getTitle().hashCode();
	}

	/**
	 * Copies the parameters of another object
	 * @return clone of the task
	 */
	@Override
	public Task clone() throws CloneNotSupportedException {
		return (Task) super.clone();
	}

	/**
	 * Generates a string with the parameters of the Task
	 * @return a string with this format (Title/ StartTime/ EndTime/ Interval/ isActive/ isRepetitive)
	 * @author Abraham Camarillo
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

