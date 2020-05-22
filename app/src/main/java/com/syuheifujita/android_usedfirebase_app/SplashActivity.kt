package com.syuheifujita.android_usedfirebase_app

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //fullscreen表示にする
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //フォントを変更
        //assetsにfontデータを格納
        val typeFace: Typeface = Typeface.createFromAsset(assets, "asset_font_carbonblock.ttf")
        tv_app_name.typeface = typeFace
    }
}
