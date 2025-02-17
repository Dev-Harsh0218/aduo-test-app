package com.example.testingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.adsdk.AdsSDKClient
import com.example.testingapp.ui.theme.TestingAppTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AdsSDKClient.initialize(
            context = applicationContext,
            appName = "DemoApp2",
            appApkKey = "demoTestApp2",
            packageName = "com.demo.test2",
            appVersion = "1.1.2"
        )

        AdsSDKClient.triggerBannerAd("demo_banner")
        enableEdgeToEdge()
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    AdsSDKClient.triggerInterstitialAd("demo_interstitial")
                }) {
                    Text("Show Interstitial")
                }

                Button(onClick = {
                    AdsSDKClient.triggerPopupAd("demo_popup")
                }) {
                    Text("Show Popup")
                }

                AdsSDKClient.BannerAdContainer(
                    containerId = "demo_banner",
                    content = {},
                    height = 60.dp
                )

                AdsSDKClient.InterstitialAdContainer(
                    containerId = "demo_interstitial",
                    content = {}
                )

                AdsSDKClient.PopupAdContainer(
                    containerId = "demo_popup",
                    content = {}
                )
            }

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestingAppTheme {
        Greeting("Android")
    }
}