package com.tidy.tidyegg.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.widget.citylist.adapter.CityListAdapter;
import com.tidy.tidyegg.widget.citylist.db.DBHelper;
import com.tidy.tidyegg.widget.citylist.db.DatabaseHelper;
import com.tidy.tidyegg.widget.citylist.entity.City;
import com.tidy.tidyegg.widget.citylist.utils.DensityUtil;
import com.tidy.tidyegg.widget.citylist.utils.PingYinUtil;
import com.tidy.tidyegg.widget.citylist.view.LetterListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by qiang on 2016/10/10.
 * 选择城市界面
 */
public class ChooseCityAty extends BaseActivity implements LetterListView.OnTouchingLetterChangedListener, AbsListView.OnScrollListener {
    private RelativeLayout title;
    private ListView city_container;
    private LetterListView letter_container;

    private List<City> allCities = new ArrayList<>();
    private List<City> hotCities = new ArrayList<>();
    private List<String> historyCities = new ArrayList<>();
    private List<City> citiesData;
    private Map<String, Integer> letterIndex = new HashMap<>();
    private CityListAdapter cityListAdapter;

    private TextView letterOverlay; // 对话框首字母textview
    private OverlayThread overlayThread; // 显示首字母对话框
    private DatabaseHelper databaseHelper;

    private boolean isScroll;
    private boolean isOverlayReady;
    private Handler handler;

    private WindowManager windowManager;

    @Override
    protected int getLayoutId() {
        return R.layout.aty_choose_city;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        title = (RelativeLayout) findViewById(R.id.title_chooseCity);
        setLeft(title);
        setTitle(title, "选择城市");

        city_container = (ListView) findViewById(R.id.city_container);
        letter_container = (LetterListView) findViewById(R.id.letter_container);

        databaseHelper = new DatabaseHelper(this);
        handler = new Handler();

        initCity();
        initHotCity();
        addHistoryCity("上海");
        addHistoryCity("北京");
        addHistoryCity("广州");
        initHistoryCity();
        setupView();
        initOverlay();
    }

    private void initCity() {
        City city = new City("定位", "0"); // 当前定位城市
        allCities.add(city);
        city = new City("最近", "1"); // 最近访问的城市
        allCities.add(city);
        city = new City("热门", "2"); // 热门城市
        allCities.add(city);
        city = new City("全部", "3"); // 全部城市
        allCities.add(city);
        citiesData = getCityList();
        allCities.addAll(citiesData);
    }

    /**
     * 热门城市
     */
    public void initHotCity() {
        City city = new City("北京", "2");
        hotCities.add(city);
        city = new City("上海", "2");
        hotCities.add(city);
        city = new City("广州", "2");
        hotCities.add(city);
        city = new City("深圳", "2");
        hotCities.add(city);
        city = new City("武汉", "2");
        hotCities.add(city);
        city = new City("天津", "2");
        hotCities.add(city);
        city = new City("西安", "2");
        hotCities.add(city);
        city = new City("南京", "2");
        hotCities.add(city);
        city = new City("杭州", "2");
        hotCities.add(city);
        city = new City("成都", "2");
        hotCities.add(city);
        city = new City("重庆", "2");
        hotCities.add(city);
    }

    private void initHistoryCity() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from recent_city order by date desc limit 0, 3", null);
        while (cursor.moveToNext()) {
            historyCities.add(cursor.getString(1));
        }
        cursor.close();
        db.close();
    }

    public void addHistoryCity(String name) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from recent_city where name = '" + name + "'", null);
        if (cursor.getCount() > 0) {
            db.delete("recent_city", "name = ?", new String[]{name});
        }
        db.execSQL("insert into recent_city(name, date) values('" + name + "', " + System.currentTimeMillis() + ")");
        db.close();
    }


    private ArrayList<City> getCityList() {
        DBHelper dbHelper = new DBHelper(this);
        ArrayList<City> list = new ArrayList<>();
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from city", null);
            City city;
            while (cursor.moveToNext()) {
                city = new City(cursor.getString(1), cursor.getString(2));
                list.add(city);
            }
            cursor.close();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(list, comparator);
        return list;
    }


    /**
     * a-z排序
     */
    Comparator comparator = new Comparator<City>() {
        @Override
        public int compare(City lhs, City rhs) {
            String a = lhs.getPinyin().substring(0, 1);
            String b = rhs.getPinyin().substring(0, 1);
            int flag = a.compareTo(b);
            if (flag == 0) {
                return a.compareTo(b);
            } else {
                return flag;
            }
        }
    };

    private void setupView() {
        city_container.setOnScrollListener(this);
        letter_container.setOnTouchingLetterChangedListener(this);

        cityListAdapter = new CityListAdapter(ChooseCityAty.this, this, allCities, hotCities, historyCities, letterIndex);
        city_container.setAdapter(cityListAdapter);
        city_container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City bean = (City) parent.getAdapter().getItem(position);
                Intent data = new Intent();
                data.putExtra("city", bean.getName());
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }

    // 初始化汉语拼音首字母弹出提示框
    private void initOverlay() {
        overlayThread = new OverlayThread();
        isOverlayReady = true;
        LayoutInflater inflater = LayoutInflater.from(this);
        letterOverlay = (TextView) inflater.inflate(R.layout.v_letter_overlay, null);
        letterOverlay.setVisibility(View.INVISIBLE);

        int width = DensityUtil.dp2px(this, 65);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                width, width,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        windowManager = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(letterOverlay, lp);
    }

    @Override
    protected void onPause() {
        super.onPause();
        windowManager.removeView(letterOverlay);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_TOUCH_SCROLL || scrollState == SCROLL_STATE_FLING) {
            isScroll = true;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (!isScroll) {
            return;
        }
        if (isOverlayReady) {
            String text;
            String name = allCities.get(firstVisibleItem).getName();
            String pinyin = allCities.get(firstVisibleItem).getPinyin();
            if (firstVisibleItem < 4) {
                text = name;
            } else {
                text = PingYinUtil.converterToFirstSpell(pinyin).substring(0, 1).toUpperCase();
            }
            Pattern pattern = Pattern.compile("^[A-Za-z]+$");
            if (pattern.matcher(text).matches()) {
                letterOverlay.setTextSize(40);
            } else {
                letterOverlay.setTextSize(20);
            }
            letterOverlay.setText(text);
            letterOverlay.setVisibility(View.VISIBLE);
            handler.removeCallbacks(overlayThread);
            // 延迟一秒后执行，让overlay为不可见
            handler.postDelayed(overlayThread, 1000);
        }
    }

    @Override
    public void onTouchingLetterChanged(String s) {
        isScroll = false;
        if (letterIndex.get(s) != null) {
            int position = letterIndex.get(s);
            city_container.setSelection(position);
            Pattern pattern = Pattern.compile("^[A-Za-z]+$");
            if (pattern.matcher(s).matches()) {
                letterOverlay.setTextSize(40);
            } else {
                letterOverlay.setTextSize(20);
            }
            letterOverlay.setText(s);
            letterOverlay.setVisibility(View.VISIBLE);
            handler.removeCallbacks(overlayThread);
            handler.postDelayed(overlayThread, 1000);
        }
    }

    private class OverlayThread implements Runnable {
        @Override
        public void run() {
            letterOverlay.setVisibility(View.GONE);
        }
    }
}