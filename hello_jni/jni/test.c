
#include <stdio.h>
int temp[10];//һ��Ҫ���ó�ȫ�ֱ�����������óɾֲ������ᱨ��warning: function returns address of local variable [enabled by default]
int *test(void)
{
	int i = 0;
	for(i=0;i<10;i++){
		temp[i]=i*2;
	}
	return temp;
}

