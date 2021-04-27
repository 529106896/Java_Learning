package my_project;

import java.util.Date;

public class HelloWorld {
	public static void main(String[] args)
	{
		int a = 5;
		
		//十进制输出整数
		System.out.printf("The first a is %d\n", a);
		System.out.printf("The second a is %d%n", a);
		
		//八进制输出整数
		int b = 9;
		System.out.printf("b in oct is %o\n", b);

		//十六进制输出整数
		int c = 47;
		System.out.printf("c in hex is %x\n", c);
		System.out.printf("c in hex is %X\n", c);
		
		System.out.printf("0x16 is %d\n", 0x16);
		System.out.printf("011 is %d\n", 011);
		
		//输出浮点数
		System.out.printf("%f\n", 3.14);
		//System.out.printf("%lf\n", 3.144444444444444444);//报错
		System.out.printf("%.0f\n", 3.14);
		System.out.printf("%.2f\n", 3.14845132);//会四舍五入
		System.out.printf("%.10f\n", 3.14845132);
		System.out.printf("%f\n", 3.12345678910111213141599999);//一般情况下只保留6位
		System.out.printf("%.100f\n", 3.12345678910111213141599999);//最多保留15位精度
		
		//科学计数法输出浮点数
		System.out.printf("%e\n", 3.14);
		System.out.printf("%E\n", 3.14);
		System.out.printf("%.3e\n", 3.14);
		System.out.printf("%e\n", 3.12345678910111213141599999);//一般情况只保留6位
		System.out.printf("%.100e\n", 3.12345678910111213141599999);//最多保留15位精度
		
		//通用浮点数类型
		System.out.printf("%g\n", 3.14);
		
		//字符类型
		System.out.printf("%c\n", 'a');
		int testchar = 'a';
		//System.out.printf("%d\n", 'a');
		System.out.printf("%c\n", 97);
		System.out.printf("%d\n", testchar);
		
		//字符串类型
		System.out.printf("HelloWorld!\n");
		
		String str = "I love China";
		System.out.printf("%s\n", str);
		System.out.printf("%S\n", str);//把字符串转换成大写
		
		String str1 = "1.Henan Province";
		String str2 = "2.Xiamen University";
		String str3 = "3.Software Engineering";
		
		System.out.printf("%s%n%s\n%s\n\n", str1, str2, str3);
		
		//用%(number)$(d、s、f......)的形式来调整顺序
		System.out.printf("%3$s%n%1$s\n%2$s\n", str1, str2, str3);
		
		//布尔类型
		System.out.printf("%b\n", true);
		System.out.printf("%b\n", false);
		//System.out.printf("%d\n", false);报错
		
		//输出特殊字符
		System.out.printf("百分号%%\n");
		System.out.printf("双引号\"\n");
		System.out.printf("反斜杠\\\n");
		
		//格式符和参数不匹配的情况
		//System.out.printf("%d %d %d\n", 15);//格式符 > 参数      报错
		System.out.printf("%2$d\n", 15, 16, 17);//格式符 < 参数    只输出一个参数
		
		//其他
		System.out.printf("%+d %+d\n", 15, -15);//在数字前显示正负号
		System.out.printf("%5d\n", 15);//最小字段宽度
		System.out.printf("% 5d\n", 15);//用空格填充字段宽度
		System.out.printf("%05d\n", 15);//用0填充宽度
		System.out.printf("%-5d%d\n", 15, 16);//左对齐
		System.out.printf("%,f\n", 1000000000.000);//对整数部分用半角逗号分组
		System.out.printf("%(d%(d\n", 85, -85);//用括号把负数括起来
		System.out.printf("%#x %#X\n", 94, 95);//用十六进制的形式输出
		System.out.printf("%#o\n", 94);//用八进制的形式输出
		System.out.printf("%d %<05d\n", 94, 98);//格式化前一个参数（两个%d都会输出94）
		
		//日期类型
		Date date1 = new Date();
		System.out.printf("%tc\n", date1);//全部日期信息
		System.out.printf("%tF\n", date1);//年-月-日
		System.out.printf("%tD\n", date1);//月/日/年
		System.out.printf("%tr\n", date1);//时:分:秒 12小时制
		System.out.printf("%tT\n", date1);//时:分:秒 24小时制
		System.out.printf("%tR\n", date1);//时:分    24小时制
	}

}
