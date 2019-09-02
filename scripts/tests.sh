#!/usr/bin/env bash
cd "$(dirname "$0")"
cd ..
./gradlew :app:test
./gradlew :data:test
./gradlew :usecases:test
