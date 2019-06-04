package com.huihu.module_mine.editprofile.view;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bailun.wangjing.permissionlibrary.annotation.RequestPermission;
import com.bumptech.glide.Glide;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.editname.view.EditNameFragment;
import com.huihu.module_mine.editprofile.editprofileinterface.IEditProfilePresenter;
import com.huihu.module_mine.editprofile.editprofileinterface.IEditProfileView;
import com.huihu.module_mine.editprofile.entity.User;
import com.huihu.module_mine.editprofile.presenter.ImpEditProfilePresenter;
import com.huihu.module_mine.editsignature.view.EditSignatureFragment;
import com.huihu.module_mine.edittelphone.view.EditTelphoneFragment;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.album.view.AlbumActivity;
import com.huihu.uilib.album.view.AlbumActivity.OnSelectEndListener;
import com.huihu.uilib.customize.LoadingDialog;
import com.huihu.uilib.imagecrop.customize.CropImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 编辑个人资料
 */
public class EditProfileFragment extends BaseFragment implements IEditProfileView {
    private static final String TAG = "EditProfileFragment";

    private final IEditProfilePresenter iEditProfilePresenter = new ImpEditProfilePresenter(this);
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.iv_photo)
    ImageView mImPhoto;
    @BindView(R2.id.ll_name)
    LinearLayout mLlName;
    @BindView(R2.id.tv_name)
    TextView mTvName;
    @BindView(R2.id.ll_sex)
    LinearLayout mLlSex;
    @BindView(R2.id.tv_sex)
    TextView mTvSex;
    @BindView(R2.id.ll_tel)
    LinearLayout mLlTel;
    @BindView(R2.id.tv_tel)
    TextView mTvTel;
    @BindView(R2.id.ll_signature)
    LinearLayout mLlSignature;
    @BindView(R2.id.tv_signature)
    TextView mTvSignature;
    @BindView(R2.id.ll_education)
    LinearLayout mLlEducation;
    @BindView(R2.id.tv_education)
    TextView mTvEducation;
    @BindView(R2.id.ll_industry)
    LinearLayout mLlIndustry;
    @BindView(R2.id.tv_industry)
    TextView mTvIndustry;

    private BottomSheetDialog mBsdPhoto;
    private NumberPickerView mNpvSex;
    private BottomSheetDialog mBsdSex;
    private NumberPickerView mNpvEducation;
    private BottomSheetDialog mBsdEducation;
    private NumberPickerView mNpvIndustry;
    private BottomSheetDialog mBsdIndustry;

    private LoadingDialog mLoadingDialog;
    private OnSelectEndListener mImageSelectEndListener;
    private AlbumImageBean mImageBean;
    private Bitmap mCropedBitmap;

    private User mUser;
    File tempPhoto;
    File cutPhoto;
    private List<AlbumImageBean> mAlbumImageBeans = new ArrayList<>();
    Unbinder unbinder;

    private static final int REQUEST_OPEN_CAMERA = 1000;
    private static final String JPEG_FILE_SUFFIX = ".jpg";

    public static final String NAME = "name";
    public static final int RESULT_UPDATE_NAME_SUCCESS = 101;
    private static final int REQUEST_UPDATE_NAME = 11;

    private static final int REQUEST_UPDATE_PHONE = 12;
    private static final int RESULT_UPDATE_PHONE_SUCCESS = 102;
    private static final String PHONE = "phone";

    private static final int REQUEST_UPDATE_SIGNATURE = 13;
    private static final int RESULT_UPDATE_SIGNATURE_SUCCESS = 103;
    private static final String SIGNATURE = "signature";

    private static final int PHOTO_REQUEST_CUT = 1001;

    public static EditProfileFragment newInstance() {
        Bundle args = new Bundle();
        EditProfileFragment fragment = new EditProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_edit_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        mTvTitle.setText(getResources().getText(R.string.module_mine_edit_info));
        mLoadingDialog = new LoadingDialog(_mActivity, false, null);
    }

    private void initData() {
        mLoadingDialog.showDialog();
        cutPhoto = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath(),
                System.currentTimeMillis() + JPEG_FILE_SUFFIX);

        iEditProfilePresenter.v2pGetUserEditDetails();
        mImageSelectEndListener = new OnSelectEndListener() {
            @Override
            public void onSelectEnd(List<AlbumImageBean> beanList) {
                if (beanList.size() == 1) {
                    mImageBean = beanList.get(0);
                    //跳转裁剪图片
                    startCropImageActivityForResult(Uri.fromFile(new File(mImageBean.getPath())), cutPhoto);

                } else {
                    ToastUtil.show("图像选择错误");
                }
            }
        };
    }

    private void startCropImageActivityForResult(Uri in, File out) {
        if (!out.exists()) {
            try {
                out.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(getContext(), CropImage.class);
        intent.putExtra(CropImage.IMAGE_PATH, in.getPath());
        intent.putExtra(CropImage.SCALE, false);
        intent.putExtra(CropImage.ASPECT_X, 3);
        intent.putExtra(CropImage.ASPECT_Y, 2);
        intent.putExtra(CropImage.CIRCLE_CROP, "true");
        intent.putExtra(CropImage.RETURN_DATA, false);
        intent.putExtra(CropImage.IMAGE_OUT_PATH, out.getPath());
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    @Override
    public void p2vInitProfileFragment(User user) {
        mUser = user;
        Log.d(TAG, "p2vInitProfileFragment " + user.getUserHeadImage());
        Glide.with(getContext()).asBitmap().load(user.getUserHeadImage()).into(mImPhoto);
        mTvEducation.setText(user.getEducation());
        mTvIndustry.setText(user.getOccupationValue());
        mTvName.setText(user.getNickName());
        mTvSex.setText(user.getSex() == 0 ? "男" : "女"); // 0=男；1=女；2=其它
        mTvSignature.setText(user.getSignature());
        mTvTel.setText(user.getPhoneNumber());

        mLoadingDialog.dismissDialog();
    }

    @Override
    public void p2vGetUserEditDetailsError(String error) {
        mLoadingDialog.dismissDialog();
        ToastUtil.show("系统繁忙");
        pop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "requestCode " + requestCode + " resultCode " + resultCode);
        if (requestCode == REQUEST_OPEN_CAMERA && resultCode == RESULT_OK) {
            startCropImageActivityForResult(Uri.fromFile(tempPhoto), cutPhoto);
        } else if (requestCode == PHOTO_REQUEST_CUT && resultCode == RESULT_OK) {

            AlbumImageBean cutImage = new AlbumImageBean();
            cutImage.setPath(cutPhoto.getPath());
            mAlbumImageBeans.clear();
            mAlbumImageBeans.add(cutImage);
            iEditProfilePresenter.v2pPostImageUtil(mAlbumImageBeans);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mBsdPhoto = null;
        mNpvSex = null;
        mBsdSex = null;
        mNpvEducation = null;
        mBsdEducation = null;
        mNpvIndustry = null;
        mBsdIndustry = null;
        mLoadingDialog = null;
    }

    @OnClick({R2.id.iv_back, R2.id.ll_name, R2.id.ll_sex, R2.id.ll_tel, R2.id.ll_signature, R2.id.ll_education, R2.id.ll_industry, R2.id.iv_photo})
    public void onViewClicked(View v) {
        int id = v.getId();
        Log.d(TAG, "onclick " + v.getClass().toString());
        if (id == R.id.iv_back) {
            pop();
        } else if (id == R.id.iv_photo) {
            showPhotoBottomDialg();
        } else if (id == R.id.ll_name) {
            startForResult(EditNameFragment.newInstance(mTvName.getText().toString()), REQUEST_UPDATE_NAME);
        } else if (id == R.id.ll_sex) {
            showSexPicker();
        } else if (id == R.id.ll_tel) {
            startForResult(EditTelphoneFragment.newInstance(mTvTel.getText().toString()), REQUEST_UPDATE_PHONE);
        } else if (id == R.id.ll_signature) {
            startForResult(EditSignatureFragment.newInstance(mTvSignature.getText().toString()), REQUEST_UPDATE_SIGNATURE);
        } else if (id == R.id.ll_education) {
            showEducationPicker();
        } else if (id == R.id.ll_industry) {
            showIndustryPicker();
        } else {
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        Log.d(TAG, "requestCode " + requestCode + "resultCode" + resultCode);
        if (requestCode == REQUEST_UPDATE_NAME && resultCode == RESULT_UPDATE_NAME_SUCCESS) {
            Log.d(TAG, data.getString(NAME));
            mTvName.setText(data.getString(NAME));
        } else if (requestCode == REQUEST_UPDATE_PHONE && resultCode == RESULT_UPDATE_PHONE_SUCCESS) {
            //TODO phone number format
            mTvTel.setText(data.getString(PHONE));
        } else if (requestCode == REQUEST_UPDATE_SIGNATURE && resultCode == RESULT_UPDATE_SIGNATURE_SUCCESS) {
            mTvSignature.setText(data.getString(SIGNATURE));
        }
    }

    //显示拍照与相册
    private void showPhotoBottomDialg() {
        if (mBsdPhoto == null) {
            mBsdPhoto = new BottomSheetDialog(getContext());
            mBsdPhoto.setCancelable(true);//设置点击外部是否可以取消
            mBsdPhoto.setContentView(R.layout.module_mine_layout_bottom_photo);//设置对框框中的布局
            TextView tvTakePhoto = mBsdPhoto.findViewById(R.id.tv_take_photo);
            TextView tvGallery = mBsdPhoto.findViewById(R.id.tv_from_gallery);
            TextView tvCancel = mBsdPhoto.findViewById(R.id.tv_cancel);
            View.OnClickListener photoClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    if (id == R.id.tv_take_photo) {
                        //TODO take photo activity
                        p2vOpenSystemCamera();
                    } else if (id == R.id.tv_from_gallery) {
                        AlbumActivity.openSelf(getContext(), 1, mImageSelectEndListener);
                    } else if (id == R.id.tv_cancel) {
                        mBsdPhoto.dismiss();
                    }

                }
            };
            tvTakePhoto.setOnClickListener(photoClickListener);
            tvGallery.setOnClickListener(photoClickListener);
            tvCancel.setOnClickListener(photoClickListener);
        }
        mBsdPhoto.show();
    }

    //展示性别选择窗口
    private void showSexPicker() {
        if (mBsdSex == null) {
            mBsdSex = new BottomSheetDialog(getContext());
            mBsdSex.setCancelable(true);//设置点击外部是否可以取消
            mBsdSex.setContentView(R.layout.module_mine_layout_bottom_picker);//设置对框框中的布局
            mNpvSex = mBsdSex.findViewById(R.id.npv_picker);
            mNpvSex.setDisplayedValuesAndPickedIndex(getResources().getStringArray(R.array.module_mine_sex), 0, false);
            TextView tvTitleSex = mBsdSex.findViewById(R.id.tv_picker_title);
            tvTitleSex.setText(getResources().getText(R.string.module_mine_picker_title_sex));
            TextView tv_ok = mBsdSex.findViewById(R.id.tv_ok);
            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "update sex " + mNpvSex.getValue());
                    mLoadingDialog.showDialog();
                    iEditProfilePresenter.v2pUpdateSex(mNpvSex.getValue());
                    mBsdSex.dismiss();
                }
            });
            ImageView ivBack = mBsdSex.findViewById(R.id.iv_back);
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBsdSex.dismiss();
                }
            });

        }
        mBsdSex.show();
    }

    //显示学历选择窗口
    private void showEducationPicker() {
        if (mBsdEducation == null) {
            mBsdEducation = new BottomSheetDialog(getContext());
            mBsdEducation.setCancelable(true);//设置点击外部是否可以取消
            mBsdEducation.setContentView(R.layout.module_mine_layout_bottom_picker);//设置对框框中的布局
            mNpvEducation = mBsdEducation.findViewById(R.id.npv_picker);
            mNpvEducation.setDisplayedValuesAndPickedIndex(getResources().getStringArray(R.array.module_mine_education), 2, false);
            TextView tvTitleEdu = mBsdEducation.findViewById(R.id.tv_picker_title);
            tvTitleEdu.setText(getResources().getText(R.string.module_mine_picker_title_edu));
            TextView tv_ok = mBsdEducation.findViewById(R.id.tv_ok);
            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLoadingDialog.showDialog();
                    Log.d(TAG, "put and update education: " + mNpvEducation.getContentByCurrValue());
                    iEditProfilePresenter.v2pPutUpdateEducation(mNpvEducation.getContentByCurrValue());
                    mBsdEducation.dismiss();
                }
            });
            ImageView ivBack = mBsdEducation.findViewById(R.id.iv_back);
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBsdEducation.dismiss();
                }
            });
        }
        mBsdEducation.show();
    }

    //显示行业选择窗口
    private void showIndustryPicker() {
        if (mBsdIndustry == null) {
            mBsdIndustry = new BottomSheetDialog(getContext());
            mBsdIndustry.setCancelable(true);//设置点击外部是否可以取消
            mBsdIndustry.setContentView(R.layout.module_mine_layout_bottom_picker);//设置对框框中的布局
            mNpvIndustry = mBsdIndustry.findViewById(R.id.npv_picker);
            mNpvIndustry.setDisplayedValuesAndPickedIndex(getResources().getStringArray(R.array.module_mine_industry), 2, false);//设置数据
            TextView tvTitleIndustry = mBsdIndustry.findViewById(R.id.tv_picker_title);
            tvTitleIndustry.setText(getResources().getText(R.string.module_mine_picker_title_industry));//设置标题
            TextView tv_ok = mBsdIndustry.findViewById(R.id.tv_ok);
            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLoadingDialog.showDialog();
                    Log.d(TAG, "put and update industry: " + mNpvIndustry.getContentByCurrValue());
                    //上传更新行业
                    iEditProfilePresenter.v2pUpdateIndustry(mNpvIndustry.getContentByCurrValue());
                    mBsdIndustry.dismiss();
                }
            });
            ImageView ivBack = mBsdIndustry.findViewById(R.id.iv_back);
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBsdIndustry.dismiss();
                }
            });
        }
        mBsdIndustry.show();
    }

    @RequestPermission(request = 1, permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void p2vOpenSystemCamera() {
        tempPhoto = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath(),
                System.currentTimeMillis() + JPEG_FILE_SUFFIX);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(getContext(), "com.huihu", tempPhoto);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempPhoto));
        }
        startActivityForResult(intent, REQUEST_OPEN_CAMERA);
    }

    @Override
    public void p2vPutUpdateHeadImageSuccess() {
        mImPhoto.setImageBitmap(BitmapFactory.decodeFile(cutPhoto.getPath()));
        mBsdPhoto.dismiss();
        mLoadingDialog.dismissDialog();
    }

    @Override
    public void p2vPutUpdateHeadImageError(String error) {
        ToastUtil.show(error);
        if (mCropedBitmap != null) {
            mCropedBitmap.recycle();
            mCropedBitmap = null;
        }
    }

    @Override
    public void p2vUpdateSexError(String error) {
        mBsdSex.dismiss();
        mLoadingDialog.dismissDialog();
        ToastUtil.show(error);
    }

    @Override
    public void p2vUpdateSexSuccess() {
        mTvSex.setText(mNpvSex.getContentByCurrValue());
        mBsdSex.dismiss();
        mLoadingDialog.dismissDialog();
    }

    @Override
    public void p2vUpdateIndustryError(String error) {
        mBsdIndustry.dismiss();
        mLoadingDialog.dismissDialog();
        ToastUtil.show(error);
    }

    @Override
    public void p2vUpdateIndustrySuccess() {
        mTvIndustry.setText(mNpvIndustry.getContentByCurrValue());
        mBsdIndustry.dismiss();
        mLoadingDialog.dismissDialog();
    }

    @Override
    public void p2vPutUpdateEducationSuccess() {
        mTvEducation.setText(mNpvEducation.getContentByCurrValue());
        mBsdEducation.dismiss();
        mLoadingDialog.dismissDialog();
    }

    @Override
    public void p2vPutUpdateEducationError(String error) {
        mBsdEducation.dismiss();
        mLoadingDialog.dismissDialog();
        ToastUtil.show(error);
    }


    @Override
    public void p2vPostImageUtilFail(String error) {
        if (mCropedBitmap != null) {
            mCropedBitmap.recycle();
            mCropedBitmap = null;
        }
        mLoadingDialog.dismissDialog();
        mBsdPhoto.dismiss();
        ToastUtil.show(error);

    }

    @Override
    public void p2vPostImageUtilSuccess() {
        ToastUtil.show("上传成功");
    }

}
