package com.example.sciverse.AllModules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sciverse.R
import com.example.sciverse.databinding.ActivityMolalityBinding
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

class MolalityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMolalityBinding

    var JobId: String = System.currentTimeMillis().toString()
    val JobName: String = "Molality"
    val sshTask2 = sshTask()

    var host: String? = "111.91.225.19"

    var username: String? = "sciverse"
    var password: String? = "Access@App"
    var filename: String? = JobId
    var command: String? = "ls"
    var command1: String? = "sh /home/sciverse/Main.sh $filename $JobName"
    var port: Int? = 22

    val Solvent: EditText by lazy { findViewById(R.id.editVol1) }
    val Solute: EditText by lazy { findViewById(R.id.editConc) }
    val MolWt: EditText by lazy { findViewById(R.id.editVol2) }

    val result: TextView by lazy { findViewById(R.id.textResult) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMolalityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SubmitButton.setOnClickListener {

            GlobalScope.launch {
                TextToFile(
                    Solvent.text.toString(),
                    Solute.text.toString(),
                    MolWt.text.toString()
                )
            }

            val toast1 = Toast.makeText(
                applicationContext,
                "Response Submitted Successfully",
                Toast.LENGTH_SHORT
            )
            toast1.show()
            val toast2 =
                Toast.makeText(applicationContext, "Your Job ID is $JobId", Toast.LENGTH_LONG)
            toast2.show()

        }
        binding.watchvideo.setOnClickListener {
            showBottomSheet()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun TextToFile(Solvent: String, Solute: String, MolWt: String) {
        val file = File(externalCacheDir, JobId)
        file.writeText(Solvent)
        file.appendText("\n")
        file.appendText(Solute)
        file.appendText("\n")
        file.appendText(MolWt)

        GlobalScope.launch {
            sshTask2.uploadFileViaSSH(
                host!!,
                username!!,
                password!!,
                file
            )
            ResultViaSSH(host!!, username!!, password!!, command1!!, result)
        }
    }

    suspend fun ResultViaSSH(
        host: String,
        user: String,
        password: String,
        command1: String,
        result: TextView
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

            val res = jsonOutput.getJSONObject("0")

            withContext(Dispatchers.Main)
            {
                result.text = res.getDouble("Molality").toString()
            }

            channel2.disconnect()
            session.disconnect()
        }
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

}