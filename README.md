# Ravn Challenge V3 - Star Wars

## Description
Native Android app where you can see all Star Wars people stored in the [Star Wars GraphQL API](https://swapi-graphql.netlify.app/).
You can view a person's details, save a person as favorite and view your favorites list.

<p align="middle">
    <img src="https://drive.google.com/uc?export=view&id=1-KpQhJ8WnLW7wu-WUObtG9vwwkTu04c9" style="width: 200px; max-width: 100%; height: auto" title="Click to enlarge picture" />
    <img src="https://drive.google.com/uc?export=view&id=1-H7v3DafTxegFByuCZEWmXfi_pDoAnYG" style="width: 200px; max-width: 100%; height: auto" title="Click to enlarge picture" />
    <img src="https://drive.google.com/uc?export=view&id=1-H6ysNStnOHtoLiu0Sv0yDuH_ZyhXyWN" style="width: 200px; max-width: 100%; height: auto" title="Click to enlarge picture" />
</p>

## Setup/Running instructions
### Prerequisites
You need to have installed:
- Recent Android Studio version (e.g., [Chipmunk](https://android-developers.googleblog.com/2022/05/android-studio-chipmunk.html), [Electric Eel](https://developer.android.com/studio/preview/index.html))
- [Git](https://git-scm.com/)

### Installation
1. Clone the GitHub repository
```
HTTPS: git clone https://github.com/EdwinLovo/Ravn-Challenge-V3-EdwinLovo.git
SSH: git clone git@github.com:EdwinLovo/Ravn-Challenge-V3-EdwinLovo.git
```
2. Open the project on Android Studio and wait till the sync is completed
3. Make sure to clean an rebuild the project
4. Now you are ready to run the project on your device or emulator 
5. You can also generate an APK, go to `Build -> Build Bundle(s) / APK(s) -> Build APK(s)`

## Built with
- Gradle v7.0.4
- Minimum SDK 23
- Target SDK 32
- JDK 11
- Kotlin v1.6.10
- Android Studio Chipmunk

## Technologies
- [Jetpack Compose](https://developer.android.com/jetpack/compose?hl=es-419)
- [Apollo GraphQL](https://www.apollographql.com/)
- [Hilt - Dagger](https://dagger.dev/hilt/) for dependency injection
- Clean Architecture + MVVM

## Plugins
- [ktlint](https://ktlint.github.io/#getting-started) - An anti-bikeshedding Kotlin linter with built-in formatter.
- [Kover](https://github.com/Kotlin/kotlinx-kover) - Gradle plugin for Kotlin code coverage. 
You can run `koverMergedHtmlReport` task to get a merged report including all modules (app, domain and data). 
You can find the report here: `build/reports/kover/html/index.html`

## Assumptions 
- Target SDK 32 and Minimum SDK 23
- Landscape mode is disabled
- The app uses only Light Theme (based on given [Design](https://www.figma.com/file/Ceoqa8DbrtyKoOBDR77ktm/Ravn-Code-Challenge?node-id=0%3A1))

## Clarifications
There seems to be an issue with Xiaomi series phones and Dark Theme. Some of the devices force dark theme mode for apps even
if the app does not have a dark theme, causing the design to change.
On some devices you can disable this option by unchecking the app in question from dark mode options:

<p align="middle">
    <img src="https://drive.google.com/uc?export=view&id=1---0zWMCVSFNnzsHeKTKrF45nD6NkOcx" style="width: 200px; max-width: 100%; height: auto" title="Click to enlarge picture" />
</p>