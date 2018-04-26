package com.gy.bringmoney.presenter.Imp;

import com.gy.bringmoney.bean.BPay2;
import com.gy.bringmoney.bean.BSort2;
import com.gy.bringmoney.bean.NoteBean;
import com.gy.bringmoney.model.Imp.NoteModelImp;
import com.gy.bringmoney.model.NoteModel;
import com.gy.bringmoney.presenter.NotePresenter;
import com.gy.bringmoney.view.NoteView;

public class NotePresenterImp extends NotePresenter implements NoteModelImp.NoteOnListener{

    private NoteModel model;
    private NoteView view;

    public NotePresenterImp(NoteView view) {
        this.model=new NoteModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(NoteBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(BSort2 bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(BPay2 bean) {
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
    public void addSort(int userid, String sortName, String sortImg, boolean income) {
        model.addSort(userid,sortName,sortImg,income);
    }

    @Override
    public void addPay(int userid, String payName, String payImg, String payNum) {
        model.addPay(userid,payName,payImg,payNum);
    }

    @Override
    public void delete(int id) {

    }
}
