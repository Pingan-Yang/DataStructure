package Tree.huffmancode;

import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @ClassName HuffmanCode
 * @Description:
 * @Author:ypa
 * @Date 2021/5/19 21:45
 * @Version:1.0
 */
public class HuffmanCode {
    @Test
    public void tese01() throws Exception {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:\\IDEA_Java_Code\\DateStructure\\src\\Tree\\huffmancode\\file\\src\\src.txt"));
        BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("E:\\IDEA_Java_Code\\DateStructure\\src\\Tree\\huffmancode\\file\\src\\dst.txt"));
        //String s = "i like like like java do you like a java";
        byte[] bytes = new byte[1024];
        int len=0;
        while ((len=bis.read(bytes))!=-1);
        System.out.println(Arrays.toString(bytes));
        Map<Byte, Integer> map = byteArray2HashMap(bytes);
        List<Node> nodes = map2List(map);
        Node root = creatHuffmanTree(nodes);
        Map<Byte, String> huffmancodes = getCodes(root);
        byte[] huffmanCodesBytes = zip(bytes, huffmancodes);
        System.out.println(Arrays.toString(huffmanCodesBytes));
       /* byte[] bytes = huffmanCodeZip(s);
        System.out.println(Arrays.toString(bytes));*/
        /*String src = decode(codes, huffmanCodesBytes);
        System.out.println(src);*/
    }

    private Map<Byte, String> codes = new HashMap<>();//用于存放哈夫曼编码
    private StringBuilder stringBuilder = new StringBuilder();//用于拼接哈夫曼编码

    //------------------------哈夫曼编码的过程----------------------------------------------

    /**
     * @Description: 将字符串转成byte数组
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/19
     */
    public byte[] str2ByteArray(String s) {
        byte[] bytes = new byte[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            bytes[i] = (byte) s.charAt(i);
        }
        return bytes;
    }

    /**
     * @Description: 将bytes数组中出现的字节与次数 对应起来 放入到hashmap中
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/19
     */
    public Map<Byte, Integer> byteArray2HashMap(byte[] bytes) {
        Map<Byte, Integer> map = new HashMap<Byte, Integer>();
        for (byte data : bytes) {
            if (map.get(data) == null) {
                map.put(data, 1);
            } else {
                map.put(data, map.get(data) + 1);
            }
        }
        return map;
    }

    /**
     * @Description: 将map中的key-value 构建成一个个Node 并且放入到ArrayList中
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/19
     */
    public List<Node> map2List(Map<Byte, Integer> map) {
        List<Node> nodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            nodes.add(node);
        }
        return nodes;
    }

    /**
     * @Description: 构建哈夫曼树
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/19
     */
    /*
	思路:
	1. 将赫夫曼编码表存放在 Map<Byte,String> 形式
   生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101,
   121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    * */
    public Node creatHuffmanTree(List<Node> nodes) {
        if (nodes == null || nodes.size() == 0)
            return null;
        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);
            Node letfNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parentNode = new Node(null, letfNode.weight + rightNode.weight);
            parentNode.left = letfNode;
            parentNode.rigth = rightNode;
            nodes.remove(letfNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }

    /**
     * @Description: 将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     * @Param: nodes:传入的节点 code：路径 左节点为0，右节点为1  str：用于拼接哈夫曼编码（0101...）
     * @return:
     * @Author: ypa
     * @Date: 2021/5/20
     */
    private void getCodes(Node node, String code, StringBuilder str) {
        StringBuilder str2 = new StringBuilder(str);
        str2.append(code);
        //node==null 不处理
        if (node != null) {
            if (node.data == null) {//非叶子节点
                getCodes(node.left, "0", str2);
                getCodes(node.rigth, "1", str2);
            } else {//叶子节点---即数据
                codes.put(node.data, str2.toString());
            }
        }

    }

    /**
     * @Description: 重载getcodes
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/20
     */
    public Map<Byte, String> getCodes(Node root) {
        if (root == null)
            return null;
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.rigth, "1", stringBuilder);
        return codes;
    }

    /**
     * @Description: 将huffman编码转换为huffman编码对应的byte[]数组
     * @Param: bytes 这时原始的字符串对应的 byte[] bytes 这时原始的字符串对应的 byte[]
     * @return:返回赫夫曼编码处理后的 byte[]
     * @Author: ypa
     * @Date: 2021/5/22
     */
    /*
    举例： String content = "i like like like java do you like a java"; =》 byte[] contentBytes = content.getBytes();
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[1] = -88
    * */
    public byte[] zip(byte[] bytes, Map<Byte, String> codes) {
        StringBuilder str = new StringBuilder();
        for (byte b : bytes) {
            str.append(codes.get(b));
        }
        int len = str.length() % 8 == 0 ? str.length() / 8 : str.length() / 8 + 1;
        byte[] huffmanCodesBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < str.length(); i += 8) {
            if (i + 8 > str.length()) {
                byte data = (byte) Integer.parseInt(str.substring(i), 2);
                huffmanCodesBytes[index] = data;
            } else {
                byte data = (byte) Integer.parseInt(str.substring(i, i + 8), 2);
                huffmanCodesBytes[index++] = data;
            }
        }
        return huffmanCodesBytes;
    }

    /**
     * @Description: 将完整的huffman编码封装成一个方法
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/22
     */
    public byte[] huffmanCodeZip(String str) {
        byte[] bytes = str2ByteArray(str);
        Map<Byte, Integer> map = byteArray2HashMap(bytes);
        List<Node> nodes = map2List(map);
        Node root = creatHuffmanTree(nodes);
        Map<Byte, String> huffmancodes = getCodes(root);
        byte[] huffmanCodesBytes = zip(bytes, huffmancodes);
        return huffmanCodesBytes;
    }

    /**
     * @Description: 将前面的方法封装起来
     * @Param: bytes 原始的字符串对应的字节数组
     * @return: 是经过 赫夫曼编码处理后的字节数组(压缩后的数组)
     * @Author: ypa
     * @Date: 2021/5/23
     */
    private byte[] huffmanZip(byte[] bytes) {
        Map<Byte, Integer> map = byteArray2HashMap(bytes);
        List<Node> list = map2List(map);
        Node root = creatHuffmanTree(list);
        Map<Byte, String> codes = getCodes(root);
        byte[] bytes1 = zip(bytes, codes);
        return bytes1;
    }

    /**
     * @Description: 前序遍历
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/19
     */
    public void preOrder(Node root) {
        if (root == null) {
            return;
        } else {
            root.preOrder();
        }
    }
    //----------------------------------------哈夫曼解码的过程----------------------------------
    /*
    完成数据的解压
	思路
	1. 将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
	   重写先转成 赫夫曼编码对应的二进制的字符串 "1010100010111..."
	2.  赫夫曼编码对应的二进制的字符串 "1010100010111..." =》 对照 赫夫曼编码  =》 "i like like like java do you like a java"
    * */

    /**
     * @Description: 将哈夫曼编码过的数组变成对应的二进制字符串
     * @Param: b 传入的 byte flag 标志是否需要补高位如果是true ，表示需要补高位，如果是false表示不补, 如果是最后一个字节，无需补高位
     * @return: 是该b 对应的二进制的字符串，（注意是按补码返回）
     * @Author: ypa
     * @Date: 2021/5/22
     */
    public String byteToBitString(boolean flag, byte b) {
        //使用变量保存 b
        int temp = b; //将 b 转成 int
        //如果是正数我们还存在补高位
        if (flag) {
            temp |= 256; //按位与 256  1 0000 0000  | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * @Description: 解码
     * @Param: Map<Byte, String> codes 对用的哈夫曼编码的map  byte[]  bytes 哈夫曼编码后的得到的自己数组
     * @return:
     * @Author: ypa
     * @Date: 2021/5/22
     */
    public String decode(Map<Byte, String> codes, byte[] bytes) {
        StringBuilder str = new StringBuilder();//存放对用的bytes对用的二进制字符串
        for (int i = 0; i < bytes.length; ++i) {
            boolean flag = (i == bytes.length - 1);
            String s = byteToBitString(!flag, bytes[i]);
            str.append(s);
        }

        //现在str中存放的就是bytes对应的二进制字符串
        System.out.println(str);
        //2、现在要将二进制字符串转化位正真的原来的字符串
        Map<String, Byte> decodemap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : codes.entrySet()) {
            decodemap.put(entry.getValue(), entry.getKey());
        }
        ArrayList<Byte> list = new ArrayList<>();
        //根据decodemap  扫描二进制字符串 得到原来的string
        for (int i = 0; i < str.length(); ) {
            int count = 1;
            while (true) {
                if (decodemap.get(str.substring(i, i + count)) != null) {
                    //这里输出的直接是105 32 ...是字符对应的数字 而不是真正的字符
                    //System.out.println(decodemap.get(str.substring(i,i+count)).toString()+"~~~");
                    list.add(decodemap.get(str.substring(i, i + count)));
                    i = i + count;
                    break;
                }
                count++;
            }
        }
        //将list转换位字节数组
        byte[] srcByte = new byte[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            srcByte[i] = list.get(i);
        }
        //System.out.println(new String(srcByte));
        //tring str = new String(byte[])会变的(经过charset编码处理，
        //处理的代码是这一句：char[] v = StringCoding.decode(charsetName, bytes, offset, length);
        return new String(srcByte);
    }

    /**
     * @Description: 解码---返回字节数组(重载一下)
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/24
     */
    public byte[] decode(Map<Byte, String> codes, byte[] bytes, boolean f) {
        StringBuilder str = new StringBuilder();//存放对用的bytes对用的二进制字符串
        for (int i = 0; i < bytes.length; ++i) {
            boolean flag = (i == bytes.length - 1);
            String s = byteToBitString(!flag, bytes[i]);
            str.append(s);
        }

        //现在str中存放的就是bytes对应的二进制字符串
        //System.out.println(str);
        //2、现在要将二进制字符串转化位正真的原来的字符串
        Map<String, Byte> decodemap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : codes.entrySet()) {
            decodemap.put(entry.getValue(), entry.getKey());
        }
        ArrayList<Byte> list = new ArrayList<>();
        //根据decodemap  扫描二进制字符串 得到原来的string
        for (int i = 0; i < str.length(); ) {
            int count = 1;
            while (true) {
                if (decodemap.get(str.substring(i, i + count)) != null) {
                    //这里输出的直接是105 32 ...是字符对应的数字 而不是真正的字符
                    //System.out.println(decodemap.get(str.substring(i,i+count)).toString()+"~~~");
                    list.add(decodemap.get(str.substring(i, i + count)));
                    i = i + count;
                    break;
                }
                count++;
            }
        }
        //将list转换位字节数组
        byte[] srcByte = new byte[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            srcByte[i] = list.get(i);
        }
        return srcByte;
    }

    /**
     * @Description: 利用哈夫曼编码来实践文件压缩
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/23
     */
    public void fileZip(String srcFile, String desFile) {
        if (srcFile == null || desFile == null)
            return;
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            //1、先创建一个输入输出流
            is = new FileInputStream(srcFile);
            os = new FileOutputStream(desFile);
            //将输出流与对象流关联起来
            oos = new ObjectOutputStream(os);

            //2、从输入流中获取文件的字节数,初始化用于接受字节的字节数组
            byte[] datas = new byte[is.available()];

            //3、将源文件内容读取到bytes中
            is.read(datas);

            //4、对源文件直接进行压缩
            byte[] huffmanBytes = huffmanZip(datas);

            //5、将压缩后的数据写到目标文件里,哈夫曼编码表也要写进去,不然被无法解压！！！！！！！！！！！
            oos.writeObject(huffmanBytes);
            oos.writeObject(codes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (oos != null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: 实现文件的解压缩
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/24
     */
    public void fileUnZip(String srcFile, String dstFile) {
        if (srcFile == null || dstFile == null)
            return;
        //1、定义输入流、输出流
        FileInputStream is = null;
        ObjectInputStream ois = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream(srcFile);
            ois = new ObjectInputStream(is);
            os = new FileOutputStream(dstFile);

            //2、读入压缩过的直接数组和哈夫曼编码表
            byte[] data = (byte[]) ois.readObject();
            HashMap<Byte, String> map = (HashMap<Byte, String>) ois.readObject();

            //3、解压
            byte[] decodeBytes = decode(map, data, true);

            //4、将解压得到的数据写入到目标文件
            os.write(decodeBytes);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //5、关闭资源文件
            try {
                if (is!=null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(os!=null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(ois!=null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test02() {
        /*
            -1原码：1000 0001
            -1的反码：1111 1110
            -1的补码：1111 1111


            Integer.toBinaryString();
            这个方法  传入正数时 是几位就是几位，不会变成int的4字节（即16位）在前面补0
                     传入负数时 会变成int的4字节（即16位） 不足的前面补1

            所以：要想把一个byte对应的二进制数变成string 负数需要截取 末尾的8位 ，正数则需要补齐前面的0
        * */
        String str = Integer.toBinaryString(-1);
        System.out.println(str);//11111111111111111111111111111111
        System.out.println(str.substring(str.length() - 8));

        str = Integer.toBinaryString(10);
        System.out.println(str);


    }

    @Test
    public void test03() {
        String srcFile = "E:\\IDEA_Java_Code\\DateStructure\\src\\Tree\\huffmancode\\测试文件压缩\\src.bmp";
        String desFile = "E:\\IDEA_Java_Code\\DateStructure\\src\\Tree\\huffmancode\\测试文件压缩\\zipFile.zip";
        fileZip(srcFile, desFile);
        srcFile=desFile;
        desFile = "E:\\IDEA_Java_Code\\DateStructure\\src\\Tree\\huffmancode\\测试文件压缩\\src2.bmp";
        fileUnZip(srcFile,desFile);
    }
}

class Node implements Comparable<Node> {
    Byte data;//存放数据本身
    int weight;//存放数据的权值
    Node left;
    Node rigth;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.rigth != null)
            this.rigth.preOrder();
    }
}
