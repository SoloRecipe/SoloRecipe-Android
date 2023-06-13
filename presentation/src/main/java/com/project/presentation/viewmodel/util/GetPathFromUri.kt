package com.project.presentation.viewmodel.util

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File
import java.io.FileOutputStream

fun getFileFromUri(context: Context, uri: Uri): File? {
    val inputStream = context.contentResolver.openInputStream(uri)
    inputStream?.let {
        val fileName = getFileNameFromUri(context, uri)
        val file = File(context.cacheDir, fileName ?: "")
        FileOutputStream(file).use { outputStream ->
            val buffer = ByteArray(4 * 1024)
            var bytesRead: Int
            while (it.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }
            outputStream.flush()
        }
        inputStream.close()
        return file
    }
    return null
}

fun getFileNameFromUri(context: Context, uri: Uri): String? {
    var fileName: String? = null
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.let {
        if (it.moveToNext()) {
            val columnIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            fileName = it.getString(columnIndex)
        }
        cursor.close()
    }
    return fileName
}