package gzq.byd.com.testInnerClass;

import java.util.List;

public class MyOuter {
    private List<MyInner> innerList;

    public List<MyInner> getInnerList() {
        return innerList;
    }

    public void setInnerList(List<MyInner> innerList) {
        this.innerList = innerList;
    }

    private static class MyInner{
        private String innerName;

        public MyInner() {
            System.out.println("already");
        }

        public MyInner(String innerName) {
            this.innerName = innerName;
        }
    };
}
