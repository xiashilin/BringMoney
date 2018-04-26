package com.gy.bringmoney.presenter.Imp;

import com.gy.bringmoney.bean.UserBean;
import com.gy.bringmoney.model.Imp.UserInfoModelImp;
import com.gy.bringmoney.model.UserInfoModel;
import com.gy.bringmoney.presenter.UserInfoPresenter;
import com.gy.bringmoney.view.UserInfoView;

public class UserInfoPresenterImp extends UserInfoPresenter implements UserInfoModelImp.UserInfoOnListener {

    private UserInfoModel model;
    private UserInfoView view;

    public UserInfoPresenterImp(UserInfoView view) {
        this.model=new UserInfoModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(UserBean user) {
        view.loadDataSuccess(user);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }

    @Override
    public void update(int id, String username, String gengder, String phone, String mail) {
        model.update(id,username,gengder,phone,mail);
    }
}
