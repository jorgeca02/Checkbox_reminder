package com.cdp.checkboxreminder.managers;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.cdp.checkboxreminder.R;
import com.cdp.checkboxreminder.Task;
import com.cdp.checkboxreminder.activity.MainActivity;

import java.util.ArrayList;

public class TaskManager {
    MainActivity mainActivity;

    private ArrayList<Task> tasks;

    public TaskManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        tasks = new ArrayList<Task>();
    }

    public void createTask(String name, String description, int day, int month, int year) {
        if (taskIsCorrect(name)) {
            Task t = new Task(name, description, day, month, year);
            tasks.add(t);
            addTask(name, description, day, month, year, t.getId());
            if (tasks.size() > 1)
                reload();

            mainActivity.getMenuManager().expand();

        }
    }

    public void addTask(String name, String description, int day, int month, int year, int id) {
        if (!mainActivity.getDateManager().exist(day, month, year)) {
            mainActivity.getDateManager().createDate(day, month, year);
        }
        View taskView;
        TextView nameView;
        TextView descriptionView;
        LinearLayout taskList;
        taskList = (LinearLayout) mainActivity.getDateManager().getCorrectList(day, month, year);
        taskView = mainActivity.getLayoutInflater().inflate(R.layout.task, null, false);
        nameView = (TextView) taskView.findViewById(R.id.name);
        descriptionView = (TextView) taskView.findViewById(R.id.description);

        nameView.setText(name);
        descriptionView.setText(description);
        taskView.findViewById(R.id.removetask).setId(id);
        taskView.findViewById(R.id.check).setId(id + 1);
        taskList.addView(taskView);
        mainActivity.getDataManager().saveTasks();

    }

    public void reload() {
        mainActivity.getDateManager().dateList.removeAllViews();
        bubbleSortTasks();
        mainActivity.getDataManager().saveTasks();

        for (int i = 0; i < tasks.size(); i++) {
            addTask(tasks.get(i).getName(),
                    tasks.get(i).getDescription(),
                    tasks.get(i).getDay(),
                    tasks.get(i).getMonth(),
                    tasks.get(i).getYear(),
                    tasks.get(i).getId());

        }


    }

    private void bubbleSortTasks() {
        Task t;
        for (int i = 1; i < tasks.size(); i++) {
            if (tasks.get(i - 1).getYear() > tasks.get(i).getYear()) {
                t = tasks.get(i - 1);
                tasks.set(i - 1, tasks.get(i));
                tasks.set(i, t);
                bubbleSortTasks();
                return;
            }
        }
        for (int i = 1; i < tasks.size(); i++) {
            if (tasks.get(i - 1).getYear() == tasks.get(i).getYear()) {
                if (tasks.get(i - 1).getMonth() > tasks.get(i).getMonth()) {
                    t = tasks.get(i - 1);
                    tasks.set(i - 1, tasks.get(i));
                    tasks.set(i, t);
                    bubbleSortTasks();
                    return;
                }
            }
        }
        for (int i = 1; i < tasks.size(); i++) {
            if (tasks.get(i - 1).getYear() == tasks.get(i).getYear()) {
                if (tasks.get(i - 1).getMonth() == tasks.get(i).getMonth()) {
                    if (tasks.get(i - 1).getDay() > tasks.get(i).getDay()) {
                        t = tasks.get(i - 1);
                        tasks.set(i - 1, tasks.get(i));
                        tasks.set(i, t);
                        bubbleSortTasks();
                        return;
                    }
                }
            }
        }
    }

    public void expand(View view) {
        TextView text = (TextView) view.findViewById(R.id.description);
        LinearLayout cardView = (LinearLayout) view.findViewById(R.id.removetaskll);
        int v;
        if (text.getVisibility() == View.GONE)
            v = View.VISIBLE;
        else
            v = View.GONE;
        text.setVisibility(v);
        cardView.setVisibility(v);
    }

    public boolean taskIsCorrect(String name) {
        if (name.length() <= 1) {
            Toast.makeText(mainActivity, "Nombre muy corto", Toast.LENGTH_SHORT);
            return false;
        }
        return true;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void removeTask(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
            }

            reload();
        }
    }

    public void checkTask(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() + 1 == id) {
                tasks.get(i).setCheck(!tasks.get(i).isCheck());
            }
        }
        mainActivity.getDataManager().saveTasks();
    }
}
