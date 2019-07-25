package com.balajiprabhu.weatherapp.utils;

import android.content.Intent;
import android.net.Uri;

public class StartActivityEvent extends BaseUnboundViewEvent {

    private Class startActivityClazz;
    private boolean launchExternalApplication;
    private boolean isStartActivityForResult;
    private String intentAction;
    private String intentParameter;
    private String activityNotFoundUri;
    private int intentFlags = 0;
    private int requestCode = 0;
    private Intent intent;
    private boolean extras;
    private Uri intentUri;

    private StartActivityEvent(Object emitter) {
        this.emitter = emitter;
    }

    public static StartActivityEvent build(Object emitter) {
        return new StartActivityEvent(emitter);
    }

    public StartActivityEvent activityName(Class startActivityClazz) {
        this.startActivityClazz = startActivityClazz;
        return this;
    }

    public StartActivityEvent launchExternalApplication(boolean value) {
        this.launchExternalApplication = value;
        return this;

    }

    public boolean isLaunchingExternalApplication() {
        return this.launchExternalApplication;
    }

    public StartActivityEvent intentAction(String value) {
        this.intentAction = value;
        return this;
    }

    public String getIntentAction() {
        return this.intentAction;
    }

    public StartActivityEvent intentParameter(String value) {
        this.intentParameter = value;
        return this;
    }

    public String getIntentParameter() {
        return this.intentParameter;
    }

    public StartActivityEvent intentUri(Uri value) {
        this.intentUri = value;
        return this;
    }

    public Uri getIntentUri() {
        return this.intentUri;
    }

    public StartActivityEvent activityNotFoundUri(String value) {
        this.activityNotFoundUri = value;
        return this;
    }

    public String getActivityNotFoundUri() {
        return this.activityNotFoundUri;
    }

    public StartActivityEvent intentFlags(int intentFlags) {
        this.intentFlags = intentFlags;
        return this;
    }

    public Class<?> getStartActivity() {
        return startActivityClazz;
    }

    public int getIntentFlags() {
        return intentFlags;
    }

    public boolean isStartActivityForResult() {
        return isStartActivityForResult;
    }

    public StartActivityEvent setStartActivityForResult(boolean startActivityForResult) {
        isStartActivityForResult = startActivityForResult;
        return this;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public StartActivityEvent setRequestCode(int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

    public Intent getIntent() {
        return intent;
    }

    public StartActivityEvent setIntent(Intent intent) {
        this.intent = intent;
        return this;
    }

    public boolean hasExtras() {
        return extras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StartActivityEvent that = (StartActivityEvent) o;

        if (launchExternalApplication != that.launchExternalApplication) return false;
        if (isStartActivityForResult != that.isStartActivityForResult) return false;
        if (intentFlags != that.intentFlags) return false;
        if (requestCode != that.requestCode) return false;
        if (extras != that.extras) return false;
        if (startActivityClazz != null ? !startActivityClazz.equals(that.startActivityClazz) : that.startActivityClazz != null)
            return false;
        if (intentAction != null ? !intentAction.equals(that.intentAction) : that.intentAction != null)
            return false;
        if (intentParameter != null ? !intentParameter.equals(that.intentParameter) : that.intentParameter != null)
            return false;
        if (activityNotFoundUri != null ? !activityNotFoundUri.equals(that.activityNotFoundUri) : that.activityNotFoundUri != null)
            return false;
        if (intentUri != null ? !intentUri.equals(that.intentUri) : that.intentUri != null)
            return false;
        return intent != null ? intent.equals(that.intent) : that.intent == null;
    }

    @Override
    public int hashCode() {
        int result = startActivityClazz != null ? startActivityClazz.hashCode() : 0;
        result = 31 * result + (launchExternalApplication ? 1 : 0);
        result = 31 * result + (isStartActivityForResult ? 1 : 0);
        result = 31 * result + (intentAction != null ? intentAction.hashCode() : 0);
        result = 31 * result + (intentParameter != null ? intentParameter.hashCode() : 0);
        result = 31 * result + (intentUri != null ? intentUri.hashCode() : 0);
        result = 31 * result + (activityNotFoundUri != null ? activityNotFoundUri.hashCode() : 0);
        result = 31 * result + intentFlags;
        result = 31 * result + requestCode;
        result = 31 * result + (intent != null ? intent.hashCode() : 0);
        result = 31 * result + (extras ? 1 : 0);
        return result;
    }

    public StartActivityEvent setExtras(boolean extras) {
        this.extras = extras;
        return this;
    }

}
