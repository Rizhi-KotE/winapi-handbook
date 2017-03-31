package common.service;

import model.*;
import org.junit.Before;
import org.junit.Test;

import static org.codehaus.groovy.runtime.InvokerHelper.asList;
import static org.junit.Assert.*;

public class ConverterUtilsTest {

    @Test
    public void convertToThrift() throws Exception {
        WinApiClass winApiClass = new WinApiClass(1l, "name", "desctiprion", "example",
                asList(new WinApiFunction(2l, "fun1name", "fun1description", "fun1example",
                        asList(new WinApiParameter(3l, "par1type", "par1name"))
                )));
        TWinApiClass tClass = ConverterUtils.convert(winApiClass);

        assertEquals(winApiClass.getId(), tClass.getId());
        assertEquals(winApiClass.getName(), tClass.getName());
        assertEquals(winApiClass.getDescription(), tClass.getDescription());
        assertEquals(winApiClass.getExample(), tClass.getExample());

        WinApiFunction winApiFunction = winApiClass.getFunctions().get(0);
        TWinApiFunction tWinApiFunction = tClass.getFunctions().get(0);

        assertEquals(winApiFunction.getId(),          tWinApiFunction.getId());
        assertEquals(winApiFunction.getName(),        tWinApiFunction.getName());
        assertEquals(winApiFunction.getDescription(), tWinApiFunction.getDescription());
        assertEquals(winApiFunction.getExample(),     tWinApiFunction.getExample());

        WinApiParameter winApiParameter = winApiFunction.getParams().get(0);
        TWinApiParams tWinApiParams = tWinApiFunction.getParams().get(0);

        assertEquals(winApiParameter.getId(),          tWinApiParams.getId());
        assertEquals(winApiParameter.getName(),        tWinApiParams.getName());
        assertEquals(winApiParameter.getType(),        tWinApiParams.getType());
    }

    @Test
    public void convertFromThrift() throws Exception {
        TWinApiClass tClass = new TWinApiClass(1l, "name", "desctiprion", "example",
                asList(new TWinApiFunction(2l, "fun1name", "fun1description", "fun1example",
                        asList(new TWinApiParams(3l, "par1type", "par1name"))
                )));
        WinApiClass winApiClass = ConverterUtils.convert(tClass);

        assertEquals(tClass.getId(), winApiClassgetId());
        assertEquals(tClass.getName(), winApiClassgetName());
        assertEquals(tClass.getDescription(), winApiClassgetDescription());
        assertEquals(tClass.getExample(), winApiClassgetExample());

        WinApiFunction winApiFunction = winApiClass.getFunctions().get(0);
        TWinApiFunction tWinApiFunction = tClass.getFunctions().get(0);

        assertEquals(tWinApiFunction.getId(),          winApiFunctiongetId());
        assertEquals(tWinApiFunction.getName(),        winApiFunctiongetName());
        assertEquals(tWinApiFunction.getDescription(), winApiFunctiongetDescription());
        assertEquals(tWinApiFunction.getExample(),     winApiFunctiongetExample());

        WinApiParameter winApiParameter = winApiFunction.getParams().get(0);
        TWinApiParams tWinApiParams = tWinApiFunction.getParams().get(0);

        assertEquals(tWinApiParams.getId(),          winApiParametergetId());
        assertEquals(tWinApiParams.getName(),        winApiParametergetName());
        assertEquals(tWinApiParams.getType(),        winApiParametergetType());
    }
}