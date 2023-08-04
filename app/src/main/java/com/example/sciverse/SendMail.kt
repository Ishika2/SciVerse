package com.example.sciverse

import android.os.AsyncTask
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class SendMail(private val subject: String, private val message: String) : AsyncTask<Void?, Void?, Void?>() {
    private val from = "sciverse.iit@gmail.com"
    private val password = "access@app"
    private val to = FirebaseAuth.getInstance().currentUser?.email

    override fun doInBackground(vararg params: Void?): Void? {
        val properties = Properties()
        properties["mail.smtp.host"] = "smtp.gmail.com"
        properties["mail.smtp.port"] = "587"
        properties["mail.smtp.auth"] = "true"
        properties["mail.smtp.starttls.enable"] = "true"

        val session: Session = Session.getInstance(properties,
            object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(from, password)
                }
            })

        try {
            val mimeMessage = MimeMessage(session)
            mimeMessage.setFrom(InternetAddress(from))
            mimeMessage.addRecipient(Message.RecipientType.TO, InternetAddress(to))
            mimeMessage.subject = subject
            mimeMessage.setText(message)

            Transport.send(mimeMessage)
        } catch (e: MessagingException) {
            e.printStackTrace()
        }

        return null
    }
}