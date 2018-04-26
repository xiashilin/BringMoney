package com.gy.bringmoney.presenter.Imp;

import com.gy.bringmoney.bean.MonthChartBean;
import com.gy.bringmoney.model.Imp.MonthChartModelImp;
import com.gy.bringmoney.model.MonthChartModel;
import com.gy.bringmoney.presenter.MonthChartPresenter;
import com.gy.bringmoney.view.MonthChartView;

public class MonthChartPresenterImp extends MonthChartPresenter implements MonthChartModelImp.MonthChartOnListener{

    private MonthChartModel model;
    private MonthChartView view;

    public MonthChartPresenterImp(MonthChartView view) {
        this.model=new MonthChartModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(MonthChartBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }

    @Override
    public void getMonthChartBills(String id, String year, String month) {
        model.getMonthChartBills(id,year,month);
    }

}
