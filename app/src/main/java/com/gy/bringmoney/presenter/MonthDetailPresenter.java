package com.gy.bringmoney.presenter;

import com.gy.bringmoney.base.BasePresenter;

public abstract  class MonthDetailPresenter extends BasePresenter {

    public abstract void getMonthDetailBills(String id,String year,String month);

    public abstract void deleteBill(int id);
}
