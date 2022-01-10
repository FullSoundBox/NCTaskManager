package mx.edu.j2se.camarillo.tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

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

    public static void writeText(AbstractTaskList tasks, File file) throws IOException {
        write(tasks,new FileWriter(file));
    }
    public static void readText(AbstractTaskList tasks, File file) throws IOException{
        read(tasks,new FileReader(file));
    }
}
