package com.example.sciverse.AllModules

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.sciverse.R
import com.example.sciverse.SendMail
import com.example.sciverse.databinding.ActivityAtgcModuleBinding
import com.example.sciverse.sshTask
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jcraft.jsch.ChannelExec
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.Exception

class ATGC_Module : AppCompatActivity() {
    lateinit var binding: ActivityAtgcModuleBinding
    lateinit var dialog: BottomSheetDialog
    var JobId: String = System.currentTimeMillis().toString() //"1234"
    val JobName: String = "DNA"
    val sshTask2 = sshTask()

    var host: String? =
        "111.91.225.19"            //out: 111.91.225.19 port: 22   #iit: 10.209.96.201
    var username: String? = "sciverse"
    var password: String? = "Access@App"
    var filename: String? = JobId
    var command: String? = "ls"
    var command1: String? = "sh /home/sciverse/Main.sh $filename $JobName"
    var port: Int? = 22

    val NumA: TextView by lazy { findViewById(R.id.NumA) }
    val NumT: TextView by lazy { findViewById(R.id.NumT) }
    val NumG: TextView by lazy { findViewById(R.id.NumG) }
    val NumC: TextView by lazy { findViewById(R.id.NumC) }

    val FreqA: TextView by lazy { findViewById(R.id.FreqA) }
    val FreqT: TextView by lazy { findViewById(R.id.FreqT) }
    val FreqG: TextView by lazy { findViewById(R.id.FreqG) }
    val FreqC: TextView by lazy { findViewById(R.id.FreqC) }

    val error: TextView by lazy { findViewById(R.id.errorView) }

    private val textATGC: EditText by lazy { findViewById(R.id.editATGC) }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtgcModuleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val online = isOnline(this) // Assuming you are calling it inside an activity or a context-aware class
        binding.sendMail.setOnClickListener{
            if (online) {
                SendMail().sendEmail("Results of your ATGC calculations😊",
                    "Frequncy A :- ${FreqA.text}" + "\nFrequency T :- ${FreqT.text}" + "\nFrequency G :- ${FreqG.text}" + "\nFrequency C :- ${FreqC.text}")
            } else {
                Toast.makeText(this, "Please connect to the internet!", Toast.LENGTH_SHORT).show()
            }
        }

        showAlertDialog()

        binding.uploadFile.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "text/plain"
            startActivityForResult(intent, FILE_PICK_REQUEST_CODE)
        }

        binding.SubmitButtonFile.setOnClickListener {
            GlobalScope.launch {
                ResultViaSSH(
                    host!!, username!!, password!!, command1!!,
                    NumA,
                    NumT,
                    NumG,
                    NumC,
                    FreqA,
                    FreqT,
                    FreqG,
                    FreqC,
                    error
                )
                // do something with the result
            }
        }

//        binding.connect.setOnClickListener {
//            GlobalScope.launch {
//                sshTask2.executeSSHCommand(host!!, username!!, password!!, command!!, port!!)
//                // do something with the result
//            }
//            val toast =
//                Toast.makeText(applicationContext, "Connected to the Server", Toast.LENGTH_SHORT)
//            toast.show()
//        }

        binding.SubmitButton.setOnClickListener {
            TextToFile(textATGC.text.toString())
            val toast = Toast.makeText(
                applicationContext,
                "Response Submitted Successfully",
                Toast.LENGTH_SHORT
            )
            toast.show()
            val toast2 =
                Toast.makeText(applicationContext, "Your Job ID is $JobId", Toast.LENGTH_LONG)
            toast2.show()
        }

        ActivityCompat.requestPermissions(
            this, arrayOf(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            PackageManager.PERMISSION_GRANTED
        )
        binding.watchvideo.setOnClickListener {
            showBottomSheet()
        }
    }


    //Get the selected file's URI in onActivityResult method
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
                    val toast2 = Toast.makeText(
                        applicationContext,
                        "Your Job ID is $JobId",
                        Toast.LENGTH_SHORT
                    )
                    toast2.show()
                } else {
                    // Handle null file path case
                    Log.e(ContentValues.TAG, "Failed to get file path from URI: $fileUri")
                }
            }

        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun TextToFile(InputText: String) {
        val file = File(externalCacheDir, JobId)
        file.writeText(InputText)

        GlobalScope.launch {
            sshTask2.uploadFileViaSSH(
                host!!,
                username!!,
                password!!,
                file
            )

            ResultViaSSH(
                host!!, username!!, password!!, command1!!,
                NumA,
                NumT,
                NumG,
                NumC,
                FreqA,
                FreqT,
                FreqG,
                FreqC,
                error
            )
        }
    }

    suspend fun ResultViaSSH(
        host: String,
        user: String,
        password: String,
        command1: String,
        NumA: TextView,
        NumT: TextView,
        NumG: TextView,
        NumC: TextView,
        FreqA: TextView,
        FreqT: TextView,
        FreqG: TextView,
        FreqC: TextView,
        error: TextView
    ) {
        withContext(Dispatchers.IO) {
            val jsch = JSch()
            val session: Session = jsch.getSession(user, host, 22)
            session.setPassword(password)
            session.setConfig("StrictHostKeyChecking", "no")
            session.setConfig("PreferredAuthentications", "password")
            session.connect()

            val channel2 = session.openChannel("exec") as ChannelExec

            channel2.setCommand(command1)

            val inputStream2 = channel2.inputStream
            val errorStream2 = channel2.errStream
            channel2.connect()

            val output2 = inputStream2.bufferedReader().use { it.readText() }
            //val error2 = errorStream2.bufferedReader().use { it.readText() }

            Log.d("Output 2 ", output2)

            val jsonOutput = JSONObject(output2)

            val NumJSON = jsonOutput.getJSONObject("Numbers")

            val FreqJSON = jsonOutput.getJSONObject("Frequency")

            withContext(Dispatchers.Main)
            {

                val others = NumJSON.getInt("others")

                if (others > 0) {
                    error.text = "Invalid DNA Sequence"
                    NumA.text = "A"
                    NumT.text = "T"
                    NumG.text = "G"
                    NumC.text = "C"

                    FreqA.text = "A"
                    FreqT.text = "T"
                    FreqG.text = "G"
                    FreqC.text = "C"
                } else {
                    error.text = ""
                    NumA.text = NumJSON.getInt("A").toString()
                    NumT.text = NumJSON.getInt("T").toString()
                    NumG.text = NumJSON.getInt("G").toString()
                    NumC.text = NumJSON.getInt("C").toString()

                    FreqA.text = FreqJSON.getDouble("A").toString()
                    FreqT.text = FreqJSON.getDouble("T").toString()
                    FreqG.text = FreqJSON.getDouble("G").toString()
                    FreqC.text = FreqJSON.getDouble("C").toString()

                }

            }
            channel2.disconnect()
            session.disconnect()
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
    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    private fun showAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Welcome!")
        alertDialogBuilder.setMessage("This is the ATGC Module activity."
        + "\nATGC is an acronym for the four types of bases found in a DNA molecule: \nadenine (A), cytosine (C), guanine (G), and thymine (T). \nA DNA molecule consists of two strands wound around each other, with each strand held together by bonds between the bases. \nAdenine pairs with thymine, and cytosine pairs with guanine.")
        alertDialogBuilder.setNegativeButton("SKIP") { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }
        // Add a button with sample inputs
        alertDialogBuilder.setPositiveButton("Sample Inputs") { dialogInterface: DialogInterface, _: Int ->
            // Handle button click
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}