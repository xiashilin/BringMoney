package com.gy.bringmoney.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.gy.bringmoney.R;
import com.gy.bringmoney.adapter.MainFragmentPagerAdapter;
import com.gy.bringmoney.fragment.MonthAccountFragment;
import com.gy.bringmoney.fragment.MonthChartFragment;
import com.gy.bringmoney.fragment.MonthDetailFragment;
import com.gy.bringmoney.common.Constants;

import butterknife.BindView;
import butterknife.OnClick;
import com.gy.bringmoney.utils.ImageUtils;
import com.gy.bringmoney.utils.SharedPUtils;
import com.gy.bringmoney.utils.ThemeManager;

import java.io.File;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.main_viewpager)
    ViewPager viewPager;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private View drawerHeader;
    private ImageView drawerIv;
    private TextView drawerTvAccount, drawerTvMail;

    protected static final int USERINFOACTIVITY_CODE = 0;
    protected static final int LOGINACTIVITY_CODE = 1;

    // Tab
    private FragmentManager mFragmentManager;
    private MainFragmentPagerAdapter mainFragmentPagerAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {


        //初始化ViewPager
        mFragmentManager = getSupportFragmentManager();
        mainFragmentPagerAdapter = new MainFragmentPagerAdapter(mFragmentManager);
        mainFragmentPagerAdapter.addFragment(new MonthDetailFragment(), "明细");
        mainFragmentPagerAdapter.addFragment(new MonthChartFragment(), "报表");
        mainFragmentPagerAdapter.addFragment(new MonthAccountFragment(), "卡片");

        viewPager.setAdapter(mainFragmentPagerAdapter);

        //初始化TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("明细"));
        tabLayout.addTab(tabLayout.newTab().setText("报表"));
        tabLayout.addTab(tabLayout.newTab().setText("卡片"));
        tabLayout.setupWithViewPager(viewPager);

        //初始化Toolbar
        toolbar.setTitle("引财");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        drawerHeader = navigationView.inflateHeaderView(R.layout.drawer_header);
        drawerIv = (ImageView) drawerHeader.findViewById(R.id.drawer_iv);
        drawerTvAccount = (TextView) drawerHeader.findViewById(R.id.drawer_tv_name);
        drawerTvMail = (TextView) drawerHeader.findViewById(R.id.drawer_tv_mail);
        //监听点击登陆事件
        drawerHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                if (currentUser==null) {
                    //用户id为0表示未有用户登陆
                    startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), LOGINACTIVITY_CODE);
                } else {
                    //进入账户中心
                    startActivityForResult(new Intent(MainActivity.this, UserInfoActivity.class), USERINFOACTIVITY_CODE);
                }
            }
        });
        //设置头部账户
        setDrawerHeaderAccount();
        //监听侧滑菜单
        navigationView.setNavigationItemSelectedListener(this);

    }

    /**
     * 设置DrawerHeader的用户信息
     */
    public void setDrawerHeaderAccount() {
        //获取当前用户
        currentUser = SharedPUtils.getCurrentUser(this);
        if (currentUser != null) {
            drawerTvAccount.setText(currentUser.getUsername());
            drawerTvMail.setText(currentUser.getMail());
            String imgPath = Environment
                    .getExternalStorageDirectory().getAbsolutePath()+"/"+currentUser.getImage();
            File file = new File(imgPath);
            //判断头像文件是否存在
            if (file.exists()) {
                //加载本地图片
                Glide.with(this).load(file).into(drawerIv);
            } else {
                //加载网络图片到本地
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = null;
                        try {
                            bitmap = Glide.with(MainActivity.this)
                                    .load(Constants.BASE_URL + Constants.IMAGE_USER + currentUser.getImage())
                                    .asBitmap()
                                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                    .get();
                            if (bitmap != null) {
                                drawerIv.setImageBitmap(bitmap);
                                Log.i(TAG,"SD可写："+Environment.getExternalStorageDirectory().canWrite()+
                                        "SD可读："+Environment.getExternalStorageDirectory().canRead());
                                String imgPath=ImageUtils.savePhoto(bitmap, Environment
                                        .getExternalStorageDirectory().getAbsolutePath(), currentUser.getImage());

                                Log.i(TAG,imgPath);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
            Constants.currentUserId = currentUser.getId();
        }else{
            drawerTvAccount.setText("账号");
            drawerTvMail.setText("点我登陆");
            drawerIv.setImageResource(R.mipmap.ic_def_icon);
            Constants.currentUserId=0;
        }
    }

    /**
     * 显示修改主题色 Dialog
     */
    private void showUpdateThemeDialog() {
        final String[] themes = ThemeManager.getInstance().getThemes();
        new AlertDialog.Builder(mContext)
                .setTitle("选择主题")
                .setItems(themes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ThemeManager.getInstance().setTheme(mContext, themes[which]);
                    }
                }).create().show();
    }

    /**
     * 监听Drawer
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 监听点击事件 R.id.drawer_tv_name,R.id.drawer_tv_mail
     *
     * @param view
     */
    @OnClick({})
    public void onClick(View view) {
        switch (view.getId()) {

            default:
                break;
        }
    }

    /**
     * 监听Activity返回值
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case USERINFOACTIVITY_CODE:
                    setDrawerHeaderAccount();
                    break;
                case LOGINACTIVITY_CODE:
                    setDrawerHeaderAccount();
                    break;
            }
        }
    }

    /**
     * 监听侧滑菜单事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_account) {      //账户
            // Handle the camera action
            viewPager.setCurrentItem(0);
        } else if (id == R.id.nav_month) {
            viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_total) {
            viewPager.setCurrentItem(2);
        } else if (id == R.id.nav_setting) {   //设置
            startActivity(new Intent(this,SettingActivity.class));
        } else if (id == R.id.nav_about) {     //关于
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        } else if (id == R.id.nav_theme) {     //主题
            showUpdateThemeDialog();
        } else if (id == R.id.nav_exit) {      //退出登陆
            //退出登陆
            new AlertDialog.Builder(mContext).setTitle("是否退出当前账户")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //清除登陆信息
                            SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                            if (sp != null)
                                sp.edit().clear().commit();
                            //刷新账户数据
                            setDrawerHeaderAccount();
                        }
                    })
                    .show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
