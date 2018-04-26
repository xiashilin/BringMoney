package com.gy.bringmoney.model.Imp;

import com.gy.bringmoney.api.RetrofitFactory;
import com.gy.bringmoney.base.BaseObserver;
import com.gy.bringmoney.bean.MonthChartBean;
import com.gy.bringmoney.model.MonthChartModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MonthChartModelImp implements MonthChartModel {

    private MonthChartOnListener listener;

    public MonthChartModelImp(MonthChartOnListener listener) {
        this.listener = listener;
    }

    @Override
    public void getMonthChartBills(String id, String year, String month) {
        RetrofitFactory.getInstence().API()
                .getMonthChart(id,year,month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MonthChartBean>() {
                    @Override
                    protected void onSuccees(MonthChartBean monthChartBean) throws Exception {
                        listener.onSuccess(monthChartBean);
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
    public interface MonthChartOnListener {

        void onSuccess(MonthChartBean bean);

        void onFailure(Throwable e);
    }
}
