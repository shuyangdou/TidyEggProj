package com.tidy.tidyegg.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.adapter.EValuationDetailAdapter;
import com.tidy.tidyegg.app.AppConfig;
import com.tidy.tidyegg.base.BaseFragment;
import com.tidy.tidyegg.bean.ValuationDetail;
import com.tidy.tidyegg.utils.ValueUtils;
import com.tidy.tidyegg.widget.recyclerview.SpaceItemDecoration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by qiang on 16/10/18.
 * 各类型鸡蛋的评价详情列表
 */
public class EggeValuationDetailFrag extends BaseFragment {
    @BindView(R.id.recyclerView_valuationDetail)
    RecyclerView mRecyclerView;

    private String goodsID;// 商品名称

    private List<ValuationDetail> list = new ArrayList<>();

    public EggeValuationDetailFrag() {
    }

    public EggeValuationDetailFrag(String goodsID) {
        this.goodsID = goodsID;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_egge_valuationdetail;
    }

    @Override
    protected void initData() {
        mRecyclerView.setFocusable(false);
        mRecyclerView.setFocusableInTouchMode(false);
        mRecyclerView.setNestedScrollingEnabled(false);

        if (ValueUtils.isStrNotEmpty(goodsID)) {
            if (goodsID.equals("100003")) {
                readExcel(1, 7);
            } else if (goodsID.equals("100023")) {
                readExcel(7, 13);
            } else if (goodsID.equals("100038")) {
                readExcel(13, 19);
            } else if (goodsID.equals("100025")) {
                readExcel(19, 25);
            } else if (goodsID.equals("100004")) {
                readExcel(25, 31);
            } else if (goodsID.equals("100014")) {
                readExcel(31, 37);
            } else if (goodsID.equals("100020")) {
                readExcel(37, 43);
            } else if (goodsID.equals("100033")) {
                readExcel(43, 49);
            }

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.addItemDecoration(new SpaceItemDecoration(
                    getActivity().getResources().getDimensionPixelSize(R.dimen.space_5),
                    0, 0, 0
            ));
            EValuationDetailAdapter adapter = new EValuationDetailAdapter(list, getActivity());
            mRecyclerView.setAdapter(adapter);
        }
    }

    private void readExcel(int start, int end) {
        try {
            InputStream is = getActivity().getApplicationContext().getAssets().open("data.xls");
            Workbook book = Workbook.getWorkbook(is);
            int num = book.getNumberOfSheets();
            Log.i(AppConfig.TAG, "the num of sheets is " + num + "\n");
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();
            int Cols = sheet.getColumns();
            ValuationDetail entity = null;
            for (int i = start; i < end; ++i) {
                String user = sheet.getCell(6, i).getContents();
                String time = sheet.getCell(7, i).getContents();
                String content = sheet.getCell(8, i).getContents();
                entity = new ValuationDetail(user, time, content);
                list.add(entity);
            }
            book.close();
        } catch (Exception e) {
            toast("读取表格失败~");
            e.printStackTrace();
        }
        for (ValuationDetail detail : list) {
            Log.i(AppConfig.TAG, detail.getContent());
        }
    }

}
