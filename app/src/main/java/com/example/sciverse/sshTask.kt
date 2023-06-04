package com.example.sciverse

import android.os.Environment
import android.util.Log
import com.jcraft.jsch.ChannelExec
import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

open class sshTask {
    suspend fun executeSSHCommand(
        host: String,
        user: String,
        password: String,
        command: String,
        port: Int
    ): String {
        return withContext(Dispatchers.IO) {
            val jsch = JSch()
            val session: Session = jsch.getSession(user, host, port)
            session.setPassword(password)
            session.setConfig("StrictHostKeyChecking", "no")
            session.setConfig("PreferredAuthentications", "password")

            session.connect()

            val channel = session.openChannel("exec") as ChannelExec
            channel.setCommand(command)

            val inputStream = channel.inputStream
            val errorStream = channel.errStream
            channel.connect()

            val output = inputStream.bufferedReader().use { it.readText() }
            val error = errorStream.bufferedReader().use { it.readText() }

            Log.d("Output", output)

            channel.disconnect()
            session.disconnect()

            if (output.isNotEmpty()) {
                output
            } else {
                error
            }
        }
    }

    suspend fun uploadFileViaSSH(
        host: String,
        user: String,
        password: String,
        filePath: File
    ) {
        withContext(Dispatchers.IO) {
            val jsch = JSch()
            val session: Session = jsch.getSession(user, host, 22)
            session.setPassword(password)
            session.setConfig("StrictHostKeyChecking", "no")
            session.setConfig("PreferredAuthentications", "password")
            session.connect()

            val channel1 = session.openChannel("sftp") as ChannelSftp

            val fis = filePath.inputStream()

            channel1.connect()
            channel1.put(fis, filePath.name)
            channel1.disconnect()
            session.disconnect()
        }
    }

    suspend fun DownloadFileViaSSH(
        host: String,
        user: String,
        password: String,
        fileName: String
    ) {
        withContext(Dispatchers.IO) {
            val jsch = JSch()
            val session: Session = jsch.getSession(user, host, 22)
            session.setPassword(password)
            session.setConfig("StrictHostKeyChecking", "no")
            session.setConfig("PreferredAuthentications", "password")
            session.connect()

            val channel2 = session.openChannel("sftp") as ChannelSftp
            channel2.connect()

            val inputStream: InputStream? = null
            var outputStream: FileOutputStream? = null

            try {

                val outputFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),fileName)
                outputStream = FileOutputStream(outputFile)

                val inputFile = channel2.get("/home/sciverse/$fileName", outputStream)

            }
            catch (e: Exception) {
                Log.d("DownloadFile","Error downloading file: $e")
            }
            finally {
                inputStream?.close()
                outputStream?.close()
            }

            channel2.disconnect()
            session.disconnect()
        }
    }

    suspend fun ExecuteCommand(
        host: String,
        user: String,
        password: String,
        command: String
    ) {
        withContext(Dispatchers.IO) {
            val jsch = JSch()
            val session: Session = jsch.getSession(user, host, 22)
            session.setPassword(password)
            session.setConfig("StrictHostKeyChecking", "no")
            session.setConfig("PreferredAuthentications", "password")
            session.connect()


            val channel1 = session.openChannel("exec") as ChannelExec
            channel1.setCommand(command)

            channel1.connect()

            channel1.disconnect()
            session.disconnect()
        }
    }
}