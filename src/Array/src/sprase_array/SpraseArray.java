package sprase_array;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

/**
 * @ClassName SpraseArray
 * @Description: 稀疏数组创建
 * @Author:ypa
 * @Date 2020/12/10 10:59
 * @Version:1.0
 */
public class SpraseArray {
    @Test
    public void test() {
        //创建用于测试的二维数组
        int[][] initial = new int[6][7];
        initial[0][3] = 22;
        initial[0][6] = 15;
        initial[1][1] = 11;
        initial[1][5] = 17;
        initial[2][3] = -6;
        initial[3][5] = 39;
        initial[4][0] = 91;
        initial[5][2] = 28;
        /*
            原矩阵：
            0   0   0   22  0   0   15
            0   11  0   0   0   17  0
            0   0   0   -6  0   0   0
            0   0   0   0   0   39  0
            91  0   0   0   0   0   0
            0   0   28  0   0   0   0

            稀疏矩阵：
            6 	 7 	 8
            0 	 3 	 22
            0 	 6 	 15
            1 	 1 	 11
            1 	 5 	 17
            2 	 3 	 -6
            3 	 5 	 39
            4 	 0 	 91
            5 	 2 	 28

        * */
        int[][] sparse=sparseArray(initial);
        saveSparseArray(sparse);
       /* for (int [] cow :sparse){
            for(int date:cow){
                System.out.print(date+" \t ");
            }
            System.out.println();
        }
        //从 稀疏数组转回来
        int[][] array=sparesToInitialArray(sparse);
        for (int [] cow :array){
            for(int date:cow){
                System.out.print(date+" \t ");
            }
            System.out.println();
        }*/
    }

    public int[][] sparseArray(int[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++j) {
                if (array[i][j] != 0) {
                    //用于记录非0值的个数
                    sum++;
                }
            }
        }
        //创建稀疏数组
        //行：value值+1，列：cow col value
        int[][] spars = new int[sum + 1][3];
        spars[0][0] = array.length;
        spars[0][1] = array[0].length;
        spars[0][2] = sum;

        //接下来给非零元素的位置找出来  赋给spars数组
        int count = 0;//利用计数器记录非0的个数,方便给spars的行赋值
        for (int x = 0; x < array.length; ++x) {
            for (int y = 0; y < array[x].length; ++y) {
                if (array[x][y] != 0) {
                    count++;
                    spars[count][0] = x;
                    spars[count][1] = y;
                    spars[count][2] = array[x][y];
                }
            }
        }
        return spars;
    }
    public int[][] sparesToInitialArray(int [][]sparse) {
        //创建原始数组
        //sparse[0][0] 原数组的行数 sparse[0][1]原数组的列数
        int[][] initialArray = new int[sparse[0][0]][sparse[0][1]];
        for (int i = 1; i < sparse.length; ++i) {
            initialArray[sparse[i][0]][sparse[i][1]] = sparse[i][2];
        }
        return initialArray;
    }
    //保存稀疏数组
    public void saveSparseArray(int[][] sparse) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File("./data.txt"));
            for (int i=0;i<sparse.length;++i){
                for(int j=0;j<sparse[i].length;++j){
                    String value=String.valueOf(sparse[i][j])+"  ";
                    fw.write(value);
                }
                fw.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fw!=null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //从文件中读取稀疏数组并转化为原数组
    public int[][] fromFileToArray(String filePath)  {
        ArrayList<String> arrayList = null;
        BufferedReader bufferedReader=null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            //按行读取字符串
            arrayList = new ArrayList<>();
            String str;
            while((str=bufferedReader.readLine())!=null){
                arrayList.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(bufferedReader!=null) {
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 对ArrayList中存储的字符串进行处理
        int length=arrayList.size();
        int width=arrayList.get(0).split("  ").length;
        int [][] array=new int[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                String s = arrayList.get(i).split("  ")[j];
                array[i][j] = Integer.parseInt(s);
            }
        }
        int[][] initialArray=new int[array[0][0]][array[0][1]];
        for (int i = 1; i < array.length; ++i) {
            initialArray[array[i][0]][array[i][1]]=array[i][2];
        }
        //打印结果
        for(int i=0;i<initialArray.length;++i){
            for (int j=0;j<initialArray[i].length;++j){
                System.out.print(initialArray[i][j]+" ");
            }
            System.out.println();
        }
        return initialArray;

    }
    @Test
    public void test01(){
        fromFileToArray("E:\\IDEA_Java_Code\\DateStructure\\Array\\data.txt");
    }

}
