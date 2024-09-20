package com.jupiter.jdk.hashcode;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2024-09-17
 */
@Data //@Setter @Getter @RequiredArgscContractor @ToString @EqualsAndHashCode
public class HashCodeTest {
    private int id;
    private String name;

    /**
     * HashMap 存取的依据是HashCode和Equals，@Data重写了EqualsAndHashCode，当对象的值改变时hashCode变了。
     * 反编译.class文件看到haseCode()被重写成了与对象的属性相关
     * public int hashCode() {
     * int PRIME = true;
     * int result = 1;
     * result = result * 59 + this.getId();
     * Object $name = this.getName();
     * result = result * 59 + ($name == null ? 43 : $name.hashCode());
     * return result;
     * }
     *
     * @author Jupiter.Lin
     * @date 2024-09-17
     */
    public static void main(String[] args) {
        HashCodeTest obj = new HashCodeTest();
        Map<HashCodeTest, String> map = new HashMap<>();
        obj.setName("张三");
        obj.setId(12);

        System.out.println("Objects.hash(): " + Objects.hash(obj));
        System.out.println("System.identityHashCode(): " + System.identityHashCode(obj));
        System.out.println("hasCode: " + obj.hashCode()); // 779078
        map.put(obj, "第一次存 ");
        System.out.println("map: " + map + ", obj.hashCode:" + obj.hashCode());

        // 姓名变更后，hashCode()返回值也发生了变化，所以用原来存入的key取不到数据。 要解决这个问题可以考虑将hashCode写成只与Id相关，因为Id不会变。
        obj.setName("李四");
        System.out.println("hasCode: " + obj.hashCode()); // 846250
        System.out.println(map.get(obj)); // null
        System.out.println("System.identityHashCode(): " + System.identityHashCode(obj));

        System.out.println("map: " + map + ", obj.hashCode:" + obj.hashCode());

        map.put(obj, "第二次存"); // 由于hasCode不同，所以存了两次。
        System.out.println("map: " + map + ", obj.hashCode:" + obj.hashCode());
        // map: {HashCodeTest(id=12, name=111)=第一次存 , HashCodeTest(id=12, name=111)=第二次存}, obj.hashCode:846250
    }
}
