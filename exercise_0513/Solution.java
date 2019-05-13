package exercise.exercise_0513;

/*
43. 字符串相乘
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
示例 1:
输入: num1 = "2", num2 = "3"
输出: "6"
示例 2:
输入: num1 = "123", num2 = "456"
输出: "56088"
说明：
1. num1 和 num2 的长度小于110。
2. num1 和 num2 只包含数字 0-9。
3. num1 和 num2 均不以零开头，除非是数字 0 本身。
4. 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

 */
//方法一
/*
class Solution {
    public String multiply(String num1, String num2) {
        String[] num = {"0",num1,"","","","","","","",""};
        for(int k = 2; k<num.length; k++){
            num[k] = add(num[k-1],num1);
        }
        int j = num2.length()-1;
        String d = "";
        String sum = "0";
        while(j>=0){
            int t = num2.charAt(j) - '0';//提取每一位
            String str = num[t].equals("0") ? "0" : num[t]+d;
            sum = add(sum, str);
            d += "0";//更新t
            j--;
        }
        return sum;
    }
    public String add(String num1, String num2){
        int i = num1.length()-1;
        int j = num2.length()-1;
        int c = 0;//进位
        String result = "";
        while(i>=0 || j>=0){
            int n1 = i>=0 ? num1.charAt(i)-'0' : 0;
            int n2 = j>=0 ? num2.charAt(j)-'0' : 0;
            int sum = n1 + n2 + c;
            result = String.valueOf(sum%10) + result;
            c = sum / 10;
            i--;
            j--;
        }
        if(c > 0){
            result = String.valueOf(c) + result;
        }
        return result;
    }
}
*/

//方法二
/*
分析：
按照平时计算两数相乘的方式来计算，定义一个长度为num1.length()+num2.length()的int型数组num来存放结果。
第一步：
i指针遍历num1字符串，j指针遍历num2字符串，从最低位开始计算，i初始为num1.length()-1，
j初始为num2.length()-1;将num1的第i位字符，num2的第j位字符转换为数字，
两数相乘的结果记为sum,将sum%10累加到num[i+j+1]中，进位sum/10累加到num[i+j]中，
由于类加后num[i+j+1]可能大于9，所以需要更新num[i+j+1]，先将进位num[i+j+1]%10累加到num[i+j]中，
再将num[i+j+1]更新为num[i+j+1]%10。
第二步：
遍历数组num,去除前导0，将结果拼接起来并返回。
 */
class Solution {
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();
        int [] num = new int[len1+len2];
        for(int i = len1-1; i>=0; i--){
            int t = num1.charAt(i)-'0';
            for(int j = len2-1; j>=0; j--){
                int sum = t * (num2.charAt(j)-'0');
                num[i+j+1] += sum %10;
                num[i+j] += (num[i+j+1]/10 + sum /10);
                num[i+j+1] %= 10;
            }
        }
        StringBuilder sb = new StringBuilder("");
        int k = 0;
        //去除前导0
        while(num[k] == 0 && k<len1+len2){
            k++;
        }
        for(; k<len1+len2; k++){
            sb.append(num[k]);
        }
        return sb.toString();
    }
}