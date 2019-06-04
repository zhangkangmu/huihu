package com.huihu.uilib.album.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bailun.wangjing.permissionlibrary.annotation.RequestPermission;
import com.huihu.uilib.R;
import com.huihu.uilib.album.adapter.AlbumAdapter;
import com.huihu.uilib.album.albuminterface.IAlbumPresenter;
import com.huihu.uilib.album.albuminterface.IAlbumView;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.album.presenter.ImpAlbumPresenter;
import com.huihu.uilib.customize.ItemEqualSpacing;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AlbumActivity extends FragmentActivity implements IAlbumView
        , View.OnClickListener, BasePictureFragment.InteractionActivity {

    private static OnSelectEndListener onSelectEndListener;

    private static final int REQUEST_OPEN_CAMERA = 1000;
    private static final int ALBUM_COLUMN_COUNT = 4;
    private static final String JPEG_FILE_SUFFIX = ".jpg";
    private static final String SELECT_LIMIT = "select_limit";

    private final IAlbumPresenter iAlbumPresenter = new ImpAlbumPresenter(this);

    private TextView mTvComplete;
    private AlbumAdapter mAlbumAdapter;
    private boolean isAddMore = false;
    private BasePictureFragment pictureFragment;
    private int limit;

    @RequestPermission(request = 1, permissions = Manifest.permission.READ_EXTERNAL_STORAGE)
    public static void openSelf(final Context context, int limit, OnSelectEndListener listener ){
        Intent intent = new Intent(context, AlbumActivity.class);
        intent.putExtra(SELECT_LIMIT, limit);
        context.startActivity(intent);
        onSelectEndListener = listener;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comresourcelib_activity_album);
        initView();
        initData();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isAddMore = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        onSelectEndListener = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_OPEN_CAMERA && resultCode == RESULT_OK){
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(tempFile);
            mediaScanIntent.setData(contentUri);
            this.sendBroadcast(mediaScanIntent);
            addSinglePicture(contentUri.getPath());
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSelectList(AlbumImageBean bean){
        mAlbumAdapter.notifyChangeSelectList(bean);
    }

    private void initView(){
        ImageView mIvBack = findViewById(R.id.iv_back);
        RecyclerView mRvAlbum = findViewById(R.id.rv_album);
        TextView mTvCancel = findViewById(R.id.tv_cancel);
        TextView mTvPreview = findViewById(R.id.tv_preview);
        mTvComplete = findViewById(R.id.tv_complete);
        GridLayoutManager layoutManager = new GridLayoutManager(this, ALBUM_COLUMN_COUNT);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRvAlbum.setLayoutManager(layoutManager);
        mAlbumAdapter = new AlbumAdapter(this, iAlbumPresenter);
        mAlbumAdapter.setImageBeanList(new ArrayList<AlbumImageBean>());
        mRvAlbum.setAdapter(mAlbumAdapter);
        mRvAlbum.addItemDecoration(new ItemEqualSpacing(this, ALBUM_COLUMN_COUNT, 6));
        mIvBack.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
        mTvPreview.setOnClickListener(this);
        mTvComplete.setOnClickListener(this);
        mAlbumAdapter.setOnSelectListener(new AlbumAdapter.OnSelectListener() {
            @Override
            public void onSelect(int count) {
                if (count > 0) {
                    mTvComplete.setText(String.format(getString(R.string.comresourcelib_album_complete_number), count));
                } else {
                    mTvComplete.setText(getString(R.string.comresourcelib_album_complete));
                }
            }
        });
        limit = getIntent().getIntExtra(SELECT_LIMIT, 9);
        mAlbumAdapter.setLimit(limit);
    }

    private void initData(){
        iAlbumPresenter.v2pGetPicture();
    }

    @Override
    public void onBackPressed() {
        if (pictureFragment != null){
            finishFragment();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_back) {
            finish();
        } else if (i == R.id.tv_cancel){
            Iterator<AlbumImageBean> iterator = mAlbumAdapter.getSelectImageBeanList().iterator();
            while (iterator.hasNext()) {
                AlbumImageBean bean = iterator.next();
                iterator.remove();
                mAlbumAdapter.notifySelectOrderChange(bean);
            }
            mTvComplete.setText(getString(R.string.comresourcelib_album_complete));
        } else if (i == R.id.tv_complete) {
            onSelectComplete();
        } else if (i == R.id.tv_preview){
            if (mAlbumAdapter.getSelectImageBeanList().size() > 0){
                iAlbumPresenter.v2pOpenPictureFragment(1, 0);
            }
        }
    }

    @Override
    public void p2vShowPicture(final List<AlbumImageBean> imageList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAlbumAdapter.setImageBeanList(imageList);
                mAlbumAdapter.notifyDataSetChanged();
                isAddMore = true;
                iAlbumPresenter.v2pGetMorePicture();
            }
        });
    }

    @Override
    public void p2vShowMorePicture(final List<AlbumImageBean> imageList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final int oldCount = mAlbumAdapter.getItemCount();
                mAlbumAdapter.getImageBeanList().addAll(imageList);
                final int newCount = mAlbumAdapter.getItemCount();
                mAlbumAdapter.notifyItemRangeInserted(oldCount, newCount);
                if (isAddMore) iAlbumPresenter.v2pGetMorePicture();
            }
        });
    }

    File tempFile;
    @RequestPermission(request = 1, permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE})
    @Override
    public void p2vOpenSystemCamera() {
        tempFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath(),
                System.currentTimeMillis() + JPEG_FILE_SUFFIX);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, "com.huihu", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_OPEN_CAMERA);
    }

    @Override
    public void p2vOpenPictureFragment(int type, int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (pictureFragment == null){
            pictureFragment = type == 0
                    ? AlbumPictureFragment.newInstance(position, limit)
                    : PreviewPictureFragment.newInstance(position, limit);
            transaction.add(R.id.cl_root, pictureFragment);
        }
        if (pictureFragment.isHidden()) transaction.show(pictureFragment);
        transaction.commit();
    }


    private void addSinglePicture(String path){
        AlbumImageBean bean = new AlbumImageBean(path);
        mAlbumAdapter.getImageBeanList().add(0, bean);
        mAlbumAdapter.notifyItemInserted(1);
    }

    private void onSelectComplete(){
        if (onSelectEndListener != null) onSelectEndListener.onSelectEnd(mAlbumAdapter.getSelectImageBeanList());
        finish();
    }

    @Override
    public List<AlbumImageBean> getAlbumImageList() {
        return mAlbumAdapter.getImageBeanList();
    }

    @Override
    public List<AlbumImageBean> getSelectList() {
        return mAlbumAdapter.getSelectImageBeanList();
    }

    @Override
    public void notifySelectListChange(AlbumImageBean bean) {
        mAlbumAdapter.notifySelectOrderChange(bean);
    }

    @Override
    public void finishFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(pictureFragment);
        transaction.commit();
        pictureFragment = null;
    }

    @Override
    public void completeSelect() {
        onSelectComplete();
    }


    public interface OnSelectEndListener{
        void onSelectEnd(List<AlbumImageBean> beanList);
    }

}
