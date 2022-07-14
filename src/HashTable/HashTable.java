package HashTable;



/**
 * @ClassName HashTable
 * @Description:
 * @Author:ypa
 * @Date 2021/5/11 15:55
 * @Version:1.0
 */
public class HashTable {
    private EmpLinkList[] empLinkListArray;
    private int size;
    public HashTable(int size) {
        this.size = size;
        empLinkListArray = new EmpLinkList[size];
        //这里面有个坑---要初始化每一个链表
        for (int i = 0; i < size; i++) {
            empLinkListArray[i] = new EmpLinkList();
        }
    }

    /**
     * @Description: 散列方法-------很重要，方法有很，这里用最简单的取模
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/11
     */
    private int hashFun(int id){
        return id%size;
    }
    /**
    * @Description: 添加节点
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/5/11
    */
    public void add(Emp emp){
        int index = hashFun(emp.getId());
        empLinkListArray[index].add(emp);
    }
    /**
    * @Description: 删除节点
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/5/11
    */
    public void delete(int id){
        int index = hashFun(id);
        empLinkListArray[index].delete(id);
    }
    /**
    * @Description: 查找节点
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/5/11
    */
    public Emp find(int id){
        int index = hashFun(id);
        Emp result = empLinkListArray[index].find(id);
        return result;
    }
    /**
    * @Description: 遍历节点
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/5/11
    */
    public void foreach(){
        for (int i=0;i<empLinkListArray.length;++i){
            System.out.print("第"+(i+1)+"条链表：");
            empLinkListArray[i].foreach(i);
            System.out.println();
        }
    }
}
class Emp{
    private int id;
    private String name;
    private Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }
}
class EmpLinkList{
    private Emp head;//指向第一个节点
    /**
    * @Description: 添加节点--id是自增的
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/5/11
    */
    public void add(Emp emp){
        if (head==null){
            head=emp;
            return;
        }
        Emp temp=head;
        while (temp.getNext()!=null){
            temp=temp.getNext();
        }
        temp.setNext(emp);
    }
    /**
    * @Description: 删除节点--按照id来删
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/5/11
    */
    public void delete(int id){
        if (head==null){
            return;
        }
        Emp temp=head;
        if (temp.getId()==id&&temp.getNext()==null){
            head=null;
            return;
        }
        if (temp.getId()==id&&temp.getNext()!=null){
            head=temp.getNext();
            System.out.println("删除成功");
            return;

        }
        while (true){
            if (temp.getNext()==null){
                break;
            }
            if (temp.getNext().getId()==id){
                temp.setNext(temp.getNext().getNext());
                System.out.println("删除成功");
                return;
            }
            temp=temp.getNext();
        }
        System.out.println("未找到id="+id+"的员工");
    }
    /**
    * @Description: 查找----按id查找
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/5/11
    */
    public Emp find(int id){
        if (head==null){
            return null;
        }
        Emp temp=head;
        while (true){
            if (temp==null){
                break;
            }
            if (temp.getId()==id)
                return temp;
            temp=temp.getNext();
        }
        return null;
    }
    /**
    * @Description: 遍历链表
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/5/11
    */
    public void foreach( int index){
        if (head==null){
            return ;
        }
        Emp temp=head;
        if (temp.getNext()==null){
            System.out.printf("id=%d,name=%s",temp.getId(),temp.getName());
            return;
        }

        while (true){
            if (temp==null)
                break;
            System.out.printf("id=%d,name=%s",temp.getId(),temp.getName());
            if (temp.getNext()!=null){
                System.out.print("-->");
            }
            temp=temp.getNext();
        }
    }
}
