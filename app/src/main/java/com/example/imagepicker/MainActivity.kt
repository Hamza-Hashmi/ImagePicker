package com.example.imagepicker

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var imageView:ImageView
    private lateinit var photoPickButton:MaterialButton
    var uri :Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image_view)
        photoPickButton = findViewById(R.id.image_picker)

        photoPickButton.setOnClickListener {
            pickPhoto()
        }
    }


    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
         if (result.resultCode == Activity.RESULT_OK){
             uri = result?.data?.data

             uri?.let {
                 imageView.setImageURI(uri)
             }?:run {
                 Toast.makeText(this@MainActivity, "Uri null", Toast.LENGTH_SHORT).show()
             }

         }
    }

    private fun pickPhoto() {
        val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
        resultLauncher.launch(intent)
    }
}