# OldPhoto <img src="imageOldPhoto/oldPhotoLogo.jpg" width="100" height="100"> 
[![](https://jitpack.io/v/Liortra/OldPhoto.svg)](https://jitpack.io/#Liortra/OldPhoto)

## Table of Contents
* [Explanation](https://github.com/Liortra/OldPhoto/blob/master/README.md#explanation)
* [Setup](https://github.com/Liortra/OldPhoto/blob/master/README.md#setup)
* [How To Use](https://github.com/Liortra/OldPhoto/blob/master/README.md#how-to-use)
* [Note](https://github.com/Liortra/OldPhoto/blob/master/README.md#note)
* [License](https://github.com/Liortra/OldPhoto/blob/master/README.md#license)

<img src="imageOldPhoto/oldPhoto.jpg" width="200"> 

## Explanation
A simple library that get your photos from your gallery and change the color to black and white.
Here you have a simple app that use the library to show you how to works.

## Setup
Step 1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

Step 2. Add the dependency:

```
dependencies {
  implementation 'com.github.Liortra:OldPhoto:1.00.01'
}
```

##  How To Use
**1.** Create an instance in your activity
```Java
  Convertor convertor = new Convertor(this);
  ```
**2.** Add the next methods to your activity
```Java
  //handle result of Runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        convertor.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    //handle result of picked image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        convertor.onActivityResult(requestCode,resultCode,data,main_IMG_photo);
    }
```
**3.** Now you can use the method
```Java
  convertor.changePhoto()
```

## Note
The app will ask your premission to get to your gallery, please allow it.

## License

    Copyright 2020 Lior Trachtman

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

