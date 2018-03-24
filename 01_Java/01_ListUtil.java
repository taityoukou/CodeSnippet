//从一个List<T>中，抽出T中指定项目（E类型），返回一个List<E>
//srtList 抽出源List
//tClass 源List中Item的类型（T自定义类）
//eClass 源List中Item类中要抽出项目的类型E（Stringd等等）
//fieldName 要抽出的项目名（注意，工具里面会利用这个fieldName生成对应的get方法,所以，该类中必须有对应的Get方法）
public static <T, E> List<E> getItemListFromList(List<T> srtList, Class<T> tClass, Class<E> eClass, String fieldName) {
    List<E> rtnList = new ArrayList<E>();
    Method method = null;
    //获得方法名 getXXX
    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

    try {
        //通过给定的类名，生成类的实例，然后取得这个实例的指定方法名的引用。
        method = (tClass.newInstance()).getClass().getMethod(methodName);
    } catch (NoSuchMethodException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    }

    for (T t : srtList) {
        E obj = null;
        try {
            //执行对象t的特定方法（前面取得的方法名method）
            obj = (E) method.invoke(t);
            rtnList.add(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    return rtnList;
}