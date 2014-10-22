
#include <stdio.h>
int temp[10];//一定要设置成全局变量；如果设置成局部变量会报错warning: function returns address of local variable [enabled by default]
int *test(void)
{
	int i = 0;
	for(i=0;i<10;i++){
		temp[i]=i*2;
	}
	return temp;
}

