package com.gy.bringmoney.model.Imp;

import com.gy.bringmoney.api.RetrofitFactory;
import com.gy.bringmoney.base.BaseObserver;
import com.gy.bringmoney.bean.UserBean;
import com.gy.bringmoney.model.UserLogModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserLogModelImp implements UserLogModel {

    private UserLogOnListener listener;

    public UserLogModelImp(UserLogOnListener listener) {
        this.listener = listener;
    }

    @Override
    public void login(String username, String password) {
        RetrofitFactory.getInstence().API()
                .login(username, password)
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
    public void signup(String username, String password, String mail) {
        RetrofitFactory.getInstence().API()
                .signup(username, password, mail)
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
    public interface UserLogOnListener {

        void onSuccess(UserBean user);

        void onFailure(Throwable e);
    }
}
