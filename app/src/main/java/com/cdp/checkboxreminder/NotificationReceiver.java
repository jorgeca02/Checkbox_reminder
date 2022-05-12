package com.cdp.checkboxreminder;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.cdp.checkboxreminder.activity.MainActivity;

import java.util.Date;

public class NotificationReceiver {
Date date = new Date();
MainActivity mainActivity;
    public void onReceive(Context context,MainActivity mainActivity) {
        this.mainActivity=mainActivity;


        Intent repeating_Intent = new Intent(context, MainActivity.class);
        repeating_Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, repeating_Intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mainActivity.getDataManager().readTask();
        for (Task t:mainActivity.getTaskManager().getTasks()) {
            if (t.getDay()==date.getDay()&&t.getMonth()==date.getMonth()&&t.getYear()==date.getYear()) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(mainActivity, "Notification")
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.drawable.ic_notice) // Icono pequeño necesario para la aplicacion aparece a la derecha del nombre de la app
                        .setContentTitle(t.getName()) // Titulo de la notificacion
                        .setContentText(t.getDescription())//Texto/descripcion de la notificacion
                        .setPriority(Notification.PRIORITY_HIGH);// Importancia de la notificacion HIGH es con sonido y vibracion, DEFAULT notificacion normal, LOW no tiene sonido ni notificacion y se muestra por debajo de las demas notificaciones


                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                notificationManager.notify(200, builder.build());
            }
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mainActivity, "Notification")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notice) // Icono pequeño necesario para la aplicacion aparece a la derecha del nombre de la app
                .setContentTitle("test") // Titulo de la notificacion
                .setContentText("desc")//Texto/descripcion de la notificacion
                .setPriority(Notification.PRIORITY_HIGH);// Importancia de la notificacion HIGH es con sonido y vibracion, DEFAULT notificacion normal, LOW no tiene sonido ni notificacion y se muestra por debajo de las demas notificaciones
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(200, builder.build());
    }
}