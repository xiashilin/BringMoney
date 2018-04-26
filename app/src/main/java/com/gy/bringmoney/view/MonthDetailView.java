package com.gy.bringmoney.view;

import com.gy.bringmoney.base.BaseView;
import com.gy.bringmoney.bean.BaseBean;
import com.gy.bringmoney.bean.MonthDetailBean;

public interface MonthDetailView extends BaseView<MonthDetailBean>{

    /**
     * 删除成功
     * @param tData
     */
    void loadDataSuccess(BaseBean tData);
}
