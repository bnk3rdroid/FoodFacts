package foodfacts.bourgault.com.rx.utils;

import io.reactivex.Scheduler;

/**
 * Rx schedulers (threads).
 */
public interface RxSchedulers {

    Scheduler runOnBackground();
    Scheduler io();
    Scheduler compute();
    Scheduler androidThread();
    Scheduler internet();

}
