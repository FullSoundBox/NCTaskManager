package mx.edu.j2se.camarillo.tasks;

import com.google.gson.Gson;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TaskIO {
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
                if (repetitive){
                    start = LocalDateTime.of(dis.readInt(),dis.readInt(),dis.readInt(),dis.readInt(),dis.readInt());
                    end = LocalDateTime.of(dis.readInt(),dis.readInt(),dis.readInt(),dis.readInt(),dis.readInt());
                    dummy.setTime(start,end,interval);
                }else{
                    start = LocalDateTime.of(dis.readInt(),dis.readInt(),dis.readInt(),dis.readInt(),dis.readInt());
                    dummy.setTime(start);
                }
//                System.out.println(dummy);
                tasks.add(new Task());
                tasks.getTask(i).clone(dummy);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        dis.close();
    }

    public static void write(AbstractTaskList tasks, File file) throws IOException{
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        writeBinary(tasks,outputStream);
        outputStream.close();
    }

    public static void read(AbstractTaskList tasks, File file) throws IOException{
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        readBinary(tasks,inputStream);
        inputStream.close();
    }

    public static void write(AbstractTaskList tasks, Writer out){

    }
    public static void read(AbstractTaskList tasks, Reader in){}
    public static void writeText(AbstractTaskList tasks, File file){
        final Gson gson = new Gson();
        try{
            gson.toJson(tasks.getTask(0),new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readText(AbstractTaskList tasks, File file){
        final Gson gson = new Gson();
        try{
            tasks = gson.fromJson(new FileReader(file),AbstractTaskList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
