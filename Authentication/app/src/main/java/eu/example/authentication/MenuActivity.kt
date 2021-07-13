package eu.example.authentication

import android.app.Notification
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso

class MenuActivity : AppCompatActivity() {
    //UI components

    private lateinit var progressBar: ProgressBar

    private lateinit var buttonUploadPhoto: Button
    private lateinit var imageViewProfilePhoto: ImageView
    private lateinit var textViewWelcome: TextView
    private lateinit var buttonSignOut:Button
    //Firebase components
    private lateinit var firebaseAut:FirebaseAuth
    private lateinit var currentUser: FirebaseUser

    private lateinit var storageReferenceFirebase:StorageReference

    //other componentes
    private  var UriImage:Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        getUIComponents()
        getFirebaseComponents()


        val email:String  = currentUser?.email ?: "User by phone"
        val welcome = String.format( resources.getString(R.string.message_welcome), email )
        textViewWelcome.text = welcome


        imageViewProfilePhoto.setOnClickListener{
            fileChooser()
        }

        buttonUploadPhoto.setOnClickListener {
            uploadPhotoInFirebase()
        }

        buttonSignOut.setOnClickListener{
            firebaseAut.signOut()
            val intent = Intent(this.applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun uploadPhotoInFirebase() {
        this.buttonUploadPhoto.isClickable = false
        progressBar.visibility = ProgressBar.VISIBLE
        val reference:StorageReference = this.storageReferenceFirebase.child("profilePhotos")

        reference.child((System.currentTimeMillis() /1000).toString() + ".jpg" ).putFile(this.UriImage!!)
            .addOnSuccessListener {
                progressBar.visibility = ProgressBar.INVISIBLE
                Toast.makeText(this.applicationContext, "Upload Success", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                progressBar.visibility = ProgressBar.INVISIBLE
                Toast.makeText(this.applicationContext, "Upload with Failure: Sorry there was a problem tray " +
                        "to submit it again", Toast.LENGTH_SHORT).show()
            }
            .addOnProgressListener {
                val progress: Double = ((it.bytesTransferred*100.0) / it.totalByteCount)
                this.progressBar.progress = progress.toInt()
            }
    }

    private fun getFirebaseComponents() {
        firebaseAut = Firebase.auth
        this.currentUser = firebaseAut.currentUser as FirebaseUser
        this.storageReferenceFirebase = Firebase.storage.getReference(this.currentUser.email!!)

    }

    private fun getUIComponents() {
        textViewWelcome = findViewById(R.id.textViewWelcome)
        imageViewProfilePhoto = findViewById(R.id.imageViewProfilePhoto)
        buttonUploadPhoto = findViewById(R.id.buttonUploadPhoto)
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = ProgressBar.INVISIBLE

        buttonSignOut = findViewById(R.id.buttonSignOut)
    }

    fun fileChooser():Unit{
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, MenuActivity.CODE_CHOSE_PHOTO  )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CODE_CHOSE_PHOTO && resultCode == RESULT_OK){
            this.UriImage = data?.data

            if(UriImage != null ){
                Picasso.get().load(this.UriImage).into(this.imageViewProfilePhoto)

            }
        }
    }

    companion object{
        const val CODE_CHOSE_PHOTO = 1
    }

}
