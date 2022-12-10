package com.example.tflearning

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.tflearning.R
import com.example.tflearning.ml.MobilenetV110224Quant
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.net.URI

class MainActivity : AppCompatActivity() {

    lateinit var bitmap: Bitmap
    lateinit var imgView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgView = findViewById(R.id.imageView)
        var selectButton :Button = findViewById(R.id.selectButton)
        var predictButton : Button = findViewById(R.id.predictButton)
        var textView : TextView = findViewById(R.id.hintText)
        var inputString = application.assets.open("labels.txt").bufferedReader().use { it.readText()}
        var labelList = inputString.split("\n")

        selectButton.setOnClickListener(View.OnClickListener{
            var intent : Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type ="image/"

            startActivityForResult(intent,100)

        })
        predictButton.setOnClickListener(View.OnClickListener {
            var resize : Bitmap = Bitmap.createScaledBitmap(bitmap,224,224,true)

            val model = MobilenetV110224Quant.newInstance(this)

// Creates inputs for reference.
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)

            var tbuffer = TensorImage.fromBitmap(resize)

            var  byteBuffer = tbuffer.buffer
            inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            var max = getMax(outputFeature0.floatArray)
            textView.setText(labelList[max])

// Releases model resources if no longer used.
            model.close()
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imgView.setImageURI(data?.data)

        var uri : Uri? = data?.data

        bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,uri)

    }

    fun getMax(arr : FloatArray) : Int{
        var index = 0
        var min = 0.0f
        for (i in 0..1000)
        {
            if(arr[i]>min)
            {
                index =i
                min = arr[i]
            }
        }
        return index
    }
}