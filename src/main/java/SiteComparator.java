import java.util.*;

public class SiteComparator {
    private Set<String> changedSites;
    private Set<String> deletedSites;
    private Set<String> appearedSites;
    SortedMap<String,String> yesterday;
    SortedMap<String,String> today;
    SiteComparator(){
        changedSites = new HashSet<String>();
        deletedSites = new HashSet<String>();
        appearedSites = new HashSet<String>();
    }

    public void setYesterday(UrlTable yesterday) {
        this.yesterday = yesterday.getSortedTable();
    }

    public void setToday(UrlTable today) {
        this.today = today.getSortedTable();
    }

    public void checkDiff(){
        boolean T=true,Y=true;
        Iterator<SortedMap.Entry<String,String>> itrY = yesterday.entrySet().iterator();
        Iterator<SortedMap.Entry<String,String>> itrT = today.entrySet().iterator();
        HashMap.Entry<String,String> entryY=null;
        HashMap.Entry<String,String> entryT=null;

        while (itrT.hasNext() && itrY.hasNext()){
            entryT = T ? itrT.next() : entryT;
            entryY = Y ? itrY.next() : entryY;
            if(entryT.getKey().equals(entryY.getKey())){
                if(checkHTMLChanges(entryY.getValue(),entryT.getValue())){
                    changedSites.add(entryT.getKey());
                }
                T=true;
                Y=true;
            } else if(entryY.getKey().hashCode()>entryT.getKey().hashCode()){
                appearedSites.add(entryT.getKey());
                T=true;
                Y=false;
                } else {
                    deletedSites.add(entryY.getKey());
                    T=false;
                    Y=true;
                }
        }
        while (itrT.hasNext()){
            entryT = itrT.next();
            appearedSites.add(entryT.getKey());
        }
        while (itrY.hasNext()){
            entryY = itrY.next();
            deletedSites.add(entryY.getKey());
        }


    }



    private boolean checkHTMLChanges(String htmlY, String htmlT){
        if(htmlT.hashCode()==htmlY.hashCode()){
            return !htmlT.equals(htmlY);
        } else
            return true;
    }

    @Override
    public String toString() {
        return "Здравствуйте, дорогая и.о. секретаря\n" +
               "За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n" +
               "Исчезли следующие страницы:"+deletedSites.toString().replaceAll("(^.)|(.$)|(, )","\n")+
                "Появились следующие новые страницы "+appearedSites.toString().replaceAll("(^.)|(.$)|(, )","\n") +
                "Изменились следующие страницы "+changedSites.toString().replaceAll("(^.)|(.$)|(, )","\n") +
                "С уважением,\n" +
                "автоматизированная система мониторинга";
    }
}
