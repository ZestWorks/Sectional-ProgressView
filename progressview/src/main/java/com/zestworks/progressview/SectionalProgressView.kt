package com.zestworks.progressview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.*


class SectionalProgressView : ConstraintLayout {

    private var segmentCount: Int = 0
        set(value) {
            require(value != 0) { "Segment count can't be 0" }
            field = value
            resetProgressViews()
        }

    @ColorInt
    private var backgroundTint: Int = Color.parseColor("#000000")

    @ColorInt
    private var progressTint: Int = Color.parseColor("#d32f2f")

    @ColorInt
    private var progressCompletedTint: Int = Color.parseColor("#219421")

    private var horSpacing: Int = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.SectionalProgressView,
            defStyleAttr,
            R.style.ProgressViewStyle
        )
        backgroundTint = typedArray.getColor(
            R.styleable.SectionalProgressView_progressBackgroundTint,
            backgroundTint
        )
        progressTint = typedArray.getColor(
            R.styleable.SectionalProgressView_progressTint,
            progressTint
        )
        progressCompletedTint = typedArray.getColor(
            R.styleable.SectionalProgressView_progressCompletedTint,
            progressCompletedTint
        )

        val marginStart =
            typedArray.getDimension(
                R.styleable.SectionalProgressView_android_layout_marginStart,
                0f
            ).toInt()

        horSpacing = if (marginStart != 0) {
            marginStart
        } else {
            typedArray.getDimension(R.styleable.SectionalProgressView_android_layout_margin, 0f)
                .toInt()
        }
        if (typedArray.hasValue(R.styleable.SectionalProgressView_segmentCount)) {
            segmentCount = typedArray.getInteger(R.styleable.SectionalProgressView_segmentCount, 0)
        } else {
            Log.e("SectionalProgressView", "Section count should be set for this view to render, you can set this either in the XML using app:segmentCount=\"3\" or by calling the method setSegmentsCount")
        }
        typedArray.recycle()
    }

    fun setProgress(segmentIndex: Int, progressPercentage: Int) {
        require(segmentIndex != 0) { "Segment is 1 based index" }
        val progressIndicator = getChildAt(segmentIndex - 1) as ProgressIndicator
        progressIndicator.setProgress(progressPercentage)
    }

    fun setBackgroundTint(@ColorInt color: Int) {
        this.backgroundTint = color
        invalidate()
    }

    fun setProgressTint(@ColorInt color: Int) {
        this.progressTint = color
        invalidate()
    }

    fun setProgressCompletedTint(@ColorInt color: Int) {
        this.progressCompletedTint = color
        invalidate()
    }

    fun setSegmentsCount(count: Int) {
        this.segmentCount = count
    }

    private fun resetProgressViews() {
        val viewIds = IntArray(segmentCount)
        repeat(segmentCount) { index ->
            addView(
                ProgressIndicator(context).apply {
                    id = View.generateViewId()
                    viewIds[index] = id
                }
            )
        }
        applyConstraints(viewIds)
    }

    private fun applyConstraints(viewIds: IntArray) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this)
        for (viewId in viewIds) {
            constraintSet.constrainWidth(viewId, MATCH_CONSTRAINT)
            constraintSet.constrainHeight(viewId, MATCH_CONSTRAINT)
            constraintSet.connect(viewId, TOP, PARENT_ID, TOP)
            constraintSet.connect(viewId, BOTTOM, PARENT_ID, BOTTOM)
        }
        if (segmentCount == 1) {
            constraintSet.connect(viewIds[0], START, PARENT_ID, START)
            constraintSet.connect(viewIds[0], END, PARENT_ID, END)
        } else {
            createChains(constraintSet, viewIds)
        }
        constraintSet.applyTo(this)
    }

    private fun createChains(constraintSet: ConstraintSet, viewIds: IntArray) {
        constraintSet.createHorizontalChainRtl(
            PARENT_ID, START, PARENT_ID, END, viewIds, null,
            CHAIN_SPREAD_INSIDE
        )
        for (index in 1 until segmentCount) {
            constraintSet.setMargin(viewIds[index], START, horSpacing)
        }
    }

    internal inner class ProgressIndicator(context: Context) : View(context) {
        private val backgroundDrawable: Drawable by lazy {
            context.resources.getDrawable(R.drawable.progress_drawable, context.theme).apply {
                setTint(backgroundTint)
                alpha = 50
                setBounds(0, 0, width, height)
            }
        }

        private val foregroundDrawable: Drawable by lazy {
            context.resources.getDrawable(R.drawable.progress_drawable, context.theme)
        }

        private var animator = ValueAnimator.ofInt(0, 0)

        fun setProgress(progress: Int) {
            if (animator.isRunning) {
                animator.cancel()
            }
            val fromValue = foregroundDrawable.bounds.right
            val toValue = (progress * width) / 100
            animator = ValueAnimator.ofInt(fromValue, toValue)
            animator.addUpdateListener {
                val currentValue = it.animatedValue as Int
                val tint = if (currentValue != width) progressTint else progressCompletedTint
                foregroundDrawable.setTint(tint)
                foregroundDrawable.setBounds(0, 0, currentValue, height)
                invalidate()
            }
            animator.start()
        }

        override fun onDraw(canvas: Canvas) {
            backgroundDrawable.draw(canvas)
            foregroundDrawable.draw(canvas)
        }
    }
}