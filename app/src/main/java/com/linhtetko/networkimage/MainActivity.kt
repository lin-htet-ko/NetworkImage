package com.linhtetko.networkimage

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.linhtetko.network_image.NetworkImage
import com.linhtetko.network_image.NetworkImageBuilder
import com.linhtetko.networkimage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.img = "https://media-cdn.tripadvisor.com/media/photo-s/16/6c/90/4a/lrm-export-14379092528022.jpg"

        //        app:url="https://cdn.pixabay.com/photo/2016/05/03/22/35/pagoda-1370346_1280.jpg"

//        findViewById<NetworkImage>(R.id.ivImg).apply {
//            loadImage("https://cdn.pixabay.com/photo/2016/11/12/17/54/hand-1819357_1280.jpg")
//        }
        val img = binding.ivImg
        NetworkImageBuilder(img).apply {
//            setNetworkUrl("https://cdn.apollo.audio/one/media/63e0/d3bc/3de0/4c05/a326/bbc2/adele-grammys-2023-easy-on-me-award.jpg")
            setErrorImage(R.drawable.pixabay_burma_fishman)
            setStrokeWith(40f)
            setPlaceholderImage(R.drawable.pixabay_burma_tea)
            setRadius(100f)
            setIsCircle(true)
            setImageResource(R.drawable.pixabay_burma_woman)
            build()
        }

        val img1 = binding.ivImg1
        NetworkImageBuilder(
            img1,
            url = "https://i1.sndcdn.com/avatars-otulnqaphpqS98tn-SPPp4A-t500x500.jpg",
            strokeWidth = 40f,
            strokeColor = R.color.black,
            radius = 10f
        )

    }
}