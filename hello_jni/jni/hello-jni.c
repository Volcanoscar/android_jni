#include <string.h>
#include <stdlib.h>
#include <assert.h>
#include <jni.h>
#include <stdio.h>
#include <android/log.h>
#define LOGD(fmt,args...) __android_log_print(ANDROID_LOG_DEBUG,TAG,fmt,##args)
#define LOGI(fmt,args...) __android_log_print(ANDROID_LOG_INFO, TAG,fmt,##args)
int *test(void);
static const char *TAG = "hello-jni";
struct fieldIds {
	jclass studentClass;
	jfieldID name;
	jfieldID age;
};

jstring native_hello(JNIEnv* env, jobject thiz) {
	return (*env)->NewStringUTF(env, "dong tai zhu ce de JNI!"); //
}

jint intFromJNI(JNIEnv* env, jobject thiz) {
	LOGI("Java_com_example_hellojni_DataProvider_intFromJNI is called");
	return 6555;
}
jint addFromJNI(JNIEnv* env, jobject thiz, jint i, jint j) {
	return (i + j);
}
jstring getStringFromJNI(JNIEnv* env, jobject thiz, jstring arg) {
	const char *nativeString = (*env)->GetStringUTFChars(env, arg, 0); //
	LOGI("JNI nativeString:%s", nativeString);
	(*env)->ReleaseStringUTFChars(env, arg, nativeString); //
	return arg;
}

//
jintArray getArrayDataFromJNI(JNIEnv* env, jobject thiz, jintArray args) {
	jint buf[10];
	jint i = 0;
	(*env)->GetIntArrayRegion(env, args, 0, 10, buf); //
	int *data = test();
	LOGI("data1:%d,data2:%d,data3:%d,data4:%d",
			data[0], data[1], data[2], data[3]);
	for (i = 0; i < 10; i++) {
		LOGI("data:%d", data[i]);
	}
	//int j = 0;
	for (i = 0; i < 10; i++) {
		//j = buf[i];
		//j = j + 1;
		buf[i] = data[i];
		LOGI("buf is:%d,data is:%d", buf[i], data[i]);
	}
	(*env)->SetIntArrayRegion(env, args, 0, 10, buf); //
	return args;
}


//
JNIEXPORT jintArray dojintArrayMethod(JNIEnv* env, jobject thiz, jintArray arr) {

	int len = (*env)->GetArrayLength(env, arr); // 
	LOGI("len=%d", len);
	if (len == 0) {
		return arr;
	}
	jint* p = (*env)->GetIntArrayElements(env, arr, 0); //
	int i = 0;
	for (i = 0; i < len; i++) {
		LOGI("len=%ld", *(p+i));
		//
		*(p + i) += 5; //
	}
	return arr;
}

//
JNIEXPORT jobjectArray dojobjectArrayMethod(JNIEnv* env, jobject thiz,
		jobjectArray arr) {
	//
	int len = (*env)->GetArrayLength(env, arr);
	LOGI("\n c-stringArray :");
	int i = 0;
	for (i = 0; i < len; i++) {
		jobject obj = (*env)->GetObjectArrayElement(env, arr, i);
		jstring str = (jstring) obj;
		const char * szStr = (*env)->GetStringUTFChars(env, str, 0); //
		LOGI(" %d-%s ", i, szStr);
		(*env)->ReleaseStringUTFChars(env, str, szStr); //
	}
	//
	jstring str;
	jobjectArray args = 0;
	jsize size = 5;
	char* sa[] = { "Hello,", "world!", "zhang", "san", "yuang" };
	int j = 0;
	jclass objClass = (*env)->FindClass(env, "java/lang/String");
	args = (*env)->NewObjectArray(env, size, objClass, 0); //
	for (j = 0; j < size; j++) {
		str = (*env)->NewStringUTF(env, sa[j]); //
		(*env)->SetObjectArrayElement(env, args, j, str); //
	}
	return args;
}

setStudentAge(JNIEnv* env, jobject thiz, jobject student) {
	struct fieldIds studentFieldIds;
	studentFieldIds.studentClass = (*env)->FindClass(env,"com/example/hellojni/Student");//FindClass
	if (studentFieldIds.studentClass == NULL)
		return;
	studentFieldIds.name = (*env)->GetFieldID(env, studentFieldIds.studentClass,"name", "Ljava/lang/String;"); //GetFieldID
	studentFieldIds.age = (*env)->GetFieldID(env, studentFieldIds.studentClass,"age", "I"); //GetFieldID
	(*env)->SetIntField(env, student, studentFieldIds.age, 250); // change the 'age' value of student
}

callCcode(JNIEnv* env, jobject thiz) {
	char* classname = "com/example/hellojni/DataProvider";//java里的类
	jclass dpclazz = (*env)->FindClass(env, classname);
	if (dpclazz == 0) {
		LOGI("not find class!");
	} else
		LOGI("find class");
	jmethodID methodID = (*env)->GetMethodID(env, dpclazz, "helloFromJava","()V"); //GetMethodID
	if (methodID == 0) {
		LOGI("not find method!");
	} else
		LOGI("find method");
	(*env)->CallVoidMethod(env, thiz, methodID);//CallVoidMethod
}

callCcode1(JNIEnv* env, jobject thiz) {
	LOGI("in code");
	//
	char* classname = "com/example/hellojni/DataProvider";
	jclass dpclazz = (*env)->FindClass(env, classname);
	if (dpclazz == 0) {
		LOGI("not find class!");
	} else
		LOGI("find class");
	jmethodID methodID = (*env)->GetMethodID(env, dpclazz, "printString","(Ljava/lang/String;)V"); //GetMethodID
	if (methodID == 0) {
		LOGI("not find method!");
	} else{
			LOGI("find method");
	}
	(*env)->CallVoidMethod(env, thiz, methodID,(*env)->NewStringUTF(env, "haha")); //CallVoidMethod
}

callCcode2(JNIEnv* env, jobject thiz) {
	char* classname = "com/example/hellojni/DataProvider";
	jclass dpclazz = (*env)->FindClass(env, classname);
	jmethodID methodID = (*env)->GetMethodID(env, dpclazz, "Add", "(II)I"); //GetMethodID
	jint result = (*env)->CallIntMethod(env, thiz, methodID, 23, 54); //CallIntMethod
	LOGI("return result is??%d", result);
}
/********************************************************************************************************************************************
* 方法对应表
*/
static JNINativeMethod gMethods[] = {
		{ "stringFromJNI", "()Ljava/lang/String;",(void*)native_hello }, //绑定
		{ "intFromJNI", "()I", (void*)intFromJNI }, //绑定
		{ "addFromJNI", "(II)I", (void*)addFromJNI }, //绑定
		{ "getStringFromJNI", "(Ljava/lang/String;)Ljava/lang/String;",(void*)getStringFromJNI }, //绑定
		{ "getArrayDataFromJNI", "([I)[I", (void*)getArrayDataFromJNI }, //绑定
		{ "dojintArrayMethod", "([I)[I", (void*)dojintArrayMethod }, //绑定
		{ "dojobjectArrayMethod", "([Ljava/lang/String;)[Ljava/lang/String;",(void*)dojobjectArrayMethod }, //绑定
		{ "setStudentAge", "(Lcom/example/hellojni/Student;)V",(void*)setStudentAge }, //绑定
		{ "callCcode", "()V", (void*)callCcode }, //绑定
		{ "callCcode1", "()V", (void*)callCcode1 }, //绑定
		{ "callCcode2", "()V", (void*)callCcode2 }, //如果Java函数的参数是class，则以"L"开头，以";"结尾，中间是用"/" 隔开的包及类名。而其对应的C函数名的参数则为jobject. 
};

/*
* 为某一个类注册本地方法
*/
static int registerNativeMethods(JNIEnv* env, const char* className,
		JNINativeMethod* gMethods, int numMethods) {
	jclass clazz;
	clazz = (*env)->FindClass(env, className);
	if (clazz == NULL) {
		return JNI_FALSE;
	}
	if ((*env)->RegisterNatives(env, clazz, gMethods, numMethods) < 0) {
		return JNI_FALSE;
	}
	return JNI_TRUE;
}
/*
* 为所有类注册本地方法
*/
static int registerNatives(JNIEnv* env) {
	const char* kClassName = "com/example/hellojni/DataProvider"; //指定要注册的类
	return registerNativeMethods(env, kClassName, gMethods,
			sizeof(gMethods) / sizeof(gMethods[0]));
/*
* System.loadLibrary("lib")时调用
* 如果成功返回JNI版本, 失败返回-1
*/
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved) {
	JNIEnv* env = NULL;
	jint result = -1;
	if ((*vm)->GetEnv(vm, (void**) &env, JNI_VERSION_1_4) != JNI_OK) {
		return -1;
	}
	assert(env != NULL);
	if (!registerNatives(env)) { //注册
		return -1;
	}
	//成功
	result = JNI_VERSION_1_4;
	return result;
}
