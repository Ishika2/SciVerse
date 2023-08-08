package com.example.sciverse

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.util.Properties
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart
import javax.activation.DataHandler
import javax.activation.DataSource
import javax.activation.FileDataSource
class SendMailwithFile() {
    fun sendEmail(subject: String, msgbody: String, filePath: String?) {
        GlobalScope.launch(Dispatchers.IO){
            val username = "sciverse.iit@gmail.com"
            val password = "xeovxhuolwiqxsbq"

            val props = Properties()
            props["mail.smtp.auth"] = "true"
            props["mail.smtp.starttls.enable"] = "true"
            props["mail.smtp.host"] = "smtp.gmail.com"
            props["mail.smtp.port"] = "587"

            val session: Session = Session.getInstance(props,
                object : javax.mail.Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(username, password)
                    }
                })

            try {
                val message = MimeMessage(session)
                message.setFrom(InternetAddress(username))
                message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(FirebaseAuth.getInstance().currentUser?.email)
                )
                message.subject = subject
                message.setText(msgbody)

                if (filePath != null) {
                    val messageBodyPart = MimeBodyPart()
                    val multipart = MimeMultipart()

                    val source: DataSource = FileDataSource(filePath)
                    messageBodyPart.dataHandler = DataHandler(source)
                    messageBodyPart.fileName = File(filePath).name
                    multipart.addBodyPart(messageBodyPart)

                    message.setContent(multipart)
                }

                Transport.send(message)

                println("Done")

            } catch (e: MessagingException) {
                throw RuntimeException(e)
            }
        }
    }
}