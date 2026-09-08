# aduo-test-app

Reference Android app demonstrating [aduo-sdk-kotlin](https://github.com/Dev-Harsh0218/aduo-sdk-kotlin) integration end-to-end. If you're wiring the Aduo SDK into a real Android app, clone this repo, look at how the calls flow, then port the pattern.

Part of the [Aduo](https://github.com/Dev-Harsh0218/aduo) platform.

## What it demonstrates

- Initializing the SDK once per process with `Aduo.init(context, sdkId)`
- Showing a banner ad in a `ViewGroup` container
- Triggering an interstitial ad between activities
- Wiring up `onAdShown` / `onAdClicked` / `onAdClosed` callbacks
- Handling the fail-open case where the backend is unreachable

## Structure

```
aduo-test-app/
├── app/
│   ├── src/main/               # Kotlin activities + layouts using the SDK
│   ├── libs/                   # drop the compiled aduo-sdk-kotlin AAR here
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── build.gradle.kts            # root project config
├── settings.gradle.kts
└── gradle/libs.versions.toml   # Gradle version catalog
```

## Running locally

1. **Build the SDK first:**
   ```bash
   cd ../aduo-sdk-kotlin
   ./gradlew :adSdk:assembleRelease
   cp adSdk/build/outputs/aar/adSdk-release.aar ../aduo-test-app/app/libs/
   ```

2. **Open this project in Android Studio** (or command line):
   ```bash
   cd aduo-test-app
   ./gradlew installDebug          # requires emulator or connected device
   ```

3. **Point the SDK at a backend** — set `SDK_KEY` in `app/src/main/res/values/strings.xml` to a valid tenant key issued by [aduo-backend](https://github.com/Dev-Harsh0218/aduo-backend). If no backend is running, the app still launches — ads simply don't render (fail-open behavior).

## Notes for real integrations

- The SDK's `Aduo.init()` should be called from your `Application.onCreate()` or the very first activity — not per-screen.
- Banner containers must be sized before `showBanner()` is called. If the container has `wrap_content` height, banner may not render.
- Interstitials automatically dismiss after user interaction or timeout — no need for manual `.dismiss()` calls.

## Related repos

- [`aduo`](https://github.com/Dev-Harsh0218/aduo) — platform meta-repo
- [`aduo-sdk-kotlin`](https://github.com/Dev-Harsh0218/aduo-sdk-kotlin) — the SDK this app integrates
- [`aduo-backend`](https://github.com/Dev-Harsh0218/aduo-backend) — the API the SDK talks to
- [`aduo-web`](https://github.com/Dev-Harsh0218/aduo-web) — advertiser console (create campaigns to see them in this app)
