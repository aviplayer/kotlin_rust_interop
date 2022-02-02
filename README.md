# Set up
For now project is adapted only for windows OS.
But you can easy prepare build for any OS.
Go to 
https://github.com/aviplayer/rust_interop_server.git
Compile Rust part of project and copy libs to src/nativeInterop/cinterop/libserver
and finally execute ```radle clean build```
to run the program use ```gradle runDebugExecutableNative```
You can run the binary by just dbl clicking on reult of compilation, but dynamic lib should be providd. In case of Windows OS copy from src/nativeInterop/cinterop/libserver/lib_server.dll 

# Important
You need to change ${project.dir} in src/nativeInterop/cinterop/libserver.def to absolute paths. This is due to Issue: https://youtrack.jetbrains.com/issue/KT-48082
