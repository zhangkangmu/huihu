package debug;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.module_home.R;
import com.huihu.module_home.home.view.fragment.HomeFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * create by wangjing on 2019/3/20 0020
 * description:
 */
public class DebugFragment extends SupportFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.module_notification_activity_debug, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (findFragment(HomeFragment.class) == null) {
            loadRootFragment(R.id.fl_content, new HomeFragment());
        }
    }
}
