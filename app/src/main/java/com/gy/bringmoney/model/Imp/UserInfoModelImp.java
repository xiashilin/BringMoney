package com.gy.bringmoney.model.Imp;

import com.gy.bringmoney.api.RetrofitFactory;
import com.gy.bringmoney.base.BaseObserver;
import com.gy.bringmoney.bean.UserBean;
import com.gy.bringmoney.model.UserInfoModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserInfoModelImp implements UserInfoModel {

    private UserInfoOnListener listener;

    public UserInfoModelImp(UserInfoOnListener listener) {
        this.listener = listener;
    }

    @Override
    public void update(int id, String username, String gengder, String phone, String mail) {
        RetrofitFactory.getInstence().API()
                .updateUser(id, username, gengder, phone, mail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UserBean>() {
                    @Override
                    protected void onSuccees(UserBean userBean) throws Exception {
                        listener.onSuccess(userBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
    }

    @Override
    public void onUnsubscribe() {

    }

    /**
     * 回调接口
     */
    public interface UserInfoOnListener {

        void onSuccess(UserBean user);

        void onFailure(Throwable e);
    }
}
