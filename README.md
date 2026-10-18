# Naol Private (Android 15 compatible starter)

This is a native Kotlin Android project intended to build a debug APK with GitHub Actions.

## Current implemented feature
- User-started foreground protection service.
- Loud repeating default alarm + vibration when the charger is disconnected after charging was detected.
- Movement alarm while charging.
- Persistent notification with a visible Stop alarm action.
- No hidden camera capture and no collection/upload of personal data.

## Important Android limitations
- Ordinary third-party apps cannot reliably receive failed lock-screen pattern/PIN events.
- Ordinary apps cannot reliably take a silent selfie while backgrounded or while the device is locked. This project does not attempt covert photography.
- Android does not provide ordinary apps a reliable public broadcast for mobile-data toggle changes. DND automation and app-risk scanning are not implemented in this starter build.
- Foreground-service behavior, sound volume, background execution and battery restrictions vary by phone manufacturer. Test on the target phone.
- This project uses a debug APK for testing, not a Play Store release.

## Build on GitHub using a phone
1. Create/open your repository.
2. Upload the *contents* of this project to the repository root, keeping all paths exactly as shown.
3. Ensure the workflow is at `.github/workflows/android.yml` (not `main.yml` unless you rename it; either filename works if inside this folder).
4. Commit to branch `main`, or open Actions and run `Build Naol Private APK`.
5. Open the successful workflow run and download artifact `Naol-Private-debug-apk`.
6. Extract the artifact ZIP and install `app-debug.apk`. Android may ask you to allow installs from your browser/files app.

## File layout
- `settings.gradle.kts`
- `build.gradle.kts`
- `gradle.properties`
- `app/build.gradle.kts`
- `app/src/main/AndroidManifest.xml`
- `app/src/main/java/com/naol/privateapp/MainActivity.kt`
- `app/src/main/java/com/naol/privateapp/AlarmService.kt`
- `.github/workflows/android.yml`

Do not paste the contents of this README into a Kotlin file. Every file path above matters.
