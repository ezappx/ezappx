#!/usr/bin/env bash

echo "deploy ezappx-builder ..."
scp EzappxBuilder/build/libs/EzappxBuilder-1.0.0.jar ing@www.ezappx.com:~/ezapp

echo "deploy ezappx-designer ..."
scp EzappxDesigner/build/libs/EzappxDesigner-1.0.0.jar ing@www.ezappx.com:~/ezapp
