#!/usr/bin/env bash
cd "$(dirname "$0")"
cd ..
./gradlew :app:connectedAndroidTest
./gradlew :cache:connectedAndroidTest
