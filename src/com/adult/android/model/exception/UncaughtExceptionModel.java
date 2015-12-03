package com.adult.android.model.exception;

public class UncaughtExceptionModel implements ApplicationUncaughtExceptionHandler.OncaughtExceptionListener {
    private UncaughtExceptionListener uncaughtExceptionListener;
    private ApplicationUncaughtExceptionHandler handler;
    public UncaughtExceptionModel(UncaughtExceptionListener l) {
        super();
        this.uncaughtExceptionListener = l;
        handler = new ApplicationUncaughtExceptionHandler(this);
    }

    public void startHandle() {
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }

    @Override
    public void onCrash(Thread thread, Throwable ex) {
        if (this.uncaughtExceptionListener != null) {
            this.uncaughtExceptionListener.onCrash(thread, ex);
        }
    }

    public static interface UncaughtExceptionListener{
        public void onCrash(Thread thread, Throwable ex);
    }
}
