/**
 * GcmMensageiro
 */

package br.ufg.inf.notifymeufg.app;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class GcmMensageiro extends IntentService {

    String message;
    private Handler handler;

    static final String MESSAGE_KEY = "message";
    public static final String TAG = "GCMNotificationIntentService";
    private NotificationManager mNotificationManager;
    public static final int NOTIFICATION_ID = 1;

    public GcmMensageiro() {
        super("GcmMensageiro");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
    }

    // Recebe um intent de mensagem de recebida do GcmBroadcastReceiver
    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String gcmMensagem = gcm.getMessageType(intent);

        message = extras.getString("title");
        showToast();
        Log.i("GCM", "Recebido : (" + gcmMensagem +")  " + extras.getString("title"));
    }

        /*
        // Verifica o sucesso ou não no recebimento da mensagem do GCM
        if (!extras.isEmpty()) {

            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification("Send error: " + extras.toString());

            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification("Deleted messages on server: " + extras.toString());

            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                for (int i = 0; i < 3; i++) {
                    Log.i(TAG,"Working... " + (i + 1) + "/5 @ " + SystemClock.elapsedRealtime());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                }
                Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());

                enviaNotificacao("Mensagem do GCM: " + extras.get(MESSAGE_KEY));
                Log.i(TAG, "Recebido: " + extras.toString());
            }
        }

        GcmBroadcastReceiver.completeWakefulIntent(intent); */


    /*
        // Cria o meio de visualizaçao da notificaçao no aparelho
        private void enviaNotificacao(String msg) {
        Log.d(TAG, "Notificaçao sendo envidada...: " + msg);
        mNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this)
                .setContentTitle("Nova Notificação")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        Log.d(TAG, "Notificação enviada com sucesso");
    }
    */

    public void showToast(){handler.post(new Runnable() {
        public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }});
    }
}
