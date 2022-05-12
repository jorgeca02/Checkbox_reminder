package com.cdp.checkboxreminder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.cdp.checkboxreminder.managers.CBNotificationManager;
import com.cdp.checkboxreminder.managers.DataManager;
import com.cdp.checkboxreminder.managers.DateManager;
import com.cdp.checkboxreminder.managers.MenuManager;
import com.cdp.checkboxreminder.R;
import com.cdp.checkboxreminder.managers.TaskManager;

public class MainActivity extends AppCompatActivity {
    TaskManager taskManager;
    MenuManager menuManager;
    DateManager dateManager;
    DataManager dataManager;
    CBNotificationManager cbNotificationManager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataManager=new DataManager(this);
        dateManager=new DateManager(this);
        menuManager=new MenuManager(this);
        taskManager=new TaskManager(this);
        //cbNotificationManager= new CBNotificationManager();
        dataManager.readTask();

    }

    public void createTask(View view) {

            taskManager.createTask(
                    String.valueOf(menuManager.getNameEdit().getText()),
                    String.valueOf(menuManager.getDescriptionEdit().getText()),
                    menuManager.getDay(),menuManager.getMonth(),menuManager.getYear()
                    );


    }
    public void checkTask(View view){
        taskManager.checkTask(view.getId());
    }
    public void removeTask(View view){
        taskManager.removeTask(view.getId());
}
    public void expandMenu(View view){
        menuManager.expand();
    }
    public void expandTask(View view) {
        taskManager.expand(view);
    }
    public MenuManager getMenuManager() {
        return menuManager;
    }
    public DateManager getDateManager() {
        return dateManager;
    }
    public TaskManager getTaskManager() {
        return taskManager;
    }
    public DataManager getDataManager() {
        return dataManager;
    }
}