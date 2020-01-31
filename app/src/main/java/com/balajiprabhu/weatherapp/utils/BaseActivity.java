package com.balajiprabhu.weatherapp.utils;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity {


    private static final String ACTIVITY_KILLED_BY_MEMORY_MANAGEMENT = "ACTIVITY_KILLED_BY_MEMORY_MANAGEMENT";
    private static final int NO_FLAGS = 0;

    @Nullable
    protected CompositeDisposable registerUnboundViewEvents() {
        return null;
    }


    private final CompositeDisposable lifecycleSubscriptions = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.getBoolean(ACTIVITY_KILLED_BY_MEMORY_MANAGEMENT, false)) {
            Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

    private void subscribeToEventBus() {
        lifecycleSubscriptions.clear();
        CompositeDisposable eventsSubscriptionV2 = registerUnboundViewEvents();
        if (eventsSubscriptionV2 != null) {
            lifecycleSubscriptions.add(eventsSubscriptionV2);
        }
    }

    @Override
    protected void onResume() {

        subscribeToEventBus();
        super.onResume();
    }

    @Override
    protected void onPause() {
        lifecycleSubscriptions.clear();
        super.onPause();
    }

    protected void subscribeOnLifecycle(Disposable disposable) {
        lifecycleSubscriptions.add(disposable);
    }

    protected void startActivity(StartActivityEvent event) {

        int flags = event.getIntentFlags();
        boolean hasExtras = event.hasExtras();

        Intent startIntent = new Intent(this, event.getStartActivity());
        if (hasExtras) {
//            startIntent.putExtra(TabBarActivity.NAVIGATOR_STATE, event.getIntentParameter());
        }
        if (flags != NO_FLAGS) {
            startIntent.setFlags(event.getIntentFlags());
        }
        startActivity(startIntent);
    }

    protected void finishActivity(FinishActivityEvent event) {
        if (event.isFinishAffinity()) {
            this.finishAffinity();
        } else {
            this.finish();
        }

    }

    private void launchActivity(Class launchActivityClazz) {
        Intent intent = new Intent(this, launchActivityClazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
