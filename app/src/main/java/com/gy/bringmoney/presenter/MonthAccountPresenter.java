package com.gy.bringmoney.presenter;

import com.gy.bringmoney.base.BasePresenter;

public abstract  class MonthAccountPresenter extends BasePresenter {

    public abstract void getMonthAccountBills(String id,String year,String month);
}
