package com.gy.bringmoney.presenter.Imp;

import com.gy.bringmoney.bean.BaseBean;
import com.gy.bringmoney.bean.MonthDetailBean;
import com.gy.bringmoney.model.Imp.MonthDetailModelImp;
import com.gy.bringmoney.model.MonthDetailModel;
import com.gy.bringmoney.presenter.MonthDetailPresenter;
import com.gy.bringmoney.view.MonthDetailView;

public class MonthDetailPresenterImp extends MonthDetailPresenter implements MonthDetailModelImp.MonthDetailOnListener{

    private MonthDetailModel monthDetailModel;
    private MonthDetailView monthDetailView;

    public MonthDetailPresenterImp(MonthDetailView monthDetailView) {
        this.monthDetailModel=new MonthDetailModelImp(this);
        this.monthDetailView = monthDetailView;
    }

    @Override
    public void onSuccess(MonthDetailBean bean) {
        monthDetailView.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(BaseBean bean) {
        monthDetailView.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        monthDetailView.loadDataError(e);
    }

    @Override
    public void getMonthDetailBills(String id, String year, String month) {
        monthDetailModel.getMonthDetailBills(id,year,month);
    }

    @Override
    public void deleteBill(int id) {
        monthDetailModel.delete(id);
    }

}
