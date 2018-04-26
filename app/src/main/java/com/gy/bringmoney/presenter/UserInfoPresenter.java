package com.gy.bringmoney.presenter;

import com.gy.bringmoney.base.BasePresenter;

public abstract  class UserInfoPresenter extends BasePresenter {

    public abstract void update(int id, String username, String gengder, String phone, String mail);
}
