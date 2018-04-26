package com.gy.bringmoney.presenter.Imp;

import com.gy.bringmoney.bean.BaseBean;
import com.gy.bringmoney.bean.NoteBean;
import com.gy.bringmoney.model.BillModel;
import com.gy.bringmoney.model.Imp.BillModelImp;
import com.gy.bringmoney.presenter.BillPresenter;
import com.gy.bringmoney.view.BillView;

public class BillPresenterImp extends BillPresenter implements BillModelImp.BillOnListener{

    private BillModel model;
    private BillView view;

    public BillPresenterImp(BillView view) {
        this.model=new BillModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(BaseBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(NoteBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }

    @Override
    public void getNote(int id) {
        model.getNote(id);
    }

    @Override
    public void add(int userid, int sortid, int payid, String cost, String content, String crdate, boolean income) {
        model.add(userid,sortid,payid,cost,content,crdate,income);
    }

    @Override
    public void update(int id, int userid, int sortid, int payid, String cost, String content, String crdate, boolean income) {
        model.update(id,userid,sortid,payid,cost,content,crdate,income);
    }

    @Override
    public void delete(int id) {
        model.delete(id);
    }
}
