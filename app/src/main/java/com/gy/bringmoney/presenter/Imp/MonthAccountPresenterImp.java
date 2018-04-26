package com.gy.bringmoney.presenter.Imp;

import com.gy.bringmoney.bean.MonthAccountBean;
import com.gy.bringmoney.model.Imp.MonthAccountModelImp;
import com.gy.bringmoney.model.MonthAccountModel;
import com.gy.bringmoney.presenter.MonthAccountPresenter;
import com.gy.bringmoney.view.MonthAccountView;

public class MonthAccountPresenterImp extends MonthAccountPresenter implements MonthAccountModelImp.MonthAccountOnListener{

    private MonthAccountModel model;
    private MonthAccountView view;

    public MonthAccountPresenterImp(MonthAccountView view) {
        this.model=new MonthAccountModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(MonthAccountBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }


    @Override
    public void getMonthAccountBills(String id, String year, String month) {
        model.getMonthAccountBills(id,year,month);
    }
}
