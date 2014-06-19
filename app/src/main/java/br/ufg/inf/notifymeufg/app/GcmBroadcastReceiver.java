/**
 * GcmBroadcastReceiver recebe comunicação do GCM para o repasse da notificação
 */

package br.ufg.inf.notifymeufg.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // Notifica o GcmMensageiro do recebimento de uma notificação
        ComponentName comp = new ComponentName(context.getPackageName(),
                GcmMensageiro.class.getName());

        // Mantém o aparelho ativo para o recebimento da notificação
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}
