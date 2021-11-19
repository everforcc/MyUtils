package cc.design.design5;

public class CopyDto implements Cloneable{

    public CopyDto() {
    }

    String key = "key_a";
    Circle circle = new Circle("abc");

    public static void main(String[] args) {

        try {
            CopyDto copyDto = new CopyDto();
            System.out.println(copyDto.key);
            System.out.println(copyDto.circle.clo);
            CopyDto cCopyDto = (CopyDto)copyDto.clone();
            System.out.println(cCopyDto.key);
            System.out.println(cCopyDto.circle.clo);

            cCopyDto.circle.clo = "def";

            System.out.println("-----");

            System.out.println(copyDto.key);
            System.out.println(copyDto.circle.clo);
            System.out.println(cCopyDto.key);
            System.out.println(cCopyDto.circle.clo);
            System.out.println("浅拷贝，不会拷贝引用，所以修改copy对象的引用，原对象的引用也会变");
            System.out.println("可以深copy，需要将对象和对象内的引用都copy不过比较麻烦");
            System.out.println("序列化,序列化以后反序列化即可，但是需要 implements Serializable ");

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
