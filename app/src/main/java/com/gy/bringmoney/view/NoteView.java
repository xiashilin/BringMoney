package com.gy.bringmoney.view;

import com.gy.bringmoney.base.BaseView;
import com.gy.bringmoney.bean.BPay2;
import com.gy.bringmoney.bean.BSort2;
import com.gy.bringmoney.bean.NoteBean;

public interface NoteView extends BaseView<NoteBean>{

    /**
     * 请求数据成功
     * @param tData
     */
    void loadDataSuccess(BSort2 tData);
    void loadDataSuccess(BPay2 tData);
}
