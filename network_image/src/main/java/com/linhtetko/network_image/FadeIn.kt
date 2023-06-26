package com.linhtetko.network_image

import android.animation.ValueAnimator
import android.view.View

class FadeIn(
    private val mView: View,
    private val mDuration: Long = 1000,
) {

    private val mAnimator: ValueAnimator = ValueAnimator.ofFloat(0f, 1f)


    init {
        mAnimator.apply {
            mView.alpha = 0f

            this.duration = mDuration
            addUpdateListener { value ->
                mView.alpha = value.animatedValue as Float
            }
        }.start()
    }
}