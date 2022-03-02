#include "com_bkhech_home_practice_jni_JNITest.h"
#include <windows.h>

JNIEXPORT void JNICALL Java_com_bkhech_home_practice_jni_JNITest_callCppMethod(JNIEnv*, jclass) {
	printf("**Cpp Method**\nprint from cpp");
}