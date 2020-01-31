package com.balajiprabhu.weatherapp.utils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


@Singleton
public class UnboundViewEventBus {

    private final PublishSubject<StartActivityEvent> startActivitySubject = PublishSubject.create();

    @Inject
    public UnboundViewEventBus() {
    }


    public void send(StartActivityEvent event) {
        startActivitySubject.onNext(event);
    }


    public Observable<StartActivityEvent> startActivity(Class viewModelClass) {
        return startActivitySubject.filter(event -> fromEmitter(event, viewModelClass));
    }


    private boolean fromEmitter(BaseUnboundViewEvent event, Class viewModelClass) {
        return viewModelClass.getName().equals((event).getEmitter().getClass().getName());
    }

}
