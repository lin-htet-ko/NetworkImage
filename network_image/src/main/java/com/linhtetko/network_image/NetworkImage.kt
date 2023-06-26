package com.linhtetko.network_image

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.net.Uri
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.bumptech.glide.Glide

class NetworkImage constructor(context: Context, attrs: AttributeSet?) :
    AppCompatImageView(context, attrs) {

    private val imgPath = Path()
    private val strokePath = Path()
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    //Attributes
    var radius = 0f
        internal set
    var strokeWidth = 0f
        internal set
    var strokeColor: Int = 0
        internal set
    var url: String? = null
        set(value) {
            field = value
            load()
        }
    var defaultImg: Int = 0
        internal set
    var placeholder: Int? = null
        internal set
    var error: Int? = null
        internal set
    var isCircular: Boolean = false
        internal set
    var isAnimate: Boolean = true
        internal set
    var mDuration: Long = 1000
        internal set


    init {

        context.withStyledAttributes(attrs, R.styleable.NetworkImage) {
            url = getString(R.styleable.NetworkImage_url)
            defaultImg =
                getResourceId(androidx.appcompat.R.styleable.AppCompatImageView_srcCompat, 0)
            radius = getDimension(R.styleable.NetworkImage_radius, 0f)
            strokeWidth = getDimension(R.styleable.NetworkImage_strokeWidth, 0f)
            strokeColor = getResourceId(R.styleable.NetworkImage_strokeColor, 0)
            placeholder = getResourceId(R.styleable.NetworkImage_placeholder, 0)
            error = getResourceId(R.styleable.NetworkImage_error, 0)
            isCircular = getBoolean(R.styleable.NetworkImage_isCircular, false)
            isAnimate = getBoolean(R.styleable.NetworkImage_isAnimate, true)
            mDuration = getInt(R.styleable.NetworkImage_animationDuration, 1000).toLong()

            if (!url.isNullOrEmpty()) {
                loadNetworkImage(url!!, placeholder, error)
            } else if (defaultImg != 0) {
                loadImage(defaultImg)
            }

        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {

        if (isCircular)
            radius = maxOf(width.toFloat(), height.toFloat()) / 2

        val rect = RectF(0f, 0f, width.toFloat(), height.toFloat())
        val strokeRect =
            RectF(
                ((strokeWidth / 2)),
                ((strokeWidth / 2)),
                width.toFloat() - ((strokeWidth / 2)),
                height.toFloat() - ((strokeWidth / 2))
            )

        strokePaint.strokeWidth = strokeWidth
        strokePaint.color =
            ContextCompat.getColor(context, if (strokeColor != 0) strokeColor else R.color.black)
        strokePaint.style = Paint.Style.STROKE
        strokePaint.isAntiAlias = true

        imgPath.addRoundRect(
            rect,
            radius,
            radius,
            Path.Direction.CCW
        )
        if (isCircular)
            strokePath.addRoundRect(
                strokeRect,
                radius - (strokeWidth / 2),
                radius - (strokeWidth / 2),
                Path.Direction.CCW
            )
        else
            strokePath.addRoundRect(
                strokeRect,
                0f,
                0f,
                Path.Direction.CCW
            )


        canvas?.clipPath(imgPath)
        super.onDraw(canvas)
        canvas?.drawPath(strokePath, strokePaint)


    }

    private fun loadImage(uri: Any) {
        when (uri) {
            is String -> {
                Glide.with(context).load(uri).into(this)
            }

            is Int -> {
                setImageResource(uri)
            }

            is Uri -> {
                setImageURI(uri)
            }
        }

    }

    private fun loadNetworkImage(
        url: String,
        @DrawableRes placeholder: Int?,
        @DrawableRes error: Int?
    ) {
        var img = Glide.with(context).load(url)

        //For Placeholder Image
        if (placeholder != null && placeholder != 0) {
            img = img.placeholder(placeholder)
        }

        //For Error Image
        if (error != null && placeholder != 0) {
            img = img.error(error)
        }
        img.into(this)

    }

    internal fun load() {
        if (!url.isNullOrEmpty()) {
            loadNetworkImage(url!!, placeholder, error)
        } else if (defaultImg != 0) {
            loadImage(defaultImg)
        }
        if (isAnimate)
            FadeIn(this, mDuration)
    }
}