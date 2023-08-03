package com.example.sciverse.AllModules

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.sciverse.R
import com.example.sciverse.databinding.ActivityReverseComplementBinding
import com.example.sciverse.sshTask
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.Exception

class ReverseComplementActivity : AppCompatActivity() {
    lateinit var binding: ActivityReverseComplementBinding
    var JobId: String = System.currentTimeMillis().toString() //"1234"
    val JobName: String = "Reverse_Complement"
    val sshTask2 = sshTask()

    var host: String? =
        "111.91.225.19"            //out: 111.91.225.19 port: 22   #iit: 10.209.96.201
    var username: String? = "sciverse"
    var password: String? = "Access@App"
    var port: Int? = 22
    var filename: String? = JobId
    var command1: String? = "sh /home/sciverse/Main.sh $filename $JobName"


    @OptIn(DelicateCoroutinesApi::class)
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReverseComplementBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.buttonUpload.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "text/plain"
            startActivityForResult(intent, FILE_PICK_REQUEST_CODE)
        }

        binding.buttonSubmit.setOnClickListener {

            GlobalScope.launch {
                sshTask2.ExecuteCommand(host!!,username!!,password!!,command1!!)
            }

            val toast2 =
                Toast.makeText(applicationContext, "Your Job ID is $JobId", Toast.LENGTH_LONG)
            toast2.show()

        }

        binding.buttonDownload.setOnClickListener {
            GlobalScope.launch {
                sshTask2.DownloadFileViaSSH(host!!,username!!,password!!,"$JobId.txt")
            }

            val toast = Toast.makeText(
                applicationContext,
                "File Downloaded Successfully, check downloads folder!",
                Toast.LENGTH_SHORT
            )
            toast.show()

            val command2 = "mv /home/sciverse/$JobId /home/sciverse/JOBS/$JobId/"

            GlobalScope.launch {
                sshTask2.ExecuteCommand(host!!,username!!,password!!,command2)
            }

        }

        ActivityCompat.requestPermissions(
            this, arrayOf(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.MANAGE_EXTERNAL_STORAGE
            ),
            PackageManager.PERMISSION_GRANTED
        )
        binding.watchvideo.setOnClickListener {
            showBottomSheet()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FILE_PICK_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val fileUri = data.data // Get the URI of the selected file
            if (fileUri != null) { // Add null check here

                val filePath = getFileFromContentUri(fileUri)
                Log.d("filepath", filePath?.path.toString())

                // Get the file path from URI
                if (filePath != null) { // Add null check for file path
                    GlobalScope.launch {
                        sshTask2.uploadFileViaSSH(
                            host!!,
                            username!!,
                            password!!,
                            filePath
                        )
                    }
                    val toast = Toast.makeText(
                        applicationContext,
                        "File Uploaded Successfully",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                } else {
                    // Handle null file path case
                    Log.e(ContentValues.TAG, "Failed to get file path from URI: $fileUri")
                }
            }
        }
    }

    private fun getFileFromContentUri(contentUri: Uri): File? {
        var inputStream: InputStream? = null
        var outputStream: FileOutputStream? = null
        var finalFile: File? = null

        try {
            inputStream = contentResolver?.openInputStream(contentUri)
            val inputBytes = inputStream?.readBytes() ?: byteArrayOf()
            //JobId = "12345"
            finalFile = File(externalCacheDir, JobId)  //job sequencing

            if (finalFile.exists()) {
                finalFile.delete()
            }

            outputStream = FileOutputStream(finalFile.path)
            outputStream.write(inputBytes)

        } catch (e: Exception) {
            Log.d("UploadError", "on creating file: $e")
        } finally {
            inputStream?.close()
            outputStream?.close()
        }

        return finalFile

    }

    private fun showBottomSheet() {
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        val bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDailogTheme)
        bottomSheetDialog.setContentView(dialogView)

        val webView: WebView = dialogView.findViewById(R.id.webView) // Find the WebView inside the dialogView
        val video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/tUesv5u5bvA\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"
        webView.loadData(video, "text/html", "utf-8")
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()

        bottomSheetDialog.show()
    }

    companion object {
        private const val FILE_PICK_REQUEST_CODE = 1
    }
}