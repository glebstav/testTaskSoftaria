import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SiteComparator comp = context.getBean("siteComparatorBean",SiteComparator.class);
        comp.checkDiff();
        System.out.println(comp.toString());
        context.close();
    }
}
