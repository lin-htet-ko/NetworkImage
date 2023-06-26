package com.linhtetko.network_image

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

class NetworkImageBuilder(
    private val imageView: NetworkImage
) {

    constructor(
        view: NetworkImage,
        url: String? = null,
        resImage: Int = 0,
        placeholderImage: Int = 0,
        errorImage: Int = 0,
        radius: Float = 0f,
        strokeWidth: Float = 0f,
        strokeColor: Int = 0,
        isCircular: Boolean = false,
        isAnimate: Boolean = true
    ) : this(view) {
        imageView.url = url
        imageView.radius = radius
        imageView.strokeWidth = strokeWidth
        imageView.defaultImg = resImage
        imageView.placeholder = placeholderImage
        imageView.error = errorImage
        imageView.strokeColor = strokeColor
        imageView.isCircular = isCircular
        imageView.isAnimate = isAnimate

        build()
    }

    fun setRadius(radius: Float): NetworkImageBuilder {
        imageView.radius = radius
        return this
    }

    fun setStrokeWith(strokeWidth: Float): NetworkImageBuilder {
        imageView.strokeWidth = strokeWidth
        return this
    }

    fun setNetworkUrl(url: String): NetworkImageBuilder {
        imageView.url = url
        return this
    }

    fun setImageResource(@DrawableRes resId: Int): NetworkImageBuilder {
        imageView.defaultImg = resId
        return this
    }

    fun setPlaceholderImage(@DrawableRes resId: Int): NetworkImageBuilder {
        imageView.placeholder = resId
        return this
    }

    fun setErrorImage(@DrawableRes resId: Int): NetworkImageBuilder {
        imageView.error = resId
        return this
    }

    fun setStrokeColor(@ColorRes color: Int): NetworkImageBuilder {
        imageView.strokeColor = color
        return this
    }

    fun setIsCircle(status: Boolean): NetworkImageBuilder {
        imageView.isCircular = status
        return this
    }

    fun setIsAnimate(status: Boolean): NetworkImageBuilder{
        imageView.isAnimate = status
        return this
    }

    fun build(): NetworkImage {
        imageView.load()
        return imageView
    }
}