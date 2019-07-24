package com.balajiprabhu.weatherapp.utils;

import android.content.Intent;

public class FinishActivityEvent extends BaseUnboundViewEvent {

    private boolean finishAffinity = false;
    private Object result;
    private ActivityResult activityResult;

    private FinishActivityEvent(Object emitter) {
        this.emitter = emitter;
    }

    public static FinishActivityEvent build(Object emitter) {
        return new FinishActivityEvent(emitter);
    }

    public FinishActivityEvent finishActivityEvent() {
        return this;
    }

    public FinishActivityEvent finishAffinity() {
        finishAffinity = true;
        return this;
    }

    public FinishActivityEvent setResult(Object result) {
        this.result = result;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FinishActivityEvent that = (FinishActivityEvent) o;

        return finishAffinity == that.finishAffinity;
    }

    @Override
    public int hashCode() {
        return (finishAffinity ? 1 : 0);
    }

    public boolean isFinishAffinity() {
        return finishAffinity;
    }

    public boolean hasResult() {
        return result != null;
    }

    public Object getResult() {
        return result;
    }
}

