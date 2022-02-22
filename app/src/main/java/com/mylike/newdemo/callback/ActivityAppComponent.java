package com.mylike.newdemo.callback;




import com.mylike.newdemo.MainActivity;
import com.mylike.newdemo.common.inject.ActivityModule;
import com.mylike.newdemo.common.inject.ApiModule;

import dagger.Component;

@Component(modules = {ActivityModule.class, ApiModule.class})
public interface ActivityAppComponent {
    void inject(MainActivity mainActivity);
}
