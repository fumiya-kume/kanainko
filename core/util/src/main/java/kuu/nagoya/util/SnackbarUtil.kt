package kuu.nagoya.util

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(
    text: String,
    @BaseTransientBottomBar.Duration length: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(
        requireActivity().findViewById(android.R.id.content),
        text,
        length
    ).show()
}
