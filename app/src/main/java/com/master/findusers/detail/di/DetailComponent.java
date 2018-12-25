package com.master.findusers.detail.di;

import com.master.findusers.detail.presentation.DetailPresenter;
import com.master.findusers.di.scopes.DetailScope;

import dagger.Subcomponent;

@DetailScope
@Subcomponent(modules = {DetailModule.class})
public interface DetailComponent {

    void inject(DetailPresenter mDetailPresenter);
}
