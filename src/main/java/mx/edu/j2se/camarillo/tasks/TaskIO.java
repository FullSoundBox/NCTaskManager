package mx.edu.j2se.camarillo.tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * This class allows working with binary serialization and JSON files. It has two types of methods, read and write.
 * Each of these methods has as parameter: a task list to read or write on, and a Stream or File to be written to.
 */
public class TaskIO {
    /**
     * This method allows to get the tasks of a given taskList and write them in an OutputStream
     * using this format: (Number of tasks in the list/ Length of task title/ Title of task/ isActive...
     * .../Interval/ StartTime/ EndTime (if repetitive) ) this for each task in the list
     * @param tasks could be an ArrayTaskList or a LinkedTaskList
     * @param out OutputStream to be written to
     * @throws IOException if file couldn't be opened, or it reached EOF
     * @author Abraham Camarillo
     */
    public static void writeBinary(AbstractTaskList tasks, OutputStream out) throws IOException{
        DataOutputStream dos = new DataOutputStream(out);
        try{
            dos.writeInt(tasks.size());
            for (Task task: tasks) {
                dos.writeInt(task.getTitle().length());
                dos.writeChars(task.getTitle());
                dos.writeBoolean(task.isActive());
                dos.writeLong(task.getRepeatInterval());
                dos.writeBoolean(task.isRepeated());
                if(task.isRepeated()){
                    dos.writeInt(task.getStartTime().getYear());
                    dos.writeInt(task.getStartTime().getMonthValue());
                    dos.writeInt(task.getStartTime().getDayOfMonth());
                    dos.writeInt(task.getStartTime().getHour());
                    dos.writeInt(task.getStartTime().getMinute());
                    dos.writeInt(task.getEndTime().getYear());
                    dos.writeInt(task.getEndTime().getMonthValue());
                    dos.writeInt(task.getEndTime().getDayOfMonth());
                    dos.writeInt(task.getEndTime().getHour());
                    dos.writeInt(task.getEndTime().getMinute());
                }else {
                    dos.writeInt(task.getStartTime().getYear());
                    dos.writeInt(task.getStartTime().getMonthValue());
                    dos.writeInt(task.getStartTime().getDayOfMonth());
                    dos.writeInt(task.getStartTime().getHour());
                    dos.writeInt(task.getStartTime().getMinute());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        dos.close();
    }

    /**
     * Reads a binary stream with the format used in {@link #writeBinary(AbstractTaskList tasks, OutputStream out)}
     * and writes the tasks in the task list
     * @param tasks could be an ArrayTaskList or a LinkedTaskList
     * @param in OutputStream to be read from
     * @throws IOException if file couldn't be opened, or it reached EOF
     * @author Abraham Camarillo
     */
    public static void readBinary(AbstractTaskList tasks, InputStream in) throws IOException{
        DataInputStream dis = new DataInputStream(in);

        try{
            Task dummy = new Task();
            int a = dis.readInt();
            for (int i = 0; i < a; i++) {
                int bufferSize = dis.readInt();
                char[] buffer = new char[bufferSize];
                StringBuilder out = new StringBuilder();
                for (int j = 0; j < bufferSize; j++) {
                    buffer[j] = dis.readChar();
                }
                out.append(buffer);
//            System.out.println(out.toString());
                dummy.setTitle(out.toString());
//                System.out.println(dummy.getTitle());
                dummy.setActive(dis.readBoolean());
//                System.out.println("Task is active: " + dummy.isActive());
                Long interval = dis.readLong();
//                System.out.println(interval);
                LocalDateTime start;
                LocalDateTime end;
                boolean repetitive = dis.readBoolean();
                start = LocalDateTime.of(dis.readInt(),dis.readInt(),dis.readInt(),dis.readInt(),dis.readInt());
                if (repetitive){
                    end = LocalDateTime.of(dis.readInt(),dis.readInt(),dis.readInt(),dis.readInt(),dis.readInt());
                    dummy.setTime(start,end,interval);
                }else{
                    dummy.setTime(start);
                }
//                System.out.println(dummy);
                try{
                    tasks.add(dummy.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        dis.close();
    }

    /**
     * Writes a binary stream to the specified file with the tasklist given, using the format described in
     * {@link #writeBinary(AbstractTaskList tasks, OutputStream out)}
     * @param tasks tasklist to get the tasks from
     * @param file where the tasks will be written
     * @throws IOException if file couldn't be opened, or it reached EOF
     * @author Abraham Camarillo
     */
    public static void write(AbstractTaskList tasks, File file) throws IOException{
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        writeBinary(tasks,outputStream);
        outputStream.close();
    }

    /**
     * Reads a file and adds the tasks within it to the task list given.
     * @param tasks task list to be written
     * @param file to bew read from
     * @throws IOException if file couldn't be opened, or it reached EOF
     * @author Abraham Camarillo
     */
    public static void read(AbstractTaskList tasks, File file) throws IOException{
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        readBinary(tasks,inputStream);
        inputStream.close();
    }

    /**
     * Writes tasks from the list to the stream in the JSON format
     * @param tasks task list to be read
     * @param out stream to be written to
     * @throws IOException if the file couldn't be opened or reached EOF
     * @author Abraham Camarillo
     */
    public static void write(AbstractTaskList tasks, Writer out) throws IOException{
        final Gson gson = new Gson();
        BufferedWriter bufferedWriter = new BufferedWriter(out);
        final Type tipoArrayTaskList = new TypeToken<ArrayTaskList>(){}.getType();
        final Type tipoLinkedTaskList = new TypeToken<LinkedTaskList>(){}.getType();

        bufferedWriter.write(tasks.getClass().toString());
        bufferedWriter.newLine();
        if (tasks.getClass()==ArrayTaskList.class)
            bufferedWriter.write(gson.toJson(tasks,tipoArrayTaskList));
        else
            bufferedWriter.write(gson.toJson(tasks,tipoLinkedTaskList));
        bufferedWriter.close();
        out.close();
    }

    /**
     * Reads tasks from the JSON stream to the list.
     * @param tasks to be written to
     * @param in stream to be read from
     * @throws IOException if the file couldn't be opened or reached EOF
     * @author Abraham Camarillo
     */
    public static void read(AbstractTaskList tasks, Reader in) throws IOException{
        final Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(in);
        final Type tipoArrayTaskList = new TypeToken<ArrayTaskList>(){}.getType();
        final Type tipoLinkedTaskList = new TypeToken<LinkedTaskList>(){}.getType();

        try{
            if (bufferedReader.readLine().equals(ArrayTaskList.class.toString())){
                String jsonInput = bufferedReader.readLine();
                AbstractTaskList dummy = gson.fromJson(jsonInput,tipoArrayTaskList);
                tasks = dummy.clone();
            }
            else {
                String jsonInput = bufferedReader.readLine();
                AbstractTaskList dummy = gson.fromJson(jsonInput, tipoLinkedTaskList);
                tasks = dummy.clone();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        bufferedReader.close();
        in.close();
    }

    /**
     * Writes tasks to the file in JSON format
     * @param tasks task list to be read
     * @param file where the tasks will be written
     * @throws IOException if the file couldn't be opened or reached EOF
     * @author Abraham Camarillo
     */
    public static void writeText(AbstractTaskList tasks, File file) throws IOException {
        write(tasks,new FileWriter(file));
    }

    /**
     * reads tasks from the JSON file and writes them to the task list
     * @param tasks task list to be written
     * @param file to read from
     * @throws IOException if the file couldn't be opened or reached EOF
     * @author Abraham Camarillo
     */
    public static void readText(AbstractTaskList tasks, File file) throws IOException{
        read(tasks,new FileReader(file));
    }
}
