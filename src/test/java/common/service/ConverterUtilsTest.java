package common.service;

import model.*;
import org.junit.Before;
import org.junit.Test;

import static org.codehaus.groovy.runtime.InvokerHelper.asList;
import static org.junit.Assert.*;

public class ConverterUtilsTest {

    @Test
    public void convertToThrift() throws Exception {
        WinApiClass winApiClass = new WinApiClass(1l, "name", "desctiprion",
                asList(new WinApiFunction(2l, "fun1name", "fun1description",
                        asList(new WinApiParameter(3l, "par1type", "par1name"))
                )));
        TWinApiClass tClass = ConverterUtils.convert(winApiClass);

        assertEquals(winApiClass.getId(), tClass.getId());
        assertEquals(winApiClass.getName(), tClass.getName());
        assertEquals(winApiClass.getDescription(), tClass.getDescription());

        WinApiFunction winApiFunction = winApiClass.getFunctions().get(0);
        TWinApiFunction tWinApiFunction = tClass.getFunctions().get(0);

        assertEquals(winApiFunction.getId(),          tWinApiFunction.getId());
        assertEquals(winApiFunction.getName(),        tWinApiFunction.getName());
        assertEquals(winApiFunction.getDescription(), tWinApiFunction.getDescription());

        WinApiParameter winApiParameter = winApiFunction.getParams().get(0);
        TWinApiParams tWinApiParams = tWinApiFunction.getParams().get(0);

        assertEquals(winApiParameter.getId(),          tWinApiParams.getId());
        assertEquals(winApiParameter.getName(),        tWinApiParams.getName());
        assertEquals(winApiParameter.getType(),        tWinApiParams.getType());
    }

    @Test
    public void convertFromThrift() throws Exception {
        TWinApiClass tClass = new TWinApiClass(1l, "name", "desctiprion",
                asList(new TWinApiFunction(2l, "fun1name", "fun1description",
                        asList(new TWinApiParams(3l, "par1type", "par1name"))
                )));
        WinApiClass winApiClass = ConverterUtils.convert(tClass);

        assertEquals(tClass.getId(), winApiClass.getId());
        assertEquals(tClass.getName(), winApiClass.getName());
        assertEquals(tClass.getDescription(), winApiClass.getDescription());

        WinApiFunction winApiFunction = winApiClass.getFunctions().get(0);
        TWinApiFunction tWinApiFunction = tClass.getFunctions().get(0);

        assertEquals(tWinApiFunction.getId(),          winApiFunction.getId());
        assertEquals(tWinApiFunction.getName(),        winApiFunction.getName());
        assertEquals(tWinApiFunction.getDescription(), winApiFunction.getDescription());

        WinApiParameter winApiParameter = winApiFunction.getParams().get(0);
        TWinApiParams tWinApiParams = tWinApiFunction.getParams().get(0);

        assertEquals(tWinApiParams.getId(),          winApiParameter.getId());
        assertEquals(tWinApiParams.getName(),        winApiParameter.getName());
        assertEquals(tWinApiParams.getType(),        winApiParameter.getType());
    }
}