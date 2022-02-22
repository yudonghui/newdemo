package com.mylike.newdemo.common.inject;


import com.trello.rxlifecycle3.components.support.RxFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private RxFragment mFragment;

    public FragmentModule(RxFragment rxFragment) {
        mFragment = rxFragment;
    }

    @Provides
    RxFragment getActivity() {
        return mFragment;
    }
}
