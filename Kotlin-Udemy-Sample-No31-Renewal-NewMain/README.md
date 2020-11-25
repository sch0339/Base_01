# Udemy에서 진행하는 Kotlin 샘플 자료

[![License](https://img.shields.io/hexpm/l/plug.svg)]()

Udemy 코틀린 안드로이드 시작하기! 강좌의 샘플 자료입니다.

## GitHub 페이지

- [Kotlin-Udemy-Sample](http://thdev.tech/Kotlin-Udemy-Sample/)

## 강의 자료

- [01. Kotlin base init](https://github.com/taehwandev/Kotlin-Udemy-Sample/tree/01-base-init)
  - `Part 4` : 코틀린으로 Activity/Fragment/Util 클래스에 대해서 다룹니다.
- [02. Kotlin Null Safety](https://github.com/taehwandev/Kotlin-Udemy-Sample/tree/02-null-safety)
  - `Part 5/Part 6` : 코틀린의 안전한 null 처리에 대해서 정리한 자료입니다.
- [03. Kotlin class](https://github.com/taehwandev/Kotlin-Udemy-Sample/tree/03-class)
  - `Part 7/Part 8` : 코틀린 Class에 대하여 일부 일부 정리하였습니다.
- [04. MVC](https://github.com/taehwandev/Kotlin-Udemy-Sample/tree/04-mvc)
  - `Part 9` : Android MVC 코드로 작성한 예제입니다. RecyclerView를 통한 리스트 뷰로 정리하였습니다.
- [05. MVP](https://github.com/taehwandev/Kotlin-Udemy-Sample/tree/05-mvp)
  - `Part 10` : Android MVC 코드를 MVP로 변경한 예제입니다.
- [06. MVP - Model](https://github.com/taehwandev/Kotlin-Udemy-Sample/tree/06-mvp-model)
  - `Part 11` : Android MVP에 Model을 추가로 분환할 예제입니다.
- [07. RecyclerView](https://github.com/taehwandev/Kotlin-Udemy-Sample/tree/07-RecyclerView)
  - `Part 12` : RecyclerView를 소개합니다.
- [08. Retrofit Intro](https://github.com/taehwandev/Kotlin-Udemy-Sample/tree/08-Retrofit-intro)
  - `Part 13` : Retrofit 소개
- [09. Retrofit과 RecyclerView 연결하기](https://github.com/taehwandev/Kotlin-Udemy-Sample/tree/09-Retrofit-RecyclerView)
  - `Part 14` : Retrofit과 RecyclerView 연결하기
- [10. Image Load Library Sample](https://github.com/taehwandev/Kotlin-Udemy-Sample/tree/10-Image-Library)
  - `Part 15` : Image Load Library을 설명하고, AsyncTask, Thread, Glide을 다룬다.
- [11. 추가 페이지 제작](https://github.com/taehwandev/Kotlin-Udemy-Sample/tree/11-Add-Detail-Page)
  - `Part 16/Part 17` : 새로운 페이지를 생성하고, Parcelable/BottomSheet을 사용한 예제를 살펴본다.
- [12. Android UI Test](https://github.com/taehwandev/Kotlin-Udemy-Sample/tree/12-UI-Test)
  - `Part 18/Part 19` : Android UI Test에 포함된 자료입니다
- [13. Android Presenter Test](https://github.com/taehwandev/Kotlin-Udemy-Sample/tree/13-Presetner-Test)
  - `Part 20` : Mockito을 이용하여 Presenter을 테스트한다
- [14. Android Renderscript](https://github.com/taehwandev/Kotlin-Udemy-Sample/tree/14-Renderscript)
  - `Part 21` : Android Renderscript - 아이템 롱클릭시 Blur 처리 예제

## Preview Java

| Java Sample       |                 |
|:-----------------:|:---------------:|
| ![sample_java_01] |![sample_java_02]|

## Preview Kotlin

| Kotlin Sample       |                 |
|:-------------------:|:-----------------:|
| ![sample_kotlin_01] |![sample_kotlin_02]|
| ![sample_kotlin_03] |![sample_kotlin_04]|

## Kotlin blur sample

![blur_sample]

## Kotlin bottom sheet

![sample_kotlin_anim]

## UI Test

- RecyclerView item click test

![ui_test_03]

- Test RecyclerView and Detail page

![ui_test_01]

- Menu test

![ui_test_02]

## UI Test References

- [Android Testing Support Library](https://google.github.io/android-testing-support-library/)
- [Kotlin testing](https://medium.com/@sergii/using-kotlin-for-tests-in-android-6d4a0c818776#.ios8lnr1u)

- [Android user interface testing with Espresso - Tutorial](http://www.vogella.com/tutorials/AndroidTestingEspresso/article.html)
- [Android Testing Codelab](https://codelabs.developers.google.com/codelabs/android-testing/#0)
- [UI Testing with Espresso](https://guides.codepath.com/android/UI-Testing-with-Espresso)

- [androidTest-JUnit4, Espresso를 이용한 테스트](http://thdev.tech/androiddev/2016/05/04/Android-Test-Example.html)

- [Espresso Example](https://github.com/vgrec/EspressoExamples)

## Presenter Test References

- [Mockito](http://site.mockito.org/)
- [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin)
- [androidTest - JUnit4, Espresso를 이용한 테스트 코드 작성](http://thdev.tech/androiddev/2016/05/04/Android-Test-Example.html)

## 이번 장에서 사용한 API

- [Glide](https://github.com/bumptech/glide)
- [Retrofit 2.0](https://square.github.io/retrofit/)
- [OkHttp](https://github.com/square/okhttp)
- [OkHttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
- [Thdev Android Base](https://github.com/taehwandev/AndroidBase)
    - 제가 작성한 Android Base(Kotlin 기반)의 코드를 사용하였습니다.(MVP 패턴으로 작성)

## API Key

- Create Flickr api key.
    - [Flickr web page](https://www.flickr.com/services/apps/create/)
- Add a flickr api key `local.properties`.
    - flickrApiKey="Flickr api key"

## Load 실패가 발생할 경우

![load_fail]

위와 같은 화면을 확인하였다면 다음의 순서대로 따라주세요.

- [API 생성 페이지로 이동합니다.](https://www.flickr.com/services/apps/create/)
    - 다음의 페이지에서 01. `API 키 요청`을 눌러주세요.
        ![flickr_01]
    - `비상업용 키 신청`을 눌러주세요.
        ![flickr_02]
    - App의 이름과 설명을 간단하게 작성해주세요.
        ![flickr_03]
    - App 키가 발급되었습니다.
        ![flickr_04]
    - 발급받은 APP 키를 `local.properties`에 아래와 같이 추가하세요.
        `flickrApiKey="발급 받은 키"`
        ![flickr_05]

## License

```
Copyright 2016-2019 Tae-hwan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[load_fail]: images/load_fail.png

[flickr_01]: images/flickr_01.png
[flickr_02]: images/flickr_02.png
[flickr_03]: images/flickr_03.png
[flickr_04]: images/flickr_04.png
[flickr_05]: images/flickr_05.png

[sample_java_01]: images/sample_java_01.png
[sample_java_02]: images/sample_java_02.png

[sample_kotlin_01]: images/sample_kotlin_01.png
[sample_kotlin_02]: images/sample_kotlin_02.png
[sample_kotlin_03]: images/sample_kotlin_03.png
[sample_kotlin_04]: images/sample_kotlin_04.png
[sample_kotlin_anim]: images/sample_kotlin_anim.gif

[ui_test_01]: images/ui_test_01.gif
[ui_test_02]: images/ui_test_02.gif
[ui_test_03]: images/ui_test_03.gif

[blur_sample]: images/blur_sample.png
