package foodfacts.bourgault.com.rx.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Retention(RetentionPolicy.RUNTIME)
@Scope
@interface RxScope {
}
