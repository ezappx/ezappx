#!/usr/bin/env bash

echo "deploy ezappx-builder ..."
scp EzappxBuilder/build/libs/EzappxBuilder-1.0.0.jar ing@www.ezappx.com:~/ezapp

echo "deploy ezappx-web ..."
scp EzappxWeb/build/libs/EzappxWeb-1.0.0.jar ing@www.ezappx.com:~/ezapp
