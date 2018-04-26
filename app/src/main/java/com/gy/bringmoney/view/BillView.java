package com.gy.bringmoney.view;

import com.gy.bringmoney.base.BaseView;
import com.gy.bringmoney.bean.BaseBean;
import com.gy.bringmoney.bean.NoteBean;

public interface BillView extends BaseView<BaseBean>{

    void loadDataSuccess(NoteBean tData);
}
