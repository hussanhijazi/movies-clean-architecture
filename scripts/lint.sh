#!/usr/bin/env bash
cd "$(dirname "$0")"
cd ..
./gradlew detekt
