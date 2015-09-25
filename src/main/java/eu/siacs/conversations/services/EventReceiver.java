package eu.siacs.conversations.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import eu.siacs.conversations.persistance.DatabaseBackend;

public class EventReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(final Context context, final Intent intent) {
		final Intent mIntentForService;
		switch (intent.getAction()) {
			case XmppConnectionService.ACTION_SEND_MESSAGE:
				mIntentForService = new Intent(intent);
				mIntentForService.setClass(context, XmppConnectionService.class);
				break;
			default:
				mIntentForService = new Intent(context, XmppConnectionService.class);
				if (intent.getAction() != null) {
					mIntentForService.setAction(intent.getAction());
				} else {
					mIntentForService.setAction("other");
				}
				break;
		}
		if (intent.getAction().equals("ui")
				|| DatabaseBackend.getInstance(context).hasEnabledAccounts()) {
			context.startService(mIntentForService);
		}
	}

}
