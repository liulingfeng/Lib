package com.llf.lib.recycle;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;

/**
 * Created by llf on 2016/7/27.
 * 自定义item添加删除动画
 * http://www.myexception.cn/mobile/1979072.html
 * https://github.com/gabrielemariotti/RecyclerViewItemAnimators大神写的动画库
 */
public class MyItemAnimator extends RecyclerView.ItemAnimator {
    private ArrayList<RecyclerView.ViewHolder> mPendingAddHolders = new ArrayList<>();
    private ArrayList<RecyclerView.ViewHolder> mPendingRemoveHolders = new ArrayList<>();
    private ArrayList<RecyclerView.ViewHolder> mAddAnimtions = new ArrayList<>();
    private ArrayList<RecyclerView.ViewHolder> mRemoveAnimations = new ArrayList<>();
    private ArrayList<MoveInfo> mPendingMoveHolders = new ArrayList<>();
    private ArrayList<MoveInfo> mMoveAnimtions = new ArrayList<>();
    //当有动画需要执行时调用

    /**
     * 当我们去添加一个item的时候runPendingAnimations不一定立即执行
     */
    @Override
    public void runPendingAnimations() {
        boolean isRemove = !mPendingRemoveHolders.isEmpty();
        boolean isAdd = !mPendingAddHolders.isEmpty();
        boolean isMove = !mPendingMoveHolders.isEmpty();

        if(!isRemove && !isAdd&&!isMove) return;

        // first remove
        if(isRemove) {
            for(RecyclerView.ViewHolder holder : mPendingRemoveHolders) {
                animateRemoveImpl(holder);
            }
            mPendingRemoveHolders.clear();
        }

        // last add
        if(isAdd) {
            ArrayList<RecyclerView.ViewHolder> holders = new ArrayList<>();
            holders.addAll(mPendingAddHolders);
            mPendingAddHolders.clear();
            for(RecyclerView.ViewHolder holder : holders) {
                animateAddImpl(holder);
            }
            holders.clear();
        }

        if(isMove) {
            ArrayList<MoveInfo> infos = new ArrayList<>();
            infos.addAll(mPendingMoveHolders);
            mPendingMoveHolders.clear();
            for(MoveInfo info : infos) {
                animateMoveImpl(info);
            }
            infos.clear();
        }
    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        mPendingRemoveHolders.add(holder);
        return true;
    }

    /**
     * add时的动画
     * 返回值表示：runPendingAnimations是否可以在下一个时机去执行。所以当我们定制动画时，这个方法要返回true。
     *
     * @param holder
     * @return
     */
    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        //防止item闪动
        holder.itemView.setAlpha(0.f);
        mPendingAddHolders.add(holder);
        return true;
    }

    //item移动时的动画
    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        View view = holder.itemView;
        fromY += view.getTranslationY();
        int delta = toY - fromY;
        view.setTranslationY(-delta);
        MoveInfo info = new MoveInfo(holder, fromX, fromY, toX, toY);
        mPendingMoveHolders.add(info);
        return true;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        return false;
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {

    }

    @Override
    public void endAnimations() {

    }

    //返回当前是否有动画需要执行
    @Override
    public boolean isRunning() {
        return !(mPendingAddHolders.isEmpty()
                && mPendingRemoveHolders.isEmpty()
                && mAddAnimtions.isEmpty()
                && mRemoveAnimations.isEmpty());
    }

    private void animateAddImpl(final RecyclerView.ViewHolder holder) {
        mAddAnimtions.add(holder);
        final View item = holder.itemView;
        ObjectAnimator animator = ObjectAnimator.ofFloat(item, "alpha", 0.f, 1.f);
        animator.setDuration(1000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                //动画开始之前调用
                dispatchAddStarting(holder);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                item.setAlpha(1.f);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                dispatchAddFinished(holder);
                mAddAnimtions.remove(holder);
                if (!isRunning()) dispatchAnimationsFinished();
            }
        });
        animator.start();
    }

    // 执行移出动画
    private void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
        mRemoveAnimations.add(holder);
        final View item = holder.itemView;
        ObjectAnimator animator = ObjectAnimator.ofFloat(item, "alpha", 1.f, 0.f);
        animator.setDuration(1000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                dispatchRemoveStarting(holder);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mRemoveAnimations.remove(holder);
                item.setAlpha(1.f);
                dispatchRemoveFinished(holder);
                if (!isRunning()) dispatchAnimationsFinished();
            }
        });
        animator.start();
    }

    private void animateMoveImpl(final MoveInfo info) {
        mMoveAnimtions.remove(info);
        final View view = info.holder.itemView;
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,
                "translationY", view.getTranslationY(), 0);
        animator.setDuration(1000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                dispatchMoveStarting(info.holder);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                dispatchMoveFinished(info.holder);
                mMoveAnimtions.remove(info.holder);
                if(!isRunning()) dispatchAnimationsFinished();
            }
        });
        animator.start();
    }
}
