package com.syuheifujita.android_usedfirebase_app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.syuheifujita.android_usedfirebase_app.R

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
//        val typeFace: Typeface = Typeface.createFromAsset(assets, "asset_font_carbonblock.ttf")
//        tv_app_name.typeface = typeFace

        //一定時間経過した後次の画面に移動
        //handlerを使う
        Handler().postDelayed({
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        },2000)
    }
}
