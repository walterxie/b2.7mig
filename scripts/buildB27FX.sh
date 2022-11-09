#!/bin/bash

### for Mac
### modified from Jordan's script

cd ../beast2/
ant compile-all

cd ../BeastFX/
ant linux
cp build/dist/BEAST.app.jar ~/Library/Application\ Support/BEAST/2.7/BEAST.app/lib/
cp ../beast2/release/Linux/beast/lib/BEAST.base.jar ~/Library/Application\ Support/BEAST/2.7/BEAST.base/lib/

cd ~/WorkSpace/BeastFX

ls -l ~/Library/Application\ Support/BEAST/2.7/BEAST.base/lib/
ls -l ~/Library/Application\ Support/BEAST/2.7/BEAST.app/lib/

echo "----Done----"


