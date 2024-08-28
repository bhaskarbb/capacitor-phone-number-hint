package com.samajsocial.plugins;

import android.app.Activity;
import android.content.Intent;
import android.app.PendingIntent;
import android.content.IntentSender;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.credentials.CredentialsOptions;


@CapacitorPlugin(name = "PhoneNumberHint")
public class PhoneNumberHintPlugin extends Plugin {

    private static final String TAG = "PhoneNumberPlugin";
    private ActivityResultLauncher<IntentSenderRequest> phoneNumberLauncher;

    @Override
    public void load() {
        phoneNumberLauncher = bridge.registerForActivityResult(
            new ActivityResultContracts.StartIntentSenderForResult(),
            result -> handlePhoneNumberResult(result)
        );
    }

    @PluginMethod
    public void getPhoneNumber(PluginCall call) {
        Activity activity = getActivity();
        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();

        CredentialsOptions options = new CredentialsOptions.Builder()
                .forceEnableSaveDialog()
                .build();

        PendingIntent credentialIntent = Credentials.getClient(activity, options).getHintPickerIntent(hintRequest);

        try {
            IntentSenderRequest intentSenderRequest = new IntentSenderRequest.Builder(credentialIntent.getIntentSender())
                .setFlags(PendingIntent.FLAG_IMMUTABLE, 0)
                .build();

            phoneNumberLauncher.launch(intentSenderRequest);
            saveCall(call);
        } catch (Exception e) {
            Log.e(TAG, "Error launching phone number hint picker", e);
            call.reject("Error launching phone number hint picker: " + e.getMessage());
        }
    }

    private void handlePhoneNumberResult(ActivityResult result) {
        PluginCall savedCall = getSavedCall();
        if (savedCall == null) {
            return;
        }

        int resultCode = result.getResultCode();
        Intent data = result.getData();

        if (resultCode == Activity.RESULT_OK && data != null) {
            Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
            if (credential != null) {
                String phoneNumber = credential.getId();
                Log.d(TAG, "Phone number selected: " + phoneNumber);
                JSObject ret = new JSObject();
                ret.put("phoneNumber", phoneNumber);
                savedCall.resolve(ret);
            } else {
                Log.e(TAG, "No phone number selected");
                savedCall.reject("No phone number selected");
            }
        } else {
            Log.e(TAG, "Phone number selection cancelled");
            savedCall.reject("Phone number selection cancelled");
        }

        savedCall.release(getBridge());
    }
}
