# OldPhoto
[![](https://jitpack.io/v/Liortra/OldPhoto.svg)](https://jitpack.io/#Liortra/OldPhoto)

## Table of Contents
* [What Is It for](https://github.com/Liortra/OldPhoto/edit/master/README.md#explanation)
* [Setup](https://github.com/Liortra/OldPhoto/edit/master/README.md#setup)


## Explanation
A simple library that get your photos from your gallery and change the color to black and white.

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
  implementation 'com.github.Liortra:OldPhoto:Tag'
}
```

##  How To Use
**1** Create an instance in your activity
```Java
  Convertor convertor = new Convertor(this);
  ```
**2** Add the next methods to your activity
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
**3** Now you can use the method
```Java
  convertor.changePhoto()
```
