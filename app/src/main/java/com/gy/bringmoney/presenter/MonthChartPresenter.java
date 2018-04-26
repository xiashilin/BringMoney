package com.gy.bringmoney.presenter;

import com.gy.bringmoney.base.BasePresenter;

public abstract  class MonthChartPresenter extends BasePresenter {

    public abstract void getMonthChartBills(String id,String year,String month);
}
