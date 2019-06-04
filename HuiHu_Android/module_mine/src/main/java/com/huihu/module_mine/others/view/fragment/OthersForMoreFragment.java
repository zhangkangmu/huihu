package com.huihu.module_mine.others.view.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.uilib.complaint.view.ComplaintDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OthersForMoreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OthersForMoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OthersForMoreFragment extends BaseFragment {
    @BindView(R2.id.iv_back)
    ImageView iv_back;
    @BindView(R2.id.tv_base)
    TextView tv_base;
    Unbinder unbinder;
    private long uid;
    public static OthersForMoreFragment newInstance(long uid) {
        OthersForMoreFragment fragment = new OthersForMoreFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("uid", uid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.module_mine__others_for_more, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            uid = bundle.getLong("uid");
        }
        TextViewUtils.setTextFakeBold(tv_base);
        tv_base.setText("更多");
    }
    @OnClick({R2.id.iv_back,R2.id.cons_report})
    public void OnClick(View view){
        int id = view.getId();
        if (id==R.id.iv_back) {
            pop();
        }else if(id==R.id.cons_report){
            ComplaintDialog.newInstance(getContext(),1,uid).show();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
