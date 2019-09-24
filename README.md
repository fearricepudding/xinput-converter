Xinput to libinput Calibration Matrix converter

To get the information you will need:

_Screen with and height_<br />
Run the command `xrandr` and it will give you the current resolution of your screen. <br />
If you are getting the display information remotely, use `export DISPLAY=:0 ` before running the command 

_click positions_<br />
Install and run `xinput_calibrator -v` and on each click you will get an output that looks like:

<br />

DEBUG: Adding click 0 (X=175, Y=102) <br />
DEBUG: Adding click 1 (X=1185, Y=104) <br />
DEBUG: Adding click 2 (X=178, Y=680) <br />
DEBUG: Adding click 3 (X=1187, Y=677) <br />

<br />

_display name_<br />
Run `xinput` and find the one on the list that matches your touch controller<br />

<br />
Once done, click the convert button and you should get an output that looks like: <br />

xinput set-prop "USBest Technology SiS HID Touch Controller" "libinput Calibration Matrix" 1.047060276679842, 0.0, -0.026309288537549, 0.0, 1.001739130434783, -0.008043478260870, 0.0, 0.0, 1.0

<br />
Copy that line to your console and run it. 