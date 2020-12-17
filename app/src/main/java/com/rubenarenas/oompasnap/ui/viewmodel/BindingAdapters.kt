package com.rubenarenas.oompasnap.ui.viewmodel

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.rubenarenas.oompasnap.R
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["setImageUrl"])
fun ImageView.bindImageUrl(url: String?) {
    if (url != null && url.isNotBlank()) {
        Picasso.get()
            .load(url)
            .into(this)
    }
}

@BindingAdapter("firstName", "secondName")
fun TextView.setCompleteName(firstName: String?, secondName: String?) {
    text = "$firstName  $secondName"
}

@BindingAdapter("age")
fun TextView.setAge(age: Int) {
    text = "$age years"
}


@BindingAdapter("height")
fun TextView.setHeight(height: Int) {
    text = "$height cm"
}

@BindingAdapter("genderIcon")
fun setImageResource(image: ImageView, gender: String?) {
    if (gender.equals("M")) image.setImageResource(R.drawable.ic_gender_male_white_48dp)
    if (gender.equals("F")) image.setImageResource(R.drawable.ic_gender_female_white_48dp)
}
