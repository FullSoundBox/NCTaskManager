package mx.edu.j2se.camarillo.tasks.tests;

import com.google.gson.Gson;
import jdk.internal.util.xml.impl.Input;
import mx.edu.j2se.camarillo.tasks.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.time.LocalDateTime;

public class SerializationTestP8 {
    /**
     * This test evaluates serialization of Task class using standard serialization methods
     */
    @Test
    public void standardSerializeTest(){
        try{
            LocalDateTime start = LocalDateTime.of(2022,1,2,10,0);
            Task taskToSerialize = new Task("Serialize this",
                    start,start.plusDays(30),24);
            FileOutputStream fileOut = new FileOutputStream("taskToSerialize.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskToSerialize);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            FileInputStream fileIn = new FileInputStream("taskToSerialize.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Task taskDeSerialized = (Task) in.readObject();
            fileIn.close();
            LocalDateTime start = LocalDateTime.of(2022,1,2,10,0);
            Task taskToSerialize = new Task("Serialize this",
                    start,start.plusDays(30),24);
            Assert.assertTrue(taskToSerialize.equals(taskDeSerialized));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void serializeTasklistTest(){
        AbstractTaskList taskList = new ArrayTaskList();
        Task lunch = new Task("Lunch with a beautiful girl",
                LocalDateTime.of(2021,8,24,16,0));

        LocalDateTime start = LocalDateTime.of(2021,3,1,8,15);
        LocalDateTime end = LocalDateTime.of(2021,9,1,8,15);
        long interval = 24;
        Task run = new Task("Morning run",start,end,interval);

        Task medication = new Task("Taking medication",
                LocalDateTime.of(2021,8,20,8,15),
                LocalDateTime.of(2021,8,28,0,0),
                12);

        Task friends = new Task("Meeting with friends",
                LocalDateTime.of(2021,9,1,18,0));

        taskList.add(lunch);
        taskList.add(run);
        taskList.add(medication);
        taskList.add(friends);
        taskList.setListName("taskListToSerialize");

        try{
            FileOutputStream fileOut = new FileOutputStream("taskListToSerialize.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskList);
            fileOut.close();

            FileInputStream fileIn = new FileInputStream("taskListToSerialize.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            AbstractTaskList taskListDeSerialized = (AbstractTaskList) in.readObject();
            fileIn.close();

            Assert.assertTrue(taskList.equals(taskListDeSerialized));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    @Test
    public void streamIOTest(){
        String fileName = "file1.txt";

        try {
            OutputStream outStream = new BufferedOutputStream(new FileOutputStream(fileName));
            for(int i = 1000000; i >= 0; i--)
                outStream.write(i);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            outStream.close();
        }
        // determine the read time without buffering
        Long timeStart = System.currentTimeMillis();
        try {
            InputStream inStream = new FileInputStream(fileName);
            while(inStream.read() != -1) { }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            inStream.close();
        }
        System.out.println("Direct read time: " + (System.currentTimeMillis() - timeStart) + " millisec");

        // Let's apply buffering
        timeStart = System.currentTimeMillis();
        try {
            InputStream inStream = new BufferedInputStream(new FileInputStream(fileName));
            while(inStream.read() != -1) { }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            inStream.close();
        }
        System.out.println("Buffered read time: " + (System.currentTimeMillis() - timeStart) + " millisec");
    }*/

    @Test
    public void binarySerializeTest(){
        Task lunch = new Task("Lunch with a beautiful girl",
                LocalDateTime.of(2021,8,24,16,0));

        LocalDateTime start = LocalDateTime.of(2021,3,1,8,15);
        LocalDateTime end = LocalDateTime.of(2021,9,1,8,15);
        long interval = 24;
        Task run = new Task("Morning run",start,end,interval);

        Task medication = new Task("Taking medication",
                LocalDateTime.of(2021,8,20,8,15),
                LocalDateTime.of(2021,8,28,0,0),
                12);

        Task friends = new Task("Meeting with friends",
                LocalDateTime.of(2021,9,1,18,0));

        lunch.setActive(true);
//        run.setActive(true);
//        medication.setActive(true);
//        friends.setActive(true);

        AbstractTaskList taskListToSerialize = new ArrayTaskList();
        AbstractTaskList taskListToDeSerialize = new LinkedTaskList();
        AbstractTaskList taskListToDeSerialize2 = new ArrayTaskList();
        taskListToSerialize.setListName("taskListToSerialize");
        taskListToDeSerialize.setListName("taskListToDeSerialize");
        taskListToDeSerialize2.setListName("taskListToDeSerialize2");

        taskListToSerialize.add(lunch);
        taskListToSerialize.add(run);
        taskListToSerialize.add(medication);
        taskListToSerialize.add(friends);

        String fileName = "file2.txt";

        try{
            //Writing to file given the OutputStream
            OutputStream outStream = new BufferedOutputStream(new FileOutputStream(fileName));
            TaskIO.writeBinary(taskListToSerialize,outStream);
            outStream.close();

            //Reading from file given the InputStream
            InputStream inStream = new FileInputStream(fileName);
            TaskIO.readBinary(taskListToDeSerialize,inStream);
            inStream.close();
            System.out.println(taskListToDeSerialize);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File serialDataFile = new File("file3.txt");
            TaskIO.write(taskListToSerialize,serialDataFile);

            TaskIO.read(taskListToDeSerialize2,serialDataFile);
            System.out.println();
            System.out.println(taskListToDeSerialize2);
        }catch (IOException e){

        }

    }

    @Test
    public void inputOutputJSONTest() {
        Task lunch = new Task("Lunch with a beautiful girl",
                LocalDateTime.of(2021,8,24,16,0));

        LocalDateTime start = LocalDateTime.of(2021,3,1,8,15);
        LocalDateTime end = LocalDateTime.of(2021,9,1,8,15);
        long interval = 24;
        Task run = new Task("Morning run",start,end,interval);

        Task medication = new Task("Taking medication",
                LocalDateTime.of(2021,8,20,8,15),
                LocalDateTime.of(2021,8,28,0,0),
                12);

        Task friends = new Task("Meeting with friends",
                LocalDateTime.of(2021,9,1,18,0));

        AbstractTaskList taskList = new ArrayTaskList();
        taskList.setListName("taskListToSerialize");
        AbstractTaskList taskListToDeSerialize = new LinkedTaskList();
        AbstractTaskList taskListToDeSerialize2 = new ArrayTaskList();
        taskListToDeSerialize.setListName("taskListToDeSerialize");
        taskListToDeSerialize2.setListName("taskListToDeSerialize2");

        taskList.add(lunch);
        taskList.add(run);
        taskList.add(medication);
        taskList.add(friends);

        try{
            TaskIO.write(taskList,new FileWriter("jsonFileToWrite.json"));

            TaskIO.read(taskListToDeSerialize, new FileReader("jsonFileToWrite.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(taskListToDeSerialize);

        try{
            File serialDataFile = new File("jsonFileToWrite.json");
            TaskIO.writeText(taskList,serialDataFile);

            TaskIO.readText(taskListToDeSerialize2,serialDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(taskListToDeSerialize2);

//        try{
//            gson.toJson(taskList.getTask(0),new FileWriter("jsonFileToWrite.json"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try{
//            TaskIO.writeText(taskList,new File("jsonFileToWrite.json"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        AbstractTaskList deserializedTaskList = new ArrayTaskList();
//        deserializedTaskList.setListName("deserializedTaskList");
//        try{
//            TaskIO.readText(deserializedTaskList,new File("jsonFileToWrite.json"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(deserializedTaskList);

    }
}
