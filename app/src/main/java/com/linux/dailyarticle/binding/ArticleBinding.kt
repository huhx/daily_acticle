package com.linux.dailyarticle.binding

import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.text.TextUtils
import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView

@BindingAdapter("android:htmlText")
fun bindTextContent(view: MaterialTextView, textContent: String?) {
    if (TextUtils.isEmpty(textContent)) {
        view.text = ""
    } else {
        view.text = Html.fromHtml(removeDuplicate(textContent), FROM_HTML_MODE_LEGACY)
    }
}

fun removeDuplicate(textContent: String?): String {
    return textContent?.replace("<p> </p>", "")!!
}
