package tech.thdev.app.view.custom

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import tech.thdev.app.R

/**
 * Created by record-tae on 11/5/17.
 */
class GlideImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    fun loadImage(
        url: String?,
        @DrawableRes loadingImageRes: Int = R.drawable.ic_bubble_chart_white_50dp
    ) {
        Glide.with(this)
            .load(url)
            .apply(RequestOptions.placeholderOf(loadingImageRes).centerCrop())
            .into(this)
    }
}