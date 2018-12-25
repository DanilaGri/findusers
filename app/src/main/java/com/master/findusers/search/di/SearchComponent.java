package com.master.findusers.search.di;

import com.master.findusers.di.scopes.SearchScope;
import com.master.findusers.search.presentation.SearchUserPresenter;

import dagger.Subcomponent;

@SearchScope
@Subcomponent(modules = {SearchModule.class})
public interface SearchComponent {
    void inject(SearchUserPresenter searchUserPresenter);
}

