package com.mazzeom.app.armstrong.libs.utils

import android.content.Context
import android.util.DisplayMetrics

class Dimension {
	companion object {
		fun dpToPx(dp: Float, context: Context): Int {
			return (dp * (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)).toInt()
		}
	}
}