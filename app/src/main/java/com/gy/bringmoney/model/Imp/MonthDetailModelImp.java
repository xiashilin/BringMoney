package com.gy.bringmoney.model.Imp;

import com.gy.bringmoney.api.RetrofitFactory;
import com.gy.bringmoney.base.BaseObserver;
import com.gy.bringmoney.bean.BaseBean;
import com.gy.bringmoney.bean.MonthDetailBean;
import com.gy.bringmoney.model.MonthDetailModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MonthDetailModelImp implements MonthDetailModel {

    private MonthDetailOnListener listener;

    public MonthDetailModelImp(MonthDetailOnListener listener) {
        this.listener = listener;
    }


    @Override
    public void getMonthDetailBills(String id, String year, String month) {
        RetrofitFactory.getInstence().API()
                .getMonthDetial(id,year,month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MonthDetailBean>() {
                    @Override
                    protected void onSuccees(MonthDetailBean monthDetailBean) throws Exception {
                        listener.onSuccess(monthDetailBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
    }

    @Override
    public void delete(int id) {
        RetrofitFactory.getInstence().API()
                .deleteBill(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    protected void onSuccees(BaseBean baseBean) throws Exception {
                        listener.onSuccess(baseBean);
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
    public interface MonthDetailOnListener {

        void onSuccess(MonthDetailBean bean);

        void onSuccess(BaseBean bean);

        void onFailure(Throwable e);
    }
}
