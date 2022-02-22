package com.mylike.newdemo.callback;


import com.mylike.newdemo.common.inject.AppModule;
import com.mylike.newdemo.common.inject.FragmentModule;
import com.mylike.newdemo.ui.fragment.HomeFragment;

import dagger.Component;

@Component(modules = {FragmentModule.class, AppModule.class})
public interface FragmentAppComponent {
    void inject(HomeFragment homeFragment);
}
