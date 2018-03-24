//��һ��List<T>�У����T��ָ����Ŀ��E���ͣ�������һ��List<E>
//srtList ���ԴList
//tClass ԴList��Item�����ͣ�T�Զ����ࣩ
//eClass ԴList��Item����Ҫ�����Ŀ������E��Stringd�ȵȣ�
//fieldName Ҫ�������Ŀ����ע�⣬����������������fieldName���ɶ�Ӧ��get����,���ԣ������б����ж�Ӧ��Get������
public static <T, E> List<E> getItemListFromList(List<T> srtList, Class<T> tClass, Class<E> eClass, String fieldName) {
    List<E> rtnList = new ArrayList<E>();
    Method method = null;
    //��÷����� getXXX
    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

    try {
        //ͨ���������������������ʵ����Ȼ��ȡ�����ʵ����ָ�������������á�
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
            //ִ�ж���t���ض�������ǰ��ȡ�õķ�����method��
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