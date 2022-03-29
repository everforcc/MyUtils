package cc.design.design9composite;

public class CompositePatternDemo {
    public static void main(String[] args) {
        Employee CEO = new Employee("John","CEO", 30000);

        // 销售
        Employee headSales = new Employee("Robert","Head Sales", 20000);

        // 营销
        Employee headMarketing = new Employee("Michel","Head Marketing", 20000);

        // 店员
        Employee clerk1 = new Employee("Laura","Marketing", 10000);
        Employee clerk2 = new Employee("Bob","Marketing", 10000);

        // 销售主管
        Employee salesExecutive1 = new Employee("Richard","Sales", 10000);
        Employee salesExecutive2 = new Employee("Rob","Sales", 10000);

        //
        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        //打印该组织的所有员工
        System.out.println(CEO);
        for (Employee headEmployee : CEO.getSubordinates()) {
            System.out.println(headEmployee);
            for (Employee employee : headEmployee.getSubordinates()) {
                System.out.println(employee);
            }
        }
    }

    public void s(){
        StringBuffer stringBuffer = new StringBuffer("s");
        s2(stringBuffer);
        System.out.println(stringBuffer);
    }

    public void s1(StringBuffer s){
        s.append("_1");
    }

    public void s2(StringBuffer s){
        s = new StringBuffer("_2");
    }
}
