// CombineFile.cpp : 定义控制台应用程序的入口点。
//

//#include "stdafx.h"
#include <jni.h>
#include <fstream>
#include <string>
#include <sstream>
#include <iostream>
#include "json/rapidjson.h"
#include "json/document.h"
#include "json/stringbuffer.h" 
#include "json/writer.h" 
using namespace std;
using namespace rapidjson;

char* head = "jingxiaoshijiayoumyuiszhazha";
//以二进制方式将文件读入内存
unsigned char* readFileToMem(const char* fileName, long* psize, bool forString)
{
	unsigned char* buffer = NULL;
	size_t size = 0;
	size_t readsize;
	const char* mode = NULL;

	if (forString)
		mode = "rt";
	else
		mode = "rb";

	do
	{
		// Read the file from hardware
		FILE *fp = fopen(fileName, mode);
		if (!fp)
			return NULL;
		fseek(fp, 0, SEEK_END);
		size = ftell(fp);
		fseek(fp, 0, SEEK_SET);

		if (forString)
		{
			buffer = (unsigned char*)malloc(sizeof(unsigned char) * (size + 1));
			buffer[size] = '\0';
			*psize = size + 1;
		}
		else
		{
			buffer = (unsigned char*)malloc(sizeof(unsigned char) * size);
			*psize = size;
		}

		readsize = fread(buffer, sizeof(unsigned char), size, fp);
		fclose(fp);

		if (forString && readsize < size)
		{
			buffer[readsize] = '\0';
		}
	} while (0);
	return buffer;
}

int writeMemToFile(char* buf, long size, const char* fileName)
{
	int result = 1;
	FILE* file = NULL;
	file = fopen(fileName, "wb");
	if (file == NULL)
	{
		return 0;
	}
	size_t wSize = fwrite(buf, 1, size, file);
	if (wSize != size)
	{
		result = 0;
	}
	fclose(file);
	return result;
}

bool isDeCode(const char* inFileName)
{
	long size;
	unsigned char* read_inBuf = readFileToMem(inFileName, &size, false);
	char fileIsMyBin = 1;
	long i = 0;

	if (read_inBuf == NULL)
	{
		return false;
	}

	for (i = 0; i < strlen(head); ++i)
	{
		if (read_inBuf[i] != head[i])
		{
			fileIsMyBin = 0;
			free(read_inBuf);
			return false;
		}
	}
	free(read_inBuf);
	return true;
}

char deCode(const char* inFileName, const char* outFileName)
{
	long size;
	unsigned char* read_inBuf = readFileToMem(inFileName, &size, false);
	char fileIsMyBin = 1;
	long i = 0;
	unsigned char* inBuf = NULL;
	int N = 0;
	int* array_N = NULL;
	unsigned char* inBuf_start = 0;
	long len = 0;
	char* outBuf_start = 0;
	unsigned char* inBuf_temp = 0;
	char* outBuf_temp = 0;
	long j = 0;

	if (read_inBuf == NULL)
	{
		return 3;
	}

	for (i = 0; i < strlen(head); ++i)
	{
		if (read_inBuf[i] != head[i])
		{
			fileIsMyBin = 0;
			break;
		}
	}
	if (fileIsMyBin == 0)
	{
		free(read_inBuf);
		return 0;
	}

	inBuf = read_inBuf + strlen(head);
	size -= strlen(head);

	N = *((int*)inBuf);
	array_N = (int*)(inBuf + sizeof(int));

	inBuf_start = inBuf + sizeof(int) + N * sizeof(int);
	len = size - sizeof(int) - N * sizeof(int);
	outBuf_start = (char*)malloc(len);
	memcpy(outBuf_start, inBuf_start, len);

	for (i = 0; i < len / N; ++i)
	{
		inBuf_temp = inBuf_start + (i*N);
		outBuf_temp = outBuf_start + (i*N);
		for (j = 0; j < N; ++j)
		{
			outBuf_temp[array_N[j]] = inBuf_temp[j];
		}
	}

	if (writeMemToFile(outBuf_start, len, outFileName) != 1)
	{
		free(outBuf_start);
		free(read_inBuf);
		return 2;
	}

	free(outBuf_start);
	free(read_inBuf);

	return 1;
}

void readBinToBuffer(const char* binFileName, int** buffer, int& len)
{
	//读取BIN文件
	FILE* file = NULL;
	file = fopen(binFileName, "rb");

	if (NULL == file)
		return;

	fseek(file, 0, SEEK_END); //定位到文件末 
	long fileSize = ftell(file); //文件长度
	fseek(file, 0, SEEK_SET); //定位到文件头 
	char *binData = new char[fileSize];
	fread(binData, fileSize, 1, file);

	len = fileSize / 4;
	*buffer = (int*)binData;

	fclose(file);
}

bool isIniBinFile(const char* fileName)
{
	bool r = false;
	int* buffer = NULL;
	char* src_buffer = NULL;
	int buf_size = 0;
	readBinToBuffer(fileName, &buffer, buf_size);
	src_buffer = (char*)buffer;

	for (int i = 0; i < buf_size; ++i, buffer++)
	{
		if (*buffer == 0xffffffff)
		{
			r = true;
			break;
		}
	}
	delete[] src_buffer;
	return r;
}


//************************************
// Method:    dealCheatFile
// FullName:  dealCheatFile
// Access:    public 
// Returns:   int 0:sucess; 1:read fileSrc error; 2:write fileDst error.
// Qualifier:
// Parameter: string fileSrc
// Parameter: string fileDst
//************************************
int dealCheatFile(const string fileSrc, const string fileDst)
{
	int ret = 0;
	FILE* file = NULL;

	////判断此文件是否加密
	string tempfile = "temp.ini";
	char nedDecode = deCode(fileSrc.c_str(), tempfile.c_str());
	string fn;
	if (nedDecode)
		fn = tempfile;
	else
		fn = fileSrc;

	if (!isIniBinFile(fn.c_str()))
	{
		unsigned char* buff;
		long size = 0;

		buff = readFileToMem(fn.c_str(), &size, true);
		if (nedDecode == 1)
			remove(tempfile.c_str());

		if (buff == NULL)
		{
			printf("dealCheatFile read %s error\n", fileSrc.c_str());
			return 1;
		}

		string src = (char*)buff;
		stringstream sstream;
		sstream << src;

		ofstream fs;
		fs.open(fileDst.c_str(), ios::out | ios::trunc);
		if (fs)
		{
			char buffer[128] = "";
			while (sstream.getline(buffer, 128))
			{
				string line = buffer;
				string::size_type pos = line.find(",");
				if (pos != string::npos)
				{
					fs << line.substr(0, pos) << '\n';
				}
				else
					fs << line << '\n';
			}
			fs.close();
		}
		else
		{
			printf("dealCheatFile write %s error\n", fileDst.c_str());
			ret = 2;
		}

		free(buff);
	}
	else
	{	//金手指文件为二进制时，执行下面的解析
		//LOGE("EmuCheat::loadCheat bin: %s %p\n", fileName);
		int* buffer = NULL;
		char* src_buffer = NULL;
		int buf_size = 0;
		readBinToBuffer(fn.c_str(), &buffer, buf_size);
		if (nedDecode == 1)
			remove(tempfile.c_str());
		
		if (buffer == NULL)
		{
			printf("dealCheatFile read %s error\n", fileSrc.c_str());
			return 1;
		}

		src_buffer = (char*)buffer;
		//解析
#define MAX_BUFFER_ROW 1000
		char** ppBuffer = new char*[MAX_BUFFER_ROW];
		for (int i = 0; i < MAX_BUFFER_ROW; ++i)
		{
			ppBuffer[i] = new char[128];
			memset(ppBuffer[i], 0, 128);
		}

		int index = 0;
		int ppBufferIndex = 0;
		ofstream fs;
		fs.open(fileDst.c_str(), ios::out | ios::trunc);
		if (fs)
		{
			for (int i = 0; i < buf_size; ++i, buffer++)
			{
				if (*buffer == 0xffffffff)
				{
					index = 0;
					string line = ppBuffer[ppBufferIndex];
					string::size_type pos = line.find(",");
					if (pos != string::npos)
					{
						fs << line.substr(0, pos) << '\n';
					}
					else
						fs << line << '\n';
					ppBufferIndex++;
				}
				else {
					ppBuffer[ppBufferIndex][index] = *buffer - 0xcc;
					index++;
				}
			}
		}
		else
		{
			printf("dealCheatFile write %s error\n", fileDst.c_str());
			ret = 2;
		}

		for (int i = 0; i < MAX_BUFFER_ROW; ++i)
		{
			delete[] ppBuffer[i];
		}
		delete[] ppBuffer;
		delete[] src_buffer;
	}
	return ret;
}

//************************************
// Method:    dealSpKeyFile
// FullName:  dealSpKeyFile
// Access:    public 
// Returns:   int 0:sucess; 1:read fileSrc error; 2:write fileDst error.
// Qualifier:
// Parameter: string fileSrc
// Parameter: string fileDst
//************************************
int dealSpKeyFile(const string fileSrc, const string fileDst)
{
	int ret = 0;
	////判断此文件是否加密
	string tempfile = "temp.json";

	char nedDecode = deCode(fileSrc.c_str(), tempfile.c_str());

	unsigned char* json;
	long size = 0;
	if (nedDecode == 1)
	{
		json = readFileToMem(tempfile.c_str(), &size, true);
		remove(tempfile.c_str());
	}
	else {
		json = readFileToMem(fileSrc.c_str(), &size, true);
	}

	if (json == NULL)
	{
		printf("dealSpKeyFile read %s error\n", fileSrc.c_str());
		return 1;
	}

	if (size == 0)
	{
		printf("dealSpKeyFile read %s error\n", fileSrc.c_str());
		return 1;
	}
	rapidjson::Document document;
	document.Parse<0>((char *)json);
	if (document.HasParseError())  //打印解析错误
	{
		free(json);
		printf("dealSpKeyFile read % error\n", fileSrc.c_str());
		return 1;
	}
	if (!document.IsObject())
	{
		free(json);
		printf("dealSpKeyFile read %s error\n", fileSrc.c_str());
		return 1;
	}

	if (document.HasMember("cha_addr_1p"))
		document["cha_addr_1p"].SetInt(0);
	if (document.HasMember("dir_addr_1p"))
		document["dir_addr_1p"].SetInt(0);
	if (document.HasMember("dir_value_1p"))
		document["dir_value_1p"].SetInt(0);
	if (document.HasMember("cha_addr_2p"))
		document["cha_addr_2p"].SetInt(0);
	if (document.HasMember("dir_addr_2p"))
		document["dir_addr_2p"].SetInt(0);
	if (document.HasMember("dir_value_2p"))
		document["dir_value_2p"].SetInt(0);
	if (document.HasMember("cha_addr_3p"))
		document["cha_addr_3p"].SetInt(0);
	if (document.HasMember("dir_addr_3p"))
		document["dir_addr_3p"].SetInt(0);
	if (document.HasMember("dir_value_3p"))
		document["dir_value_3p"].SetInt(0);
	if (document.HasMember("cha_addr_4p"))
		document["cha_addr_4p"].SetInt(0);
	if (document.HasMember("dir_addr_4p"))
		document["dir_addr_4p"].SetInt(0);
	if (document.HasMember("dir_value_4p"))
		document["dir_value_4p"].SetInt(0);

	if (document.HasMember("skills_table"))
	{
		rapidjson::Value &pArray = document["skills_table"];

		if (pArray.IsArray())
		{
			for (rapidjson::SizeType i = 0; i < pArray.Size(); i++)
			{
				rapidjson::Value& sp = pArray[i];

				if (sp.HasMember("cha_value"))
				{
					sp["cha_value"].SetInt(0);
				}

				if (sp.HasMember("skill"))
				{
					if (sp["skill"].IsArray())
					{
						for (rapidjson::SizeType j = 0; j < sp["skill"].Size(); j++)
						{
							rapidjson::Value& vSkill = sp["skill"][j];
							if (vSkill.HasMember("skill_key"))
								vSkill["skill_key"].SetString("");
						}
					}
				}
			}
		}
		else
		{
			ret = 1;
		}
	}
	else
	{
		ret = 1;
	}
	if (ret != 0)
	{
		free(json);
		printf("dealSpKeyFile read %s error\n", fileSrc.c_str());
		return ret;
	}
	ofstream fs;
	fs.open(fileDst.c_str(), ios::out | ios::trunc);
	if (fs)
	{
		StringBuffer sJson;
		rapidjson::Writer<StringBuffer> writer(sJson);
		document.Accept(writer);

		fs << (char*)sJson.GetString();
		fs.close();
	}
	else
	{
		printf("dealSpKeyFile write %s error\n", fileDst.c_str());
		ret = 2;
	}
	free(json);
	return ret;
}

extern "C" {
	/*
	* Class:     com_wufan_content_so_SimulatorAntiCrackingJni
	* Method:    convertContent
	* Signature: (ILjava/lang/String;Ljava/lang/String;)V
	*/
	JNIEXPORT void JNICALL Java_com_wufan_wfcrontabjob_service_jni_AntiCrackingCfgJni_convertContent(JNIEnv *env, jclass, jint type, jstring jsrc, jstring jdst)
	{
		const char* filesrc = env->GetStringUTFChars(jsrc, NULL);
		const char* filedst = env->GetStringUTFChars(jdst, NULL);
		if (type == 1)
			dealCheatFile(filesrc, filedst);
		else if (type == 2)
			dealSpKeyFile(filesrc, filedst);
		env->ReleaseStringUTFChars(jsrc, filesrc);
		env->ReleaseStringUTFChars(jdst, filedst);
	}
}

//int _tmain(int argc, _TCHAR* argv[])
//{
//
//	//dealSpKeyFile("D:\\temp\\skill_table.json", "D:\\temp\\skill_table111.json");
//	dealCheatFile("D:\\temp\\hdl2.ini", "D:\\temp\\hdl2111.ini");
//
//	return 0;
//}
