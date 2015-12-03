package com.adult.android.model.exception;

public class ApplicationUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private OncaughtExceptionListener listener;
    public ApplicationUncaughtExceptionHandler(OncaughtExceptionListener l) {
        super();
        this.listener = l;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (this.listener != null) {
            this.listener.onCrash(thread, ex);
        }
    }

    public static interface OncaughtExceptionListener{
        public void onCrash(Thread thread, Throwable ex);
    }
}
