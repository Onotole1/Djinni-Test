#!/bin/bash

./djinni/src/run \
   --java-out app/src/main/java/com/example/anatoly/djinnigenerated \
   --java-package com.example.anatoly.djinnigenerated \
   --ident-java-field mFooBar \
   --cpp-out app/src/main/jni/djinnigenerated/cpp \
   --cpp-namespace Prime \
   --jni-namespace Prime \
   --jni-out app/src/main/jni/djinnigenerated/jni \
   --ident-jni-class NativeFooBar \
   --ident-jni-file NativeFooBar \
   --idl prime.djinni