package com.gy.bringmoney.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.Toast;
import butterknife.OnClick;
import com.bigkoo.pickerview.TimePickerView;
import com.gy.bringmoney.R;
import com.gy.bringmoney.adapter.AccountCardAdapter;
import com.gy.bringmoney.bean.MonthAccountBean;
import com.gy.bringmoney.common.Constants;
import com.gy.bringmoney.presenter.Imp.MonthAccountPresenterImp;
import com.gy.bringmoney.presenter.MonthAccountPresenter;
import com.gy.bringmoney.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

import com.gy.bringmoney.utils.SnackbarUtils;
import com.gy.bringmoney.view.MonthAccountView;

import static com.gy.bringmoney.utils.DateUtils.FORMAT_M;
import static com.gy.bringmoney.utils.DateUtils.FORMAT_Y;

/**
 * 我的账户
 */
public class MonthAccountFragment extends BaseFragment implements MonthAccountView{

    @BindView(R.id.data_year)
    TextView dataYear;
    @BindView(R.id.data_month)
    TextView dataMonth;
    @BindView(R.id.layout_data)
    LinearLayout layoutData;
    @BindView(R.id.t_outcome)
    TextView tOutcome;
    @BindView(R.id.t_income)
    TextView tIncome;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    private MonthAccountPresenter presenter;

    private AccountCardAdapter adapter;

    private MonthAccountBean monthAccountBean;
    private List<MonthAccountBean.PayTypeListBean> list;

    private String setYear = DateUtils.getCurYear(FORMAT_Y);
    private String setMonth = DateUtils.getCurMonth(FORMAT_M);


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu_account;
    }


    @Override
    protected void initEventAndData() {

        initView();

        presenter=new MonthAccountPresenterImp(this);

        //请求当月数据
        getAcountData(Constants.currentUserId, setYear, setMonth);
    }

    /**
     * 初始化view
     */
    private void initView() {
        dataYear.setText(DateUtils.getCurYear("yyyy 年"));
        dataMonth.setText(DateUtils.date2Str(new Date(), "MM"));
        //改变加载显示的颜色
        swipe.setColorSchemeColors(getResources().getColor(R.color.text_red), getResources().getColor(R.color.text_red));
        //设置向下拉多少出现刷新
        swipe.setDistanceToTriggerSync(200);
        //设置刷新出现的位置
        swipe.setProgressViewEndTarget(false, 200);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(false);
                getAcountData(Constants.currentUserId, setYear, setMonth);
            }
        });

        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AccountCardAdapter(getActivity(), list);
        adapter.setmListener(new AccountCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        rvList.setAdapter(adapter);
    }

    /**
     * 获取账单数据
     * @param userid
     * @param year
     * @param month
     */
    private void getAcountData(final int userid, String year, String month) {
        if (userid==0){
            Toast.makeText(getContext(), "请先登陆", Toast.LENGTH_SHORT).show();
            return;
        }
        dataYear.setText(setYear + " 年");
        dataMonth.setText(setMonth);

        presenter.getMonthAccountBills(String.valueOf(userid),year,month);
    }

    @Override
    public void loadDataSuccess(MonthAccountBean tData) {
        monthAccountBean=tData;
        tOutcome.setText(monthAccountBean.getTotalOut());
        tIncome.setText(monthAccountBean.getTotalIn());
        list = monthAccountBean.getList();
        adapter.setmDatas(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadDataError(Throwable throwable) {
        SnackbarUtils.show(mActivity,throwable.getMessage());
    }

    @OnClick({R.id.layout_data,R.id.top_ll_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_data:
                //时间选择器
                new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        setYear=DateUtils.date2Str(date, "yyyy");
                        setMonth=DateUtils.date2Str(date, "MM");
                        getAcountData(Constants.currentUserId,setYear,setMonth);
                    }
                }).setRangDate(null, Calendar.getInstance())
                        .setType(new boolean[]{true, true, false, false, false, false})
                        .build()
                        .show();
                break;
            case R.id.top_ll_out:

                break;
        }
    }
}
