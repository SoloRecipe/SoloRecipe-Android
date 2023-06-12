package com.project.presentation.viewmodel.util

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log

fun getPathFromUri(context: Context, uri: Uri): String? {
    var filePath: String? = null
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.let {
        if (it.moveToNext()) {
            val columnIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            filePath = it.getString(columnIndex)
        }
        Log.d("filepathTest", "getPathFromUri: ${filePath}")
        cursor.close()
    }
    return "/storage/emulated/0/Pictures/" + filePath
}
