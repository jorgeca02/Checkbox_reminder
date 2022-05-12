package com.cdp.checkboxreminder.managers;


import android.content.Context;

import com.cdp.checkboxreminder.Task;
import com.cdp.checkboxreminder.activity.MainActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataManager {
    private static final String FILENAME = "task.txt";
    public MainActivity mainActivity;
    public DataManager(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }
public void saveTasks(){
    FileOutputStream fileOutputStream = null;
    StringBuilder stringBuilder;
    try {
        fileOutputStream = mainActivity.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        stringBuilder = new StringBuilder();
        for(int i=0;i<mainActivity.getTaskManager().getTasks().size();i++){
            if(!mainActivity.getTaskManager().getTasks().get(i).isCheck()) {
                stringBuilder.append(
                        mainActivity.getTaskManager().getTasks().get(i).getName())
                        .append("\n")
                        .append(mainActivity.getTaskManager().getTasks().get(i).getDescription())
                        .append("\n")
                        .append(mainActivity.getTaskManager().getTasks().get(i).getDay())
                        .append("\n")
                        .append(mainActivity.getTaskManager().getTasks().get(i).getMonth())
                        .append("\n")
                        .append(mainActivity.getTaskManager().getTasks().get(i).getYear())
                        .append("\n");
            }

        }
        fileOutputStream.write(stringBuilder.toString().getBytes());

    }catch (Exception e){
        e.printStackTrace();
    }finally {
        if(fileOutputStream != null){
             try{
                fileOutputStream.close();
             }catch (Exception e){
                e.printStackTrace();
             }
        }
    }
}
public void readTask(){
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = mainActivity.openFileInput(FILENAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String textLn;
            char i='n';
            String name="ej";
            String description="ej";
            int     day=1,
                    month=1,
                    year=1;
            while((textLn = bufferedReader.readLine())!=null) {
                switch (i){
                    case 'n':
                        name=textLn;
                        i='d';
                        break;
                    case 'd':
                        description=textLn;
                        i='a';
                        break;
                    case 'a':
                        day=Integer.parseInt(textLn);
                        i='m';
                        break;
                    case'm':
                        month=Integer.parseInt(textLn);
                        i='y';
                        break;
                    case 'y':
                        year=Integer.parseInt(textLn);
                        i='n';
                        mainActivity.getTaskManager().getTasks().add
                                (new Task(name,description,day,month,year));
                        break;

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        mainActivity.getTaskManager().reload();
    }
}
