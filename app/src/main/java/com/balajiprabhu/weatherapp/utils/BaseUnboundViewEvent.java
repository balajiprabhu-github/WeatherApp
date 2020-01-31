package com.balajiprabhu.weatherapp.utils;

public abstract class BaseUnboundViewEvent {
    protected Object emitter;
    public Object getEmitter() {
        return emitter;
    }
}
