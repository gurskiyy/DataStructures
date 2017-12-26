package ua.com.gurskiyy.datastructures.list;

public class ArrayListTest extends AbstractListTest {

    @Override
    List<String> getList(){
        return new ArrayList<>(5);
    }
}

