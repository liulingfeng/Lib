package com.llf.lib.mvp.cooking;

import com.llf.lib.mvp.BasePresenter;
import com.llf.lib.mvp.BaseView;

/**
 * Created by llf on 2016/8/2.
 */
public interface CookingContract {
    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter{

    }
}
