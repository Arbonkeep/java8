package com.arbonkeep.java8_streamapi;

import org.junit.Test;
import com.arbonkeep.java8_streamapi.Employee.Status;
import java.util.Optional;

/**
 * Optional.of(T t)：创建一个Optional实例

 * Optional.empty()：创建一个空的Optional实例

 * Optional.ofNullable(T t)：若t不为null，创建一个Optional实例，否则创建空实例
     注意：
     * of方法不能构建null，会报出空指针异常
     * empty只是构建一个空的Optional
     * ofNullable传入对象就构建对象，传入null就构建一个空的Optional


 * isPresent()：判断是否包含值

 * orElse(T t)：如果调用对象包含值，返回该值，否则返回t

 * orElseGet(Supplier s)：如果调用对象包含值，返回该值 否则返回s获取的值

 * map(Function f)：如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()

 * flatMap(Function mapper)：与map类似，要求返回值必须是Optional
 */
public class TestOptional {

    @Test
    public void test1() {
        Optional<Employee> op = Optional.of(null);
        Employee emp = op.get();

        System.out.println(emp);//报出空指针异常
    }

    @Test
    public void test2() {
        Optional<Employee> op = Optional.empty();//创建一个空的Optional对象
        Employee emp = op.get();
        System.out.println(emp);
    }

    @Test
    public void test3() {
        Optional<Employee> op = Optional.ofNullable(new Employee());//若t不为null，创建一个Optional实例，否则创建空实例

        /*if (op.isPresent()) {//如果存在对象（就获取打印）
            Employee emp = op.get();
            System.out.println(emp);
        }*/

       /* Employee emp2 = op.orElse(new Employee("张三", 23, 8888.51, Status.FREE));
        //如果Optional为空，那么就用创建的对象代替，如果不为空就用原来的对象
        System.out.println(emp2);*/

        Employee emp3 = op.orElseGet(() -> new Employee("lisi", 24, 5496.0, Status.BUSY));
        System.out.println(emp3);

    }

    @Test
    public void test4() {
        Optional<Employee> op = Optional.ofNullable(new Employee("lisi", 24, 5496.0, Status.BUSY));

        //map(Function f)：如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
        Optional<String> str = op.map((e) -> e.getName());
        System.out.println(str.get());

        //flatMap(Function mapper)：与map类似，要求返回值必须是Optional
        Optional<String> s = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(s.get());

    }

    //例题（男人心目中的女神）
    @Test
    public void test5() {
        //正常情况获取
        /*Man man = new Man();
        String name = getGodnessName(man);
        System.out.println(name);*/

        //通过Optional获取女生名字

        Optional<Godness> godness = Optional.ofNullable(new Godness("徐小小"));
        Optional<NewMan> man = Optional.ofNullable(new NewMan(godness));

        String name2 = getGodnessName2(man);
        System.out.println(name2);

    }

    //声明获取男人心中女生的方法(使用Optional)
    public String getGodnessName2(Optional<NewMan> man) {
        return man.orElse(new NewMan())
                  .getGodness()
                  .orElse(new Godness("李思"))
                  .getName();
    }


    //声明获取男人心中女生的方法（正常处理方法）
    public String getGodnessName(Man man) {
        //由于并不是所有人都有女生，所以我们需要防止空指针异常
        if (man != null) {
            Godness gn = man.getGodness();
            if (gn != null) {
                String name = gn.getName();
                return name;
            }
        }
        return "李思";//如果为空返回一个默认值
    }

}
