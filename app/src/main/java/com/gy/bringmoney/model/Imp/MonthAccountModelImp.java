package com.gy.bringmoney.model.Imp;

import com.gy.bringmoney.api.RetrofitFactory;
import com.gy.bringmoney.base.BaseObserver;
import com.gy.bringmoney.bean.MonthAccountBean;
import com.gy.bringmoney.model.MonthAccountModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MonthAccountModelImp implements MonthAccountModel {

    private MonthAccountOnListener listener;

    public MonthAccountModelImp(MonthAccountOnListener listener) {
        this.listener = listener;
    }


    @Override
    public void getMonthAccountBills(String id, String year, String month) {
        RetrofitFactory.getInstence().API()
                .getMonthAccount(id,year,month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MonthAccountBean>() {
                    @Override
                    protected void onSuccees(MonthAccountBean bean) throws Exception {
                        listener.onSuccess(bean);
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
    public interface MonthAccountOnListener {

        void onSuccess(MonthAccountBean bean);

        void onFailure(Throwable e);
    }
}
