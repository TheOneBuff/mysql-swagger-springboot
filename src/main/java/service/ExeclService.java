package service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface ExeclService {
    void OutputExecl(String path,String sheetname) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
